package com.qibao.order.content;

/**
 * Created by ljn on 2018/1/18.
 */
public class OrderDTO {

    private Long id;

    private Long userId;

    /**
     * 用户帐号
     */
    private String userAccount;

    /**
     * 第几页
     */
    private Integer page;

    /**
     * 一页多少条
     */
    private Integer size;

    /**
     * 创建开始时间
     */
    private String createStartTime;

    /**
     * 创建结束时间
     */
    private String createEndTime;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 订单类型
     */
    private Integer orderType;

    /**
     * 金额
     */
    private Double amountMoney;

    /**
     * 商品id
     */
    private Long goodsId;

    /**
     * 手续费
     */
    private Double poundage;

    /**
     * 游戏角色
     */
    private String gameRole;

    /**
     * 七宝订单编号
     */
    private String orderNo;

    /**
     * 区服名称
     */
    private String regionServerName;

    /**
     * 通货数量
     */
    private Double currencyNum;

    /**
     * 分类id
     */
    private Integer cateId;

    /**
     * 金币商城订单号
     */
    private String mallOrderId;

    /**
     * 客服id
     */
    private String serviceAccount;

    private String currencyUnit;

    public String getCurrencyUnit() {
        return currencyUnit;
    }

    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getServiceAccount() {
        return serviceAccount;
    }

    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
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

    public String getRegionServerName() {
        return regionServerName;
    }

    public void setRegionServerName(String regionServerName) {
        this.regionServerName = regionServerName;
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

    public String getMallOrderId() {
        return mallOrderId;
    }

    public void setMallOrderId(String mallOrderId) {
        this.mallOrderId = mallOrderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getGameRole() {
        return gameRole;
    }

    public void setGameRole(String gameRole) {
        this.gameRole = gameRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getCreateStartTime() {
        return createStartTime;
    }

    public void setCreateStartTime(String createStartTime) {
        this.createStartTime = createStartTime;
    }

    public String getCreateEndTime() {
        return createEndTime;
    }

    public void setCreateEndTime(String createEndTime) {
        this.createEndTime = createEndTime;
    }

    public Integer getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }
}
