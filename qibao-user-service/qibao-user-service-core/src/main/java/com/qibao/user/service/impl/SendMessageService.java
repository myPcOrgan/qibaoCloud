package com.qibao.user.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.qibao.common.exception.BaseException;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.HttpUtil;
import com.qibao.user.context.dto.SendMessageDTO;
import com.qibao.user.entity.UserAccountEO;
import com.qibao.user.redis.IUserRedisDao;
import com.qibao.user.service.ISendMessage;
import com.qibao.user.service.IThirdLoginService;
import com.qibao.user.utils.Md5;
import com.qibao.user.utils.UserRedisKeyHelper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class SendMessageService extends BaseService<UserAccountEO> implements ISendMessage {

    @Value("${SENDMESSAGEURL}")
    private String sendMessageUrl;

    @Value("${SENDMESSAGECATEGORY}")
    private String sendMessageCategory;

    @Value("${SENDMESSAGEKEY}")
    private String sendMessageKey;

    @Value("${SENDMESSAGEIP}")
    private String sendMessageIp;

    @Autowired
    IThirdLoginService thirdLoginService;

    @Autowired
    IUserRedisDao userRedisDao;

    public Boolean sendMessage(SendMessageDTO sendMessageDTO) {
        Boolean resultB = true;
        try {
            String context = sendMessageDTO.getContext();
            String phone = sendMessageDTO.getPhone();
            //进行发送短信前的验证
            this.beforeSend(sendMessageDTO);
            String str = sendMessageKey + sendMessageIp;
            byte[] bytes = str.getBytes("UTF-8");
            str = new String(bytes,"gb2312");
            String sign = Md5.getMd5String(str);
            context = URLEncoder.encode(context,"UTF-8");
            Map<String, Object> queryMap = new HashMap<>();
            queryMap.put("m_sign", sign);
            queryMap.put("m_clientIP", sendMessageIp);
            queryMap.put("category",sendMessageCategory);
            queryMap.put("mobile", phone);
            queryMap.put("content", context);
            String result = HttpUtil.httpGet(sendMessageUrl, queryMap, "UTF-8");
            LOGGER.info("发送短信返回结果："+ result);
//            if (StringUtils.isNotBlank(result)) {
//                JSONObject jsonObject = JSONObject.parseObject(result);
//                boolean code1 = jsonObject.containsKey("ResultNo");
//                if (code1){
//                    int code = (int)jsonObject.get("ResultNo");
//                    if (code == 0){
                        //发送短信后保存
                        this.afterSend(sendMessageDTO);
                        return true;
//                    }
//                }
//            }
        } catch (UnsupportedEncodingException e) {
            LOGGER.info("发送短信秘钥编码异常："+ e.getMessage());
        } catch (IOException e) {
            LOGGER.info("发送短信发送请求异常："+ e.getMessage());
        }
        return resultB;
    }


    private void beforeSend(SendMessageDTO sendMessageDTO){
        // 检查同个IP短信发送数量
        String num = userRedisDao.getValueRedis(UserRedisKeyHelper.messageUserIpNumIncrease(sendMessageDTO.getUserIp()));
        if (StringUtils.isBlank(num)){
            num = "0";
        }
        int count = Integer.parseInt(num);
        if (count > 50) {
            LOGGER.error("同个ip一个小时内最多只能发送50条短信,ip:{}", sendMessageDTO.getUserIp());
            throw new BaseException(01,"同个ip一个小时内最多只能发送50条短信");
        }

        // 从redis中获取短信验证码
        String code = userRedisDao.getValueRedis(UserRedisKeyHelper.userPhoneVerifyCodeRedis(sendMessageDTO.getPhone()));
        if (StringUtils.isNotBlank(code)) {
            LOGGER.error("60秒内访问频率太快拒绝发送短信");
            // 同个手机号码，60秒内只能发送一次短信
            throw new BaseException(01,"60秒内访问频率太快,请稍后重试");
        }

        // 检查当天短信是否达到发送上限
        String daySendNum = userRedisDao.getValueRedis(UserRedisKeyHelper.messageDayNumIncrease());
        if (StringUtils.isBlank(daySendNum)){
            daySendNum = "0";
        }
        int dayNum = Integer.parseInt(daySendNum);
        //设置的当天发送的短信数量限制
        String dayTotalNum = userRedisDao.getValueRedis(UserRedisKeyHelper.messageDayTotalNumDay());
        if (StringUtils.isBlank(dayTotalNum)){
            dayTotalNum = "7000";
        }
        int dayNum1 = Integer.parseInt(dayTotalNum);
        if (dayNum > dayNum1){
            LOGGER.error("今日短信发送数量已达上限");
            throw new BaseException(01,"今日短信发送数量已达上限");
        }
    }

    private void afterSend(SendMessageDTO sendMessageDTO){
        //同个IP每小时发送条数
        userRedisDao.addIncrement(UserRedisKeyHelper.messageUserIpNumIncrease(sendMessageDTO.getUserIp()));
        Calendar oneHourLast = getOneHourOrDayLast(0);
        userRedisDao.setExpireAt(UserRedisKeyHelper.messageUserIpNumIncrease(sendMessageDTO.getUserIp()), oneHourLast.getTime());
        //每天发送消息条数增加
        userRedisDao.addIncrement(UserRedisKeyHelper.messageDayNumIncrease());
        //设置缓存数据最后的失效时间为当天的最后一秒
        Calendar oneDayLast = getOneHourOrDayLast(1);
        userRedisDao.setExpireAt(UserRedisKeyHelper.messageDayNumIncrease(), oneDayLast.getTime());
        if (StringUtils.isNotBlank(sendMessageDTO.getCode())) {
            thirdLoginService.addPhoneVerifyCode(sendMessageDTO.getPhone(), sendMessageDTO.getCode());
        }
    }

    /**
     * 获取每小时最后一秒与每天最后一秒
     * @param type 0.每小时  1.每天
     * @return
     */
    private Calendar getOneHourOrDayLast(Integer type){
        Date now = new Date();
        Calendar nowCal = Calendar.getInstance();
        nowCal.setTime(now);
        Calendar lastCal = Calendar.getInstance();
        if (Integer.valueOf(1).equals(type)) {
            lastCal.set(nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH), nowCal.get(Calendar.DAY_OF_MONTH),
                    23, 59, 59);
        } else {
            lastCal.set(nowCal.get(Calendar.YEAR), nowCal.get(Calendar.MONTH), nowCal.get(Calendar.DAY_OF_MONTH),
                    nowCal.get(Calendar.HOUR_OF_DAY), 59, 59);
        }
        lastCal.set(Calendar.MILLISECOND, 999);
        return lastCal;
    }

}
