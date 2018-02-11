package com.qibao.payment.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_finance_balance")
public class FinanceBalanceEO extends BaseEntity {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 期初时间
     */
    @Column(name = "begin_time")
    private Date beginTime;

    /**
     * 期末时间
     */
    @Column(name = "end_time")
    private Date endTime;

    /**
     * 期初金额
     */
    @Column(name = "begin_balance")
    private Double beginBalance;

    /**
     * 期末金额
     */
    @Column(name = "end_balance")
    private Double endBalance;

    /**
     * 平台购买金额
     */
    @Column(name = "platform_buy_balance")
    private Double platformBuyBalance;

    /**
     * 用户购买金额
     */
    @Column(name = "buy_balance")
    private Double buyBalance;

    /**
     * 售得金额
     */
    @Column(name = "sell_balance")
    private Double sellBalance;

    /**
     * 取回金额
     */
    @Column(name = "get_back_balance")
    private Double getBackBalance;

    /**
     * 开箱奖金库金额
     */
    @Column(name = "repository_balance")
    private Double repositoryBalance;

    /**
     * 取回手续费
     */
    @Column(name = "poundage_balance")
    private Double poundageBalance;

    /**
     * 差异
     */
    @Column(name = "diff_balance")
    private Double diffBalance;

    /**
     * 商城交易均价
     */
    @Column(name = "mall_average_price")
    private Double mallAveragePrice;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取期初时间
     *
     * @return begin_time - 期初时间
     */
    public Date getBeginTime() {
        return beginTime;
    }

    /**
     * 设置期初时间
     *
     * @param beginTime 期初时间
     */
    public void setBeginTime(Date beginTime) {
        this.beginTime = beginTime;
    }

    /**
     * 获取期末时间
     *
     * @return end_time - 期末时间
     */
    public Date getEndTime() {
        return endTime;
    }

    /**
     * 设置期末时间
     *
     * @param endTime 期末时间
     */
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    /**
     * 获取期初金额
     *
     * @return begin_balance - 期初金额
     */
    public Double getBeginBalance() {
        return beginBalance;
    }

    /**
     * 设置期初金额
     *
     * @param beginBalance 期初金额
     */
    public void setBeginBalance(Double beginBalance) {
        this.beginBalance = beginBalance;
    }

    /**
     * 获取期末金额
     *
     * @return end_balance - 期末金额
     */
    public Double getEndBalance() {
        return endBalance;
    }

    /**
     * 设置期末金额
     *
     * @param endBalance 期末金额
     */
    public void setEndBalance(Double endBalance) {
        this.endBalance = endBalance;
    }

    /**
     * 获取平台购买金额
     *
     * @return platform_buy_balance - 平台购买金额
     */
    public Double getPlatformBuyBalance() {
        return platformBuyBalance;
    }

    /**
     * 设置平台购买金额
     *
     * @param platformBuyBalance 平台购买金额
     */
    public void setPlatformBuyBalance(Double platformBuyBalance) {
        this.platformBuyBalance = platformBuyBalance;
    }

    /**
     * 获取用户购买金额
     *
     * @return buy_balance - 用户购买金额
     */
    public Double getBuyBalance() {
        return buyBalance;
    }

    /**
     * 设置用户购买金额
     *
     * @param buyBalance 用户购买金额
     */
    public void setBuyBalance(Double buyBalance) {
        this.buyBalance = buyBalance;
    }

    /**
     * 获取售得金额
     *
     * @return sell_balance - 售得金额
     */
    public Double getSellBalance() {
        return sellBalance;
    }

    /**
     * 设置售得金额
     *
     * @param sellBalance 售得金额
     */
    public void setSellBalance(Double sellBalance) {
        this.sellBalance = sellBalance;
    }

    /**
     * 获取取回金额
     *
     * @return get_back_balance - 取回金额
     */
    public Double getGetBackBalance() {
        return getBackBalance;
    }

    /**
     * 设置取回金额
     *
     * @param getBackBalance 取回金额
     */
    public void setGetBackBalance(Double getBackBalance) {
        this.getBackBalance = getBackBalance;
    }

    /**
     * 获取开箱奖金库金额
     *
     * @return repository_balance - 开箱奖金库金额
     */
    public Double getRepositoryBalance() {
        return repositoryBalance;
    }

    /**
     * 设置开箱奖金库金额
     *
     * @param repositoryBalance 开箱奖金库金额
     */
    public void setRepositoryBalance(Double repositoryBalance) {
        this.repositoryBalance = repositoryBalance;
    }

    /**
     * 获取取回手续费
     *
     * @return poundage_balance - 取回手续费
     */
    public Double getPoundageBalance() {
        return poundageBalance;
    }

    /**
     * 设置取回手续费
     *
     * @param poundageBalance 取回手续费
     */
    public void setPoundageBalance(Double poundageBalance) {
        this.poundageBalance = poundageBalance;
    }

    /**
     * 获取差异
     *
     * @return diff_balance - 差异
     */
    public Double getDiffBalance() {
        return diffBalance;
    }

    /**
     * 设置差异
     *
     * @param diffBalance 差异
     */
    public void setDiffBalance(Double diffBalance) {
        this.diffBalance = diffBalance;
    }

    /**
     * 获取商城交易均价
     *
     * @return mall_average_price - 商城交易均价
     */
    public Double getMallAveragePrice() {
        return mallAveragePrice;
    }

    /**
     * 设置商城交易均价
     *
     * @param mallAveragePrice 商城交易均价
     */
    public void setMallAveragePrice(Double mallAveragePrice) {
        this.mallAveragePrice = mallAveragePrice;
    }
}