package com.qibao.finance.content;

import java.util.Date;

/**
 * Created by ljn on 2018/2/2.
 */
public class GoldBalanceDTO {

    private Long id;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 期初金币
     */
    private Double beginGold;

    /**
     * 平台赠送金币
     */
    private Double platformGiveGold;

    /**
     * 购买金币
     */
    private Double buyGold;

    /**
     * 存入金币
     */
    private Double depositGold;

    /**
     * roll房送出金币
     */
    private Double rollGiveGold;

    /**
     * roll房获得金币
     */
    private Double rollGetGold;

    /**
     * 开箱消耗金币
     */
    private Double openBoxConsumeGold;

    /**
     * 开箱获得金币
     */
    private Double openBoxGetGold;

    /**
     * 奖金池金币
     */
    private Double goldPool;

    /**
     * 奖金库金币
     */
    private Double goldRepository;

    /**
     * 取回金币
     */
    private Double getBackGold;

    /**
     * 取回手续费
     */
    private Double poundage;

    /**
     * 期末金币
     */
    private Double endGold;

    /**
     * 差异
     */
    private Double diffGold;

    /**
     * 结束时间
     */
    private Date endTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    public Double getBeginGold() {
        return beginGold;
    }

    public void setBeginGold(Double beginGold) {
        this.beginGold = beginGold;
    }

    public Double getPlatformGiveGold() {
        return platformGiveGold;
    }

    public void setPlatformGiveGold(Double platformGiveGold) {
        this.platformGiveGold = platformGiveGold;
    }

    public Double getBuyGold() {
        return buyGold;
    }

    public void setBuyGold(Double buyGold) {
        this.buyGold = buyGold;
    }

    public Double getDepositGold() {
        return depositGold;
    }

    public void setDepositGold(Double depositGold) {
        this.depositGold = depositGold;
    }

    public Double getRollGiveGold() {
        return rollGiveGold;
    }

    public void setRollGiveGold(Double rollGiveGold) {
        this.rollGiveGold = rollGiveGold;
    }

    public Double getRollGetGold() {
        return rollGetGold;
    }

    public void setRollGetGold(Double rollGetGold) {
        this.rollGetGold = rollGetGold;
    }

    public Double getOpenBoxConsumeGold() {
        return openBoxConsumeGold;
    }

    public void setOpenBoxConsumeGold(Double openBoxConsumeGold) {
        this.openBoxConsumeGold = openBoxConsumeGold;
    }

    public Double getOpenBoxGetGold() {
        return openBoxGetGold;
    }

    public void setOpenBoxGetGold(Double openBoxGetGold) {
        this.openBoxGetGold = openBoxGetGold;
    }

    public Double getGoldPool() {
        return goldPool;
    }

    public void setGoldPool(Double goldPool) {
        this.goldPool = goldPool;
    }

    public Double getGoldRepository() {
        return goldRepository;
    }

    public void setGoldRepository(Double goldRepository) {
        this.goldRepository = goldRepository;
    }

    public Double getGetBackGold() {
        return getBackGold;
    }

    public void setGetBackGold(Double getBackGold) {
        this.getBackGold = getBackGold;
    }

    public Double getPoundage() {
        return poundage;
    }

    public void setPoundage(Double poundage) {
        this.poundage = poundage;
    }

    public Double getEndGold() {
        return endGold;
    }

    public void setEndGold(Double endGold) {
        this.endGold = endGold;
    }

    public Double getDiffGold() {
        return diffGold;
    }

    public void setDiffGold(Double diffGold) {
        this.diffGold = diffGold;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
