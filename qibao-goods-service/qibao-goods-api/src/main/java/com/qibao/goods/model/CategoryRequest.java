package com.qibao.goods.model;

import java.util.Date;

public class CategoryRequest {
    private Long id;
    /**
     * 分类code,1表示区服，2地下城游戏币
     */
    private String cateCode;

    /**
     * 名称
     */
    private String cateName;

    /**
     * 商品单位
     */
    private String goodsUnit;

    /**
     * 层级id,记录父级的分类ID，把所有父级的ID用逗号拼接成字符串，当层级为0时该字段为空字符串
     */
    private String levelId;

    /**
     * 层级，默认为0，代表最上层父级,1为下一级，2为下下一级，依次类推
     */
    private Integer cateLevel;

    /**
     * 父类别，当层级为0时该字段为空
     */
    private Long parentId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date lastUpdateTime;

    /**
     * false是未删除true是已删除
     */
    private Boolean isDeleted;

    /**
     * false是禁用，true是启用
     */
    private Boolean isEnable;

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
     * 获取分类code
     *
     * @return cate_code - 分类code
     */
    public String getCateCode() {
        return cateCode;
    }

    /**
     * 设置分类code
     *
     * @param cateCode 分类code
     */
    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    /**
     * 获取名称
     *
     * @return cate_name - 名称
     */
    public String getCateName() {
        return cateName;
    }

    /**
     * 设置名称
     *
     * @param cateName 名称
     */
    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    /**
     * 获取商品单位
     *
     * @return goods_unit - 商品单位
     */
    public String getGoodsUnit() {
        return goodsUnit;
    }

    /**
     * 设置商品单位
     *
     * @param goodsUnit 商品单位
     */
    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }

    /**
     * 获取层级id
     *
     * @return level_id - 层级id
     */
    public String getLevelId() {
        return levelId;
    }

    /**
     * 设置层级id
     *
     * @param levelId 层级id
     */
    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    /**
     * 获取层级
     *
     * @return cate_level - 层级
     */
    public Integer getCateLevel() {
        return cateLevel;
    }

    /**
     * 设置层级
     *
     * @param cateLevel 层级
     */
    public void setCateLevel(Integer cateLevel) {
        this.cateLevel = cateLevel;
    }

    /**
     * 获取父类别
     *
     * @return parent_id - 父类别
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父类别
     *
     * @param parentId 父类别
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    /**
     * 获取0是已启用，1是禁用
     *
     * @return is_enable - 0是已启用，1是禁用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置0是已启用，1是禁用
     *
     * @param isEnable 0是已启用，1是禁用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
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
        return "CategoryRequest{" +
                "id=" + id +
                ", cateCode='" + cateCode + '\'' +
                ", cateName='" + cateName + '\'' +
                ", goodsUnit='" + goodsUnit + '\'' +
                ", levelId='" + levelId + '\'' +
                ", cateLevel=" + cateLevel +
                ", parentId=" + parentId +
                ", createTime=" + createTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", isDeleted=" + isDeleted +
                ", isEnable=" + isEnable +
                ", page=" + page +
                ", pageSize=" + pageSize +
                ", orderBy='" + orderBy + '\'' +
                ", isAsc=" + isAsc +
                '}';
    }
}