package com.qibao.user.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.qibao.common.exception.BaseException;
import com.qibao.common.service.abs.BaseService;
import com.qibao.user.context.enums.GoldTypeEnum;
import com.qibao.user.context.enums.SysMessageTypeEnum;
import com.qibao.user.dao.UserAccountEOMapper;
import com.qibao.user.entity.MessageEO;
import com.qibao.user.entity.UserAccountEO;
import com.qibao.user.enums.ThirdLoginTypeEnum;
import com.qibao.user.exceptions.UserException;
import com.qibao.user.redis.IUserRedisDao;
import com.qibao.user.service.IMessageService;
import com.qibao.user.service.IUserService;
import com.qibao.user.utils.UserRedisKeyHelper;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by 340067 on 2018/1/10.
 */
@Component
public class UserService extends BaseService<UserAccountEO> implements IUserService {

    @Autowired
    IUserRedisDao userRedisDao;
    @Autowired
    UserAccountEOMapper userAccountEOMapper;
    @Autowired
    IMessageService messageService;



    private static final String PREFIX = "pc_";
    private static final String NICKPREFIX = "用户_";
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    @Override
    public UserAccountEO getUserInfoById(Long userId){
        UserAccountEO userAccountEO;
        if (userId == null){
            throw new UserException(UserException.USERID_EMPTY);
        }
        String userInfo = userRedisDao.getValueRedis(UserRedisKeyHelper.userInfoRedis(userId.toString()));
        if (StringUtils.isBlank(userInfo)){
            userAccountEO = this.selectById(userId);
            if (userAccountEO != null) {
                String result = JSONObject.toJSONString(userAccountEO);
                userRedisDao.addValueRedis(UserRedisKeyHelper.userInfoRedis(userId.toString()),result);
            }
        } else {
            try {
                userAccountEO = JSON.parseObject(userInfo, UserAccountEO.class);
            } catch (Exception e) {
                throw new BaseException(01,"json转换异常");
            }
        }
        return userAccountEO;
    }

    @Override
    public Long insertUserInfo(UserAccountEO userAccountEO) {
        String userAccount = userAccountEO.getUserAccount();
        if (StringUtils.isBlank(userAccount)) {
            userAccount = getUserAccount();
        }
        String userNick = userAccountEO.getNickName();
        if (StringUtils.isBlank(userNick)) {
            userNick = getUserNick();
        }
        userAccountEO.setCreateTime(new Date());
        userAccountEO.setLastUpdateTime(new Date());
        userAccountEO.setUserAccount(userAccount);
        userAccountEO.setNickName(userNick);
        userAccountEO.setTotalAmount(Double.valueOf(0));
        userAccountEO.setExceedCount(0);
        Long userId = this.insert(userAccountEO);
        String value = JSONObject.toJSONString(userAccountEO);
        userRedisDao.addValueRedis(UserRedisKeyHelper.userInfoRedis(userAccountEO.getId().toString()),value);
        return userId;
    }

    @Override
    public void updateUserInfo(UserAccountEO userAccountEO) {
        if (userAccountEO.getId() == null){
            throw new UserException(UserException.USERID_EMPTY);
        }
        UserAccountEO userAccountEO1 = this.getUserInfoById(userAccountEO.getId());
        if (userAccountEO1 == null){
            throw new UserException(UserException.USER_NOT_EXITED);
        }
        //如果修改，判断手机号，账号，昵称是否存在
        checkOnlyOne(userAccountEO);
        boolean update = this.update(userAccountEO);
        if (!update){
            throw new BaseException(01,"更新数据库异常");
        }
        userAccountEO = this.selectById(userAccountEO.getId());
        String value = JSONObject.toJSONString(userAccountEO);
        userRedisDao.addValueRedis(UserRedisKeyHelper.userInfoRedis(userAccountEO.getId().toString()),value);
    }

