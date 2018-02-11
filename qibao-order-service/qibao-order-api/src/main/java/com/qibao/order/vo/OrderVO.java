package com.qibao.order.vo;

import java.util.Date;

/**
 * Created by ljn on 2018/1/16.
 */
public class OrderVO {

    private String mainOrderNo;

    private String orderNo;

    private Integer orderType;

    private Integer orderStatus;

    private Double amountMoney;

    private Long goodsId;

    private Double poundage;

    private String userAccount;

    private Long userId;

    private String regionServerName;

    private String gameRole;

    private Double currencyNum;

    private Integer cateId;

    private Double dealNum;

    private String trader;

    private String mallOrderId;

    private String serviceAccount;

    private Date createTime;

    private Integer payType;

    private String currencyUnit;

    public String getMainOrderNo() {
        return mainOrderNo;
    }

    public void setMainOrderNo(String mainOrderNo) {
        this.mainOrderNo = mainOrderNo;
    }

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public Integer getPayType() {
        return payType;
    }

    public void setPayType(Integer payType) {
        this.payType = payType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    public String getMallOrderId() {
        return mallOrderId;
    }

    public void setMallOrderId(String mallOrderId) {
        this.mallOrderId = mallOrderId;
    }

    public String getTrader() {
        return trader;
    }

    public void setTrader(String trader) {
        this.trader = trader;
    }

    public Double getDealNum() {
        return dealNum;
    }

    public void setDealNum(Double dealNum) {
        this.dealNum = dealNum;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public Double getAmountMoney() {
        return amountMoney;
    }

    public void setAmountMoney(Double amountMoney) {
        this.amountMoney = amountMoney;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    public Double getPoundage() {
        return poundage;
    }

    public void setPoundage(Double poundage) {
        this.poundage = poundage;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getRegionServerName() {
        return regionServerName;
    }

    public void setRegionServerName(String regionServerName) {
        this.regionServerName = regionServerName;
    }

    public String getGameRole() {
        return gameRole;
    }

    public void setGameRole(String gameRole) {
        this.gameRole = gameRole;
    }

    public Double getCurrencyNum() {
        return currencyNum;
    }

    public void setCurrencyNum(Double currencyNum) {
        this.currencyNum = currencyNum;
    }

    public Integer getCateId() {
        return cateId;
    }

    public void setCateId(Integer cateId) {
        this.cateId = cateId;
    }
}
