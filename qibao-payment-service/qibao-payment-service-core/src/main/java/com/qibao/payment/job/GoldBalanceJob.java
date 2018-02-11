package com.qibao.payment.job;

import com.qibao.activity.entity.dto.UserPrizeDTO;
import com.qibao.activity.entity.vo.UserPrizeLotteryGoldVO;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.DateUtil;
import com.qibao.finance.vo.GoldBalanceVO;
import com.qibao.order.enums.OrderType;
import com.qibao.payment.entity.GoldBalanceEO;
import com.qibao.payment.feign.IOrderFeign;
import com.qibao.payment.feign.IUserFeign;
import com.qibao.payment.feign.IUserPrizeFeign;
import com.qibao.payment.service.IGoldBalanceService;
import com.qibao.user.context.dto.UserAccountDTO;
import com.qibao.user.context.enums.UserGradeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 定时生成金币平衡数据
 * Created by ljn on 2018/2/2.
 */
@Component
public class GoldBalanceJob extends BaseController{

    @Autowired
    private IOrderFeign orderFeign;

    @Autowired
    private IUserFeign userFeign;

    @Autowired
    private IUserPrizeFeign userPrizeFeign;

    @Autowired
    private IGoldBalanceService goldBalanceService;

    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 每天凌晨4点生成金币平衡表数据
     */
    @Scheduled(cron="0 0 4 * * ?")
    public void addGoldBalance(){
        boolean result = false;
        int num = 0;
        while(num < 3 && !result) {
            result = this.add();
            num ++;
            if (num > 2) {
                LOGGER.info(getStartTime().toString() + ":生成金币平衡表数据发生异常");
            }
        }
    }

    public boolean add(){
        boolean result = false;
        try{
            //期末金币
            double endGold = this.getUserGold();
            //平台赠送金币
            double officialGold = this.getOfficialGold();
            //购买金币
            double ordinaryGold = this.getOrdinaryGold();
            //存入金币
            double depositGold = this.getMallTradeGold(OrderType.DEPOSIT);
            //取回金币
            double getBackGold = this.getMallTradeGold(OrderType.GET_BACK);
            //开始时间
            Date startTime = format.parse(this.getStartTime());
            //结束时间
            Date endTime = format.parse(this.getEndTime());
            //Roll房送出金币

            //Roll房获得金币

            UserPrizeLotteryGoldVO prizeInfo = this.getPrizeInfo();
            //开箱消耗金币
            double openBoxConsumeGold = prizeInfo.getBoxNum() == null ? 0.0 : prizeInfo.getBoxNum();
            //开箱获得金币
            double openBoxGetGold = prizeInfo.getWinGold()== null ? 0.0 : prizeInfo.getWinGold();
            //开箱奖金池金币
            double goldPool = prizeInfo.getBoxGoldPond() == null ? 0.0 : prizeInfo.getBoxGoldPond();
            //开箱奖金库金币
            double goldRepository = prizeInfo.getBoxGoldPondOverflow() == null ? 0.0 : prizeInfo.getBoxGoldPondOverflow();
            //开箱奖金池金币累计
            double totalGoldPool = prizeInfo.getBoxGoldPondTotal() == null ? 0.0 : prizeInfo.getBoxGoldPondTotal();
            //开箱奖金库金币累计
            double totalGoldRepository = prizeInfo.getBoxGoldPondOverflowTotal() == null ? 0.0 : prizeInfo.getBoxGoldPondOverflowTotal();
            //取回手续费（金币）
            double poundage = this.getPoundage();
            //平台获取金币 = 当天开箱奖金库金币+取回手续费（金币）
            double platformGetGold = goldRepository + poundage;
            //期初金币
            double beginGold = 0.0;
            //平台获取金币累计 = 昨天平台获取金币累计+当天平台获取金币
            double totalPlatformGetGold = 0.0;
            GoldBalanceVO goldBalanceVO = this.getStartGold();
            if (goldBalanceVO != null) {
                beginGold = goldBalanceVO.getEndGold() == null ? 0.0 : goldBalanceVO.getEndGold();
                double platformGold = goldBalanceVO.getTotalPlatformGetGold() == null ? 0.0 : goldBalanceVO.getTotalPlatformGetGold();
                totalPlatformGetGold = platformGold + platformGetGold;
            }
            //差异 = 期初金币+平台赠送金币+购买金币+存入金币-取回金币-取回手续费（金币）-期末金币-今天奖金池和奖金库增加的
            double diffNum = beginGold + officialGold + ordinaryGold + depositGold - getBackGold - poundage - endGold - goldPool - goldRepository;
            GoldBalanceEO gold = new GoldBalanceEO();
            gold.setBeginGold(beginGold);
            gold.setEndGold(endGold);
            gold.setPlatformGiveGold(officialGold);
            gold.setBuyGold(ordinaryGold);
            gold.setDepositGold(depositGold);
            gold.setGetBackGold(getBackGold);
            gold.setBeginTime(startTime);
            gold.setEndTime(endTime);


            gold.setDiffGold(diffNum);
            gold.setOpenBoxConsumeGold(openBoxConsumeGold);
            gold.setOpenBoxGetGold(openBoxGetGold);
            gold.setGoldPool(goldPool);
            gold.setGoldRepository(goldRepository);
            gold.setTotalGoldPool(totalGoldPool);
            gold.setTotalGoldRepository(totalGoldRepository);
            gold.setPoundage(poundage);
            gold.setPlatformGetGold(platformGetGold);
            gold.setTotalPlatformGetGold(totalPlatformGetGold);
            goldBalanceService.add(gold);
            result = true;
        }catch (BaseException e){
            LOGGER.info(e.getErrorMsg());
        } catch (Exception e){
            LOGGER.info(e.getMessage());
        }finally {
            return result;
        }
    }