    /**
     * 判断手机号，QQ或微信用户是否存在
     * @param message 手机号，QQ或微信号
     * @param loginType 类型，1.QQ 2.微信  3.手机号
     * @return
     */
    @Override
    public UserAccountEO checkQQExist(String message, Integer loginType) {
        UserAccountEO userAccountEO = new UserAccountEO();
        if (loginType.equals(ThirdLoginTypeEnum.PHONE.getCode())){
            userAccountEO.setMobilePhone(message);
        } else if (loginType.equals(ThirdLoginTypeEnum.THIRDQQ.getCode())){
            userAccountEO.setQqCode(message);
        } else if (loginType.equals(ThirdLoginTypeEnum.THIRDWECHAT.getCode())){
            userAccountEO.setWechartCode(message);
        }
        List<UserAccountEO> userAccountEOList = this.select(userAccountEO);
        if (CollectionUtils.isEmpty(userAccountEOList)){
            return null;
        }
        return userAccountEOList.get(0);
    }

    @Override
    public List<UserAccountEO> listUserInfoByIds(List<Long> userIds) {
        List<UserAccountEO> userAccountEOS = new ArrayList<>();
        if (CollectionUtils.isEmpty(userIds)){
            return userAccountEOS;
        }
        Example example = new Example(UserAccountEO.class);
        example.createCriteria().andIn("id",userIds);
        example.createCriteria().andEqualTo("isDeleted",0);
        userAccountEOS = this.selectByExample(example);
        if (CollectionUtils.isEmpty(userAccountEOS)){
            return userAccountEOS;
        }
        return userAccountEOS;
    }

    @Override
    public List<UserAccountEO> listUserAccountByMap(Map<String, Object> params) {
        if (params.containsKey("page") && params.containsKey("size")){
            Integer page = (Integer) params.get("page");
            Integer size = (Integer) params.get("size");
            PageHelper.startPage(page,size);
        }
        if (params.containsKey("orderBy") && params.containsKey("isAsc")) {
            Boolean isAsc = (Boolean)params.get("isAsc");
            if (isAsc) {
                params.put("ORDER","ASC");
            }else{
                params.put("ORDER","DESC");
            }
        }
        List<UserAccountEO> userAccountEOs = userAccountEOMapper.listUserAccountByMap(params);
        return userAccountEOs;
    }

    @Override
    public int countByMap(Map<String, Object> params) {
        int num = userAccountEOMapper.countByMap(params);
        return num;
    }

    @Override
    public void updateUserGold(Long userId, Integer type, Double goldNum, String desp) {
        UserAccountEO userAccountEO = this.getUserInfoById(userId);
        String title = "加金币";
        if (userAccountEO == null){
            throw new UserException(UserException.USER_NOT_EXITED);
        }
        if (userAccountEO.getTotalAmount() == null){
            userAccountEO.setTotalAmount(Double.valueOf("0"));
        }
        if (type.equals(GoldTypeEnum.ADDTOTALGOLD.getCode())){
            userAccountEO.setTotalAmount(userAccountEO.getTotalAmount() + goldNum);
        } else if (type.equals(GoldTypeEnum.REDUCETOTALGOLD.getCode())){
            if (userAccountEO.getTotalAmount() < goldNum){
                throw new BaseException(01,"该用户金币少于减少金币");
            }
            userAccountEO.setTotalAmount(userAccountEO.getTotalAmount() - goldNum);
            title = "减金币";
        } else if (type.equals(GoldTypeEnum.ALLWINNUM.getCode())){
            userAccountEO.setExceedCount(userAccountEO.getExceedCount() + 1);
            userAccountEO.setTotalAmount(userAccountEO.getTotalAmount() + goldNum);
        } else if (type.equals(GoldTypeEnum.ALLWINNUMNULL.getCode())){
            userAccountEO.setExceedCount(0);
            userAccountEO.setTotalAmount(userAccountEO.getTotalAmount() + goldNum);
        }
        userAccountEO.setLastUpdateTime(new Date());
        this.update(userAccountEO);
        String value = JSONObject.toJSONString(userAccountEO);
        userRedisDao.addValueRedis(UserRedisKeyHelper.userInfoRedis(userAccountEO.getId().toString()),value);
        //添加日志
        this.addSysMessage(title,desp,userId,goldNum);
    }

