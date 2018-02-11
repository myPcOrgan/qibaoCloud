package com.qibao.goods.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.Column;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "t_category")
public class CategoryEO extends BaseEntity{
    /**
     * 分类code,1表示区服，2地下城游戏币
     */
    @Column(name = "cate_code")
    private String cateCode;

    /**
     * 名称
     */
    @Column(name = "cate_name")
    private String cateName;

    /**
     * 商品单位
     */
    @Column(name = "goods_unit")
    private String goodsUnit;

    /**
     * 层级id,记录父级的分类ID，把所有父级的ID用逗号拼接成字符串，当层级为0时该字段为空字符串
     */
    @Column(name = "level_id")
    private String levelId;

    /**
     * 层级，默认为0，代表最上层父级,1为下一级，2为下下一级，依次类推
     */
    @Column(name = "cate_level")
    private Integer cateLevel;

    /**
     * 父类别，当层级为0时该字段为空
     */
    @Column(name = "parent_id")
    private Long parentId;

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
     * false是未删除true是已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * false是禁用，true是启用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

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

    @Override
    public String toString() {
        return "CategoryEO{" +
                "id=" + getId() +
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
                '}';
    }
}