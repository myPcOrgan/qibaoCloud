package com.qibao.payment.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_gold_balance")
public class GoldBalanceEO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 开始时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 期初金币
     */
    @Column(name = "begin_gold")
    private Double beginGold;

    /**
     * 平台赠送金币
     */
    @Column(name = "platform_give_gold")
    private Double platformGiveGold;

    /**
     * 购买金币
     */
    @Column(name = "buy_gold")
    private Double buyGold;

    /**
     * 存入金币
     */
    @Column(name = "deposit_gold")
    private Double depositGold;

    /**
     * roll房送出金币
     */
    @Column(name = "roll_give_gold")
    private Double rollGiveGold;

    /**
     * roll房获得金币
     */
    @Column(name = "roll_get_gold")
    private Double rollGetGold;

    /**
     * 开箱消耗金币
     */
    @Column(name = "open_box_consume_gold")
    private Double openBoxConsumeGold;

    /**
     * 开箱获得金币
     */
    @Column(name = "open_box_get_gold")
    private Double openBoxGetGold;

    /**
     * 奖金池金币
     */
    @Column(name = "gold_pool")
    private Double goldPool;

    /**
     * 奖金库金币
     */
    @Column(name = "gold_repository")
    private Double goldRepository;

    /**
     * 取回金币
     */
    @Column(name = "get_back_gold")
    private Double getBackGold;

    /**
     * 取回手续费
     */
    private Double poundage;

    /**
     * 期末金币
     */
    @Column(name = "end_gold")
    private Double endGold;

    /**
     * 差异
     */
    @Column(name = "diff_gold")
    private Double diffGold;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 开箱奖金池金币累计
     */
    @Column(name = "total_gold_pool")
    private Double totalGoldPool;

    /**
     * 开箱奖金库金币累计
     */
    @Column(name = "total_gold_repository")
    private Double totalGoldRepository;

    /**
     * 平台获取金币
     */
    @Column(name = "platform_get_gold")
    private Double platformGetGold;

    /**
     * 平台获取金币累计
     */
    @Column(name = "total_platform_get_gold")
    private Double totalPlatformGetGold;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取开始时间
     *
     * @return begin_time - 开始时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置开始时间
     *
     * @param beginTime 开始时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取期初金币
     *
     * @return begin_gold - 期初金币
     */
    public Double getBeginGold() {
        return beginGold;
    }

    /**
     * 设置期初金币
     *
     * @param beginGold 期初金币
     */
    public void setBeginGold(Double beginGold) {
        this.beginGold = beginGold;
    }

    /**
     * 获取平台赠送金币
     *
     * @return platform_give_gold - 平台赠送金币
     */
    public Double getPlatformGiveGold() {
        return platformGiveGold;
    }

    /**
     * 设置平台赠送金币
     *
     * @param platformGiveGold 平台赠送金币
     */
    public void setPlatformGiveGold(Double platformGiveGold) {
        this.platformGiveGold = platformGiveGold;
    }

    /**
     * 获取购买金币
     *
     * @return buy_gold - 购买金币
     */
    public Double getBuyGold() {
        return buyGold;
    }

    /**
     * 设置购买金币
     *
     * @param buyGold 购买金币
     */
    public void setBuyGold(Double buyGold) {
        this.buyGold = buyGold;
    }

    /**
     * 获取存入金币
     *
     * @return deposit_gold - 存入金币
     */
    public Double getDepositGold() {
        return depositGold;
    }

    /**
     * 设置存入金币
     *
     * @param depositGold 存入金币
     */
    public void setDepositGold(Double depositGold) {
        this.depositGold = depositGold;
    }

    /**
     * 获取roll房送出金币
     *
     * @return roll_give_gold - roll房送出金币
     */
    public Double getRollGiveGold() {
        return rollGiveGold;
    }

    /**
     * 设置roll房送出金币
     *
     * @param rollGiveGold roll房送出金币
     */
    public void setRollGiveGold(Double rollGiveGold) {
        this.rollGiveGold = rollGiveGold;
    }

    /**
     * 获取roll房获得金币
     *
     * @return roll_get_gold - roll房获得金币
     */
    public Double getRollGetGold() {
        return rollGetGold;
    }

    /**
     * 设置roll房获得金币
     *
     * @param rollGetGold roll房获得金币
     */
    public void setRollGetGold(Double rollGetGold) {
        this.rollGetGold = rollGetGold;
    }

    /**
     * 获取开箱消耗金币
     *
     * @return open_box_consume_gold - 开箱消耗金币
     */
    public Double getOpenBoxConsumeGold() {
        return openBoxConsumeGold;
    }

    /**
     * 设置开箱消耗金币
     *
     * @param openBoxConsumeGold 开箱消耗金币
     */
    public void setOpenBoxConsumeGold(Double openBoxConsumeGold) {
        this.openBoxConsumeGold = openBoxConsumeGold;
    }

    /**
     * 获取开箱获得金币
     *
     * @return open_box_get_gold - 开箱获得金币
     */
    public Double getOpenBoxGetGold() {
        return openBoxGetGold;
    }

    /**
     * 设置开箱获得金币
     *
     * @param openBoxGetGold 开箱获得金币
     */
    public void setOpenBoxGetGold(Double openBoxGetGold) {
        this.openBoxGetGold = openBoxGetGold;
    }

    /**
     * 获取奖金池金币
     *
     * @return gold_pool - 奖金池金币
     */
    public Double getGoldPool() {
        return goldPool;
    }

    /**
     * 设置奖金池金币
     *
     * @param goldPool 奖金池金币
     */
    public void setGoldPool(Double goldPool) {
        this.goldPool = goldPool;
    }

    /**
     * 获取奖金库金币
     *
     * @return gold_repository - 奖金库金币
     */
    public Double getGoldRepository() {
        return goldRepository;
    }

    /**
     * 设置奖金库金币
     *
     * @param goldRepository 奖金库金币
     */
    public void setGoldRepository(Double goldRepository) {
        this.goldRepository = goldRepository;
    }

    /**
     * 获取取回金币
     *
     * @return get_back_gold - 取回金币
     */
    public Double getGetBackGold() {
        return getBackGold;
    }

    /**
     * 设置取回金币
     *
     * @param getBackGold 取回金币
     */
    public void setGetBackGold(Double getBackGold) {
        this.getBackGold = getBackGold;
    }

    /**
     * 获取取回手续费
     *
     * @return poundage - 取回手续费
     */
    public Double getPoundage() {
        return poundage;
    }

    /**
     * 设置取回手续费
     *
     * @param poundage 取回手续费
     */
    public void setPoundage(Double poundage) {
        this.poundage = poundage;
    }

    /**
     * 获取期末金币
     *
     * @return end_gold - 期末金币
     */
    public Double getEndGold() {
        return endGold;
    }

    /**
     * 设置期末金币
     *
     * @param endGold 期末金币
     */
    public void setEndGold(Double endGold) {
        this.endGold = endGold;
    }

    /**
     * 获取差异
     *
     * @return diff_gold - 差异
     */
    public Double getDiffGold() {
        return diffGold;
    }

    /**
     * 设置差异
     *
     * @param diffGold 差异
     */
    public void setDiffGold(Double diffGold) {
        this.diffGold = diffGold;
    }

    /**
     * 获取结束时间
     *
     * @return end_time - 结束时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置结束时间
     *
     * @param endTime 结束时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取开箱奖金池金币累计
     *
     * @return total_gold_pool - 开箱奖金池金币累计
     */
    public Double getTotalGoldPool() {
        return totalGoldPool;
    }

    /**
     * 设置开箱奖金池金币累计
     *
     * @param totalGoldPool 开箱奖金池金币累计
     */
    public void setTotalGoldPool(Double totalGoldPool) {
        this.totalGoldPool = totalGoldPool;
    }

    /**
     * 获取开箱奖金库金币累计
     *
     * @return total_gold_repository - 开箱奖金库金币累计
     */
    public Double getTotalGoldRepository() {
        return totalGoldRepository;
    }

    /**
     * 设置开箱奖金库金币累计
     *
     * @param totalGoldRepository 开箱奖金库金币累计
     */
    public void setTotalGoldRepository(Double totalGoldRepository) {
        this.totalGoldRepository = totalGoldRepository;
    }

    /**
     * 获取平台获取金币
     *
     * @return platform_get_gold - 平台获取金币
     */
    public Double getPlatformGetGold() {
        return platformGetGold;
    }

    /**
     * 设置平台获取金币
     *
     * @param platformGetGold 平台获取金币
     */
    public void setPlatformGetGold(Double platformGetGold) {
        this.platformGetGold = platformGetGold;
    }

    /**
     * 获取平台获取金币累计
     *
     * @return total_platform_get_gold - 平台获取金币累计
     */
    public Double getTotalPlatformGetGold() {
        return totalPlatformGetGold;
    }

    /**
     * 设置平台获取金币累计
     *
     * @param totalPlatformGetGold 平台获取金币累计
     */
    public void setTotalPlatformGetGold(Double totalPlatformGetGold) {
        this.totalPlatformGetGold = totalPlatformGetGold;
    }
}