    @Override
    public Double sumToatalGold(Map<String, Object> params){
        double num = userAccountEOMapper.sumToatalGold(params);
        return num;
    }

    /**
     * 获取用户账号
     * @return
     */
    private String getUserAccount(){
        String userAccount;
        List<UserAccountEO> userAccountEOS;
        do {
            userAccount = simpleDateFormat.format(new Date());
            String code = RandomStringUtils.randomNumeric(7);
            userAccount = PREFIX + userAccount.substring(2,8) + code;
            UserAccountEO userAccountEO1 = new UserAccountEO();
            userAccountEO1.setUserAccount(userAccount);
            userAccountEOS = this.select(userAccountEO1);
        } while (!CollectionUtils.isEmpty(userAccountEOS));
        return userAccount;
    }

    /**
     * 获取用户昵称
     * @return
     */
    private String getUserNick(){
        String userNick;
        List<UserAccountEO> userAccountEOS;
        int i = 0;
        int num = 5;
        do {
            String code = RandomStringUtils.randomNumeric(num);
            userNick = NICKPREFIX + code;
            UserAccountEO userAccountEO1 = new UserAccountEO();
            userAccountEO1.setNickName(userNick);
            userAccountEOS = this.select(userAccountEO1);
            if (i > 2){
                num ++;
            }
            i++;
        } while (!CollectionUtils.isEmpty(userAccountEOS));
        return userNick;
    }

    /**
     * 添加系统日志
     * @param title
     * @param desp
     * @param userId
     */
    private void addSysMessage(String title, String desp, Long userId, Double goldNum){
        MessageEO messageEO = new MessageEO();
        messageEO.setMessageType(SysMessageTypeEnum.SYSTEM.getCode());
        messageEO.setCreateTime(new Date());
        messageEO.setContent(desp);
        messageEO.setIsView(false);
        messageEO.setName(title);
        messageEO.setUserId(userId);
        messageEO.setUpdateGolds(goldNum);
        messageService.insert(messageEO);
    }

    /**
     * 判断手机号，昵称与账号是否唯一
     * @param userAccountEO
     */
    private void checkOnlyOne(UserAccountEO userAccountEO){
        UserAccountEO oldUserAccountEO = new UserAccountEO();
        if (StringUtils.isNotBlank(userAccountEO.getMobilePhone())){
            if (!userAccountEO.getMobilePhone().matches("^1(3|4|5|7|8)\\d{9}$")){
                throw new UserException(UserException.PHONE_ERROR_FORMAT);
            }
            oldUserAccountEO.setMobilePhone(userAccountEO.getMobilePhone());
            List<UserAccountEO> userAccountEOs = this.select(oldUserAccountEO);
            if (!CollectionUtils.isEmpty(userAccountEOs)){
                oldUserAccountEO.setMobilePhone(null);
                throw new BaseException(01,"该手机号已存在");
            }
        } else if (StringUtils.isNotBlank(userAccountEO.getUserAccount())){
            oldUserAccountEO.setUserAccount(userAccountEO.getUserAccount());
            List<UserAccountEO> userAccountEOs = this.select(oldUserAccountEO);
            if (!CollectionUtils.isEmpty(userAccountEOs)){
                oldUserAccountEO.setMobilePhone(null);
                throw new BaseException(01,"该账号已存在");
            }
        }else if (StringUtils.isNotBlank(userAccountEO.getNickName())){
            oldUserAccountEO.setNickName(userAccountEO.getNickName());
            List<UserAccountEO> userAccountEOs = this.select(oldUserAccountEO);
            if (!CollectionUtils.isEmpty(userAccountEOs)){
                oldUserAccountEO.setMobilePhone(null);
                throw new BaseException(01,"该昵称已存在");
            }
        }
    }
}
