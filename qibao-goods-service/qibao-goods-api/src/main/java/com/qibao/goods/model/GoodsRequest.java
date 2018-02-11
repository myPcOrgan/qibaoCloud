package com.qibao.goods.model;

public class GoodsRequest {
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
     * 0是否1是已删除
     */
    private Boolean isDeleted;

    /**
     * 分页查询-第几页
     */
    private Integer page;

    /**
     * 分页查询-每页大小
     */
    private Integer pageSize;

    /**
     * 排序字段
     */
    private String orderBy;

    /**
     * true为正序
     */
    private Boolean isAsc;

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

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public Boolean getIsAsc() {
        return isAsc;
    }

    public void setIsAsc(Boolean asc) {
        isAsc = asc;
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
                ", isDeleted=" + isDeleted +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", isAsc=" + isAsc +
                '}';
    }
}