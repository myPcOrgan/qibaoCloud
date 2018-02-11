package com.qibao.goods.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_goods")
public class GoodsEO extends BaseEntity{
    /**
     * 商品类型,1为区服，2地下城游戏币
     */
    private Long category;

    /**
     * 商品编号
     */
    @Column(name = "goods_no")
    private String goodsNo;

    /**
     * 商品名称
     */
    @Column(name = "goods_name")
    private String goodsName;

    /**
     * 商品单位
     */
    @Column(name = "goods_unit")
    private String goodsUnit;

    /**
     * 商品数量
     */
    @Column(name = "goods_num")
    private Long goodsNum;

    /**
     * 商品金额
     */
    @Column(name = "goods_amount")
    private Double goodsAmount;

    /**
     * 图片id
     */
    @Column(name = "img_id")
    private Long imgId;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 修改时间
     */
    @Column(name = "last_update_time")
    private Date lastUpdateTime;

    /**
     * 0是否1是已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 获取商品类型
     *
     * @return category - 商品类型
     */
    public Long getCategory() {
        return category;
    }

    /**
     * 设置商品类型
     *
     * @param category 商品类型
     */
    public void setCategory(Long category) {
        this.category = category;
    }

    /**
     * 获取商品编号
     *
     * @return goods_no - 商品编号
     */
    public String getGoodsNo() {
        return goodsNo;
    }

    /**
     * 设置商品编号
     *
     * @param goodsNo 商品编号
     */
    public void setGoodsNo(String goodsNo) {
        this.goodsNo = goodsNo;
    }

    /**
     * 获取商品名称
     *
     * @return goods_name - 商品名称
     */
    public String getGoodsName() {
        return goodsName;
    }

    /**
     * 设置商品名称
     *
     * @param goodsName 商品名称
     */
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    /**
     * 获取货币单位
     *
     * @return goods_unit - 货币单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置货币单位
     *
     * @param goodsUnit 货币单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * 获取货币数量
     *
     * @return goods_num - 货币数量
     */
    public Long getGoodsNum() {
        return goodsNum;
    }

    /**
     * 设置货币数量
     *
     * @param goodsNum 货币数量
     */
    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }

    /**
     * 获取商品金额
     *
     * @return goodsAmount - 商品金额
     */
    public Double getGoodsAmount() {
        return goodsAmount;
    }

    /**
     * 设置商品金额
     *
     * @param goodsAmount 商品金额
     */
    public void setGoodsAmount(Double goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    /**
     * 获取图片id
     *
     * @return img_id - 图片id
     */
    public Long getImgId() {
        return imgId;
    }

    /**
     * 设置图片id
     *
     * @param imgId 图片id
     */
    public void setImgId(Long imgId) {
        this.imgId = imgId;
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
     * 获取修改时间
     *
     * @return last_update_time - 修改时间
     */
    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    /**
     * 设置修改时间
     *
     * @param lastUpdateTime 修改时间
     */
    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    /**
     * 获取0是否1是已删除
     *
     * @return is_deleted - 0是否1是已删除
     */
    public Boolean getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置0是否1是已删除
     *
     * @param isDeleted 0是否1是已删除
     */
    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "GoodsEO{" +
                "id=" + getId() +
                ", category=" + category + '\'' +
                ", goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", goodsNum=" + goodsNum +
                ", goodsAmount=" + goodsAmount +
                ", imgId=" + imgId +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", isDeleted=" + isDeleted +
                '}';
    }
}