    /**
     * 获取前一天官方用户购买的金币数量
     * @return
     */
    private double getOfficialGold() throws ParseException {
        BaseResponse<Double> response = orderFeign.selectBuyGoldNum(UserGradeEnum.OFFICIAL.getCode(),getStartTime(),getEndTime());
        double num = 0.0 ;
        if (!response.getCode().equals("00")) {
            throw new BaseException(01,"获取官方购买金币数量发生异常");
        }
        num = response.getResult();
        return num;
    }

    /**
     * 获取前一天普通用户购买的金币数量
     * @return
     */
    private double getOrdinaryGold() throws ParseException {
        BaseResponse<Double> response = orderFeign.selectBuyGoldNum(UserGradeEnum.GENERAL.getCode(),getStartTime(),getEndTime());
        double num = 0.0 ;
        if (!response.getCode().equals("00")) {
            throw new BaseException(01,"获取购买金币数量发生异常");
        }
        num = response.getResult();
        return num;
    }

    private double getUserGold(){
        UserAccountDTO dto = new UserAccountDTO();
        dto.setUserGrade(0);
        BaseResponse<Double> response = userFeign.getNumByUserGrade(dto);
        double num = 0.0;
        if (!response.getCode().equals("00")) {
            throw new BaseException(01,"获取期末金币发生异常");
        }
        num = response.getResult();
        return num;
    }

    /**
     * 存入，取出
     * @param orderType
     * @return
     */
    private double getMallTradeGold(OrderType orderType) throws ParseException {
        BaseResponse<Double> response = orderFeign.selectMallTradeGold(orderType.getCode(),getStartTime(),getEndTime());
        double num = 0.0;
        if (!response.getCode().equals("00")) {
            throw new BaseException(01,"获取存入，取出数据异常");
        }
        num = response.getResult();
        return num;
    }

    /**
     * 前一天4点
     * @return
     */
    public String getStartTime() {
        Calendar start = Calendar.getInstance();
        start.add(Calendar.DATE,-1);
        start.set(Calendar.HOUR_OF_DAY, 4);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        return format.format(start.getTime());
    }

    /**
     * 今天3点59分59秒
     * @return
     */
    public String getEndTime() {
        Calendar end = Calendar.getInstance();
        end.add(Calendar.DATE,0);
        end.set(Calendar.HOUR_OF_DAY, 3);
        end.set(Calendar.MINUTE, 59);
        end.set(Calendar.SECOND, 59);
        end.set(Calendar.MILLISECOND, 999);
        return format.format(end.getTime());
    }

    /**
     * 期初金币（即前一天的期末金币）
     * @return
     */
    private GoldBalanceVO getStartGold(){
        Map<String,Object> map = new HashMap<>();
        Calendar start = Calendar.getInstance();
        start.add(Calendar.DATE,-1);
        start.set(Calendar.HOUR_OF_DAY, 0);
        start.set(Calendar.MINUTE, 0);
        start.set(Calendar.SECOND, 0);
        start.set(Calendar.MILLISECOND, 0);
        Date startTime = start.getTime();
        map.put("startTime",startTime);
        map.put("endTime", DateUtil.oneDateLastTime(start.getTime()));
        List<GoldBalanceVO> list = goldBalanceService.selectGoldBalance(map, null, null, "id", true);
        if (!CollectionUtils.isEmpty(list)) {
            return list.get(0);
        }
        return null;
    }

    /**
     * 当天平台所有用户取回金币过程中实际发生的手续费（金币）总数
     */
    private Double getPoundage() throws ParseException {
        BaseResponse<Double> response = orderFeign.selectPoundage(getStartTime(),getEndTime());
        double num = 0.0 ;
        if (!response.getCode().equals("00")) {
            throw new BaseException(01,"获取手续费发生异常");
        }
        num = response.getResult();
        return num;
    }

    private UserPrizeLotteryGoldVO getPrizeInfo(){
        UserPrizeDTO dto = new UserPrizeDTO();
        dto.setBeginDateStr(getStartTime());
        dto.setEndDateStr(getEndTime());
        UserPrizeLotteryGoldVO prizeInfo = userPrizeFeign.selectLotteryGoldToDay(dto).getResult();
        if (prizeInfo == null) {
            throw new BaseException(01,"获取开箱金币信息异常");
        }
        return prizeInfo;
    }






}
