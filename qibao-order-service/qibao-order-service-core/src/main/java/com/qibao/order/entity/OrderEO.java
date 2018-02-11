package com.qibao.order.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_order")
public class OrderEO extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 主订单号
     */
    @Column(name = "main_order_no")
    private String mainOrderNo;

    /**
     * 订单编号
     */
    @Column(name = "order_no")
    private String orderNo;

    /**
     * 金币商城订单id
     */
    @Column(name = "mall_order_id")
    private String mallOrderId;

    /**
     * 交易人(存入时是收货商，取出时是出货商)
     */
    private String trader;

    /**
     * 金额
     */
    @Column(name = "amount_money")
    private Double amountMoney;

    /**
     * 商品ID
     */
    @Column(name = "goods_id")
    private Long goodsId;

    /**
     * 商品分类(服id)
     */
    @Column(name = "cate_id")
    private Long cateId;

    /**
     * 游戏角色
     */
    @Column(name = "game_role")
    private String gameRole;

    /**
     * 货币单位
     */
    @Column(name = "currency_unit")
    private String currencyUnit;

    /**
     * 货币数量
     */
    @Column(name = "currency_num")
    private Double currencyNum;

    /**
     * 实际交易数量
     */
    @Column(name = "deal_num")
    private Double dealNum;

    /**
     * 订单类型(1.商城购买;2.存入;3.取回;4.补单)
     */
    @Column(name = "order_type")
    private Integer orderType;

    /**
     * 手续费
     */
    private Double poundage;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 用户帐号
     */
    @Column(name = "user_account")
    private String userAccount;

    /**
     * 订单状态(1.待付款；2.存入中；3.取回中；4.交易取消;5.交易成功;6.付款失败)
     */
    @Column(name = "order_status")
    private Integer orderStatus;

    /**
     * 描述
     */
    private String description;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 最后修改时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 客服帐号
     */
    @Column(name = "service_account")
    private String serviceAccount;

    /**
     * 区服名称
     */
    @Column(name = "region_server_name")
    private String regionServerName;

    /**
     * 支付方式(1,支付宝；2.微信)
     */
    @Column(name = "pay_type")
    private Integer payType;

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
     * 获取主订单号
     *
     * @return main_order_no - 主订单号
     */
    public String getMainOrderNo() {
        return mainOrderNo;
    }

    /**
     * 设置主订单号
     *
     * @param mainOrderNo 主订单号
     */
    public void setMainOrderNo(String mainOrderNo) {
        this.mainOrderNo = mainOrderNo;
    }

    /**
     * 获取订单编号
     *
     * @return order_no - 订单编号
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * 设置订单编号
     *
     * @param orderNo 订单编号
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * 获取金币商城订单id
     *
     * @return mall_order_id - 金币商城订单id
     */
    public String getMallOrderId() {
        return mallOrderId;
    }

    /**
     * 设置金币商城订单id
     *
     * @param mallOrderId 金币商城订单id
     */
    public void setMallOrderId(String mallOrderId) {
        this.mallOrderId = mallOrderId;
    }

    /**
     * 获取交易人(存入时是收货商，取出时是出货商)
     *
     * @return trader - 交易人(存入时是收货商，取出时是出货商)
     */
    public String getTrader() {
        return trader;
    }

    /**
     * 设置交易人(存入时是收货商，取出时是出货商)
     *
     * @param trader 交易人(存入时是收货商，取出时是出货商)
     */
    public void setTrader(String trader) {
        this.trader = trader;
    }

    /**
     * 获取金额
     *
     * @return amount_money - 金额
     */
    public Double getAmountMoney() {
        return amountMoney;
    }

    /**
     * 设置金额
     *
     * @param amountMoney 金额
     */
    public void setAmountMoney(Double amountMoney) {
        this.amountMoney = amountMoney;
    }

    /**
     * 获取商品ID
     *
     * @return goods_id - 商品ID
     */
    public Long getGoodsId() {
        return goodsId;
    }

    /**
     * 设置商品ID
     *
     * @param goodsId 商品ID
     */
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }

    /**
     * 获取商品分类(服id)
     *
     * @return cate_id - 商品分类(服id)
     */
    public Long getCateId() {
        return cateId;
    }

    /**
     * 设置商品分类(服id)
     *
     * @param cateId 商品分类(服id)
     */
    public void setCateId(Long cateId) {
        this.cateId = cateId;
    }

    /**
     * 获取游戏角色
     *
     * @return game_role - 游戏角色
     */
    public String getGameRole() {
        return gameRole;
    }

    /**
     * 设置游戏角色
     *
     * @param gameRole 游戏角色
     */
    public void setGameRole(String gameRole) {
        this.gameRole = gameRole;
    }

    /**
     * 获取货币单位
     *
     * @return currency_unit - 货币单位
     */
    public String getCurrencyUnit() {
        return currencyUnit;
    }

    /**
     * 设置货币单位
     *
     * @param currencyUnit 货币单位
     */
    public void setCurrencyUnit(String currencyUnit) {
        this.currencyUnit = currencyUnit;
    }

    /**
     * 获取货币数量
     *
     * @return currency_num - 货币数量
     */
    public Double getCurrencyNum() {
        return currencyNum;
    }

    /**
     * 设置货币数量
     *
     * @param currencyNum 货币数量
     */
    public void setCurrencyNum(Double currencyNum) {
        this.currencyNum = currencyNum;
    }

    /**
     * 获取实际交易数量
     *
     * @return deal_num - 实际交易数量
     */
    public Double getDealNum() {
        return dealNum;
    }

    /**
     * 设置实际交易数量
     *
     * @param dealNum 实际交易数量
     */
    public void setDealNum(Double dealNum) {
        this.dealNum = dealNum;
    }

    /**
     * 获取订单类型(1.商城购买;2.存入;3.取回;4.补单)
     *
     * @return order_type - 订单类型(1.商城购买;2.存入;3.取回;4.补单)
     */
    public Integer getOrderType() {
        return orderType;
    }

    /**
     * 设置订单类型(1.商城购买;2.存入;3.取回;4.补单)
     *
     * @param orderType 订单类型(1.商城购买;2.存入;3.取回;4.补单)
     */
    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    /**
     * 获取手续费
     *
     * @return poundage - 手续费
     */
    public Double getPoundage() {
        return poundage;
    }

    /**
     * 设置手续费
     *
     * @param poundage 手续费
     */
    public void setPoundage(Double poundage) {
        this.poundage = poundage;
    }

    /**
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取用户帐号
     *
     * @return user_account - 用户帐号
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置用户帐号
     *
     * @param userAccount 用户帐号
     */
    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    /**
     * 获取订单状态(1.待付款；2.存入中；3.取回中；4.交易取消;5.交易成功;6.付款失败)
     *
     * @return order_status - 订单状态(1.待付款；2.存入中；3.取回中；4.交易取消;5.交易成功;6.付款失败)
     */
    public Integer getOrderStatus() {
        return orderStatus;
    }

    /**
     * 设置订单状态(1.待付款；2.存入中；3.取回中；4.交易取消;5.交易成功;6.付款失败)
     *
     * @param orderStatus 订单状态(1.待付款；2.存入中；3.取回中；4.交易取消;5.交易成功;6.付款失败)
     */
    public void setOrderStatus(Integer orderStatus) {
        this.orderStatus = orderStatus;
    }

    /**
     * 获取描述
     *
     * @return description - 描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置描述
     *
     * @param description 描述
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取最后修改时间
     *
     * @return last_update_time - 最后修改时间
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置最后修改时间
     *
     * @param lastUpdateTime 最后修改时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取是否删除
     *
     * @return is_deleted - 是否删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置是否删除
     *
     * @param isDeleted 是否删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * 获取客服帐号
     *
     * @return service_account - 客服帐号
     */
    public String getServiceAccount() {
        return serviceAccount;
    }

    /**
     * 设置客服帐号
     *
     * @param serviceAccount 客服帐号
     */
    public void setServiceAccount(String serviceAccount) {
        this.serviceAccount = serviceAccount;
    }

    /**
     * 获取区服名称
     *
     * @return region_server_name - 区服名称
     */
    public String getRegionServerName() {
        return regionServerName;
    }

    /**
     * 设置区服名称
     *
     * @param regionServerName 区服名称
     */
    public void setRegionServerName(String regionServerName) {
        this.regionServerName = regionServerName;
    }

    /**
     * 获取支付方式(1,支付宝；2.微信)
     *
     * @return pay_type - 支付方式(1,支付宝；2.微信)
     */
    public Integer getPayType() {
        return payType;
    }

    /**
     * 设置支付方式(1,支付宝；2.微信)
     *
     * @param payType 支付方式(1,支付宝；2.微信)
     */
    public void setPayType(Integer payType) {
        this.payType = payType;
    }
}