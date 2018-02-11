package com.qibao.goods.model;

/**
 * Created by 周黎钢 on 2018/1/29.
 */
public class GoodsVO {
    private Long id;
    /**
     * 商品类型,1为地下城游戏币，大于1暂定
     */
    private Long category;

    /**
     * 商品编号
     */
    private String goodsNo;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 商品单位
     */
    private String goodsUnit;

    /**
     * 商品数量
     */
    private Long goodsNum;

    /**
     * 商品金额
     */
    private Double goodsAmount;

    /**
     * 图片id
     */
    private Long imgId;


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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GoodsRequest{" +
                "id=" + id +
                ", category=" + category +
                ", goodsNo='" + goodsNo + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", goodsNum=" + goodsNum +
                ", goodsAmount=" + goodsAmount +
                ", imgId=" + imgId +
                '}';
    }
}
