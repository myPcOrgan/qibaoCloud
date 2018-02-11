package com.qibao.goods.entity;

import com.qibao.common.entity.BaseEntity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "t_sys_help")
public class SysHelpEO  extends BaseEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 分类
     */
    private Long cate;

    /**
     * 父id
     */
    @Column(name = "parent_id")
    private Long parentId;

    /**
     * 标题
     */
    private String title;

    /**
     * 副标题
     */
    @Column(name = "sub_title")
    private String subTitle;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片地址
     */
    @Column(name = "img_url")
    private String imgUrl;

    /**
     * 类型(1首页横幅，2帮助中心)
     */
    private Integer type;

    /**
     * 链接url
     */
    @Column(name = "link_url")
    private String linkUrl;

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
     * 排序（数字越大越靠前）
     */
    @Column(name = "list_order")
    private Integer listOrder;

    /**
     * 0是否1是已删除
     */
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    /**
     * 是否启用
     */
    @Column(name = "is_enable")
    private Boolean isEnable;

    /**
     * 备注（内容）
     */
    private String content;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取分类
     *
     * @return cate - 分类
     */
    public Long getCate() {
        return cate;
    }

    /**
     * 设置分类
     *
     * @param cate 分类
     */
    public void setCate(Long cate) {
        this.cate = cate;
    }

    /**
     * 获取父id
     *
     * @return parent_id - 父id
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 设置父id
     *
     * @param parentId 父id
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 获取标题
     *
     * @return title - 标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置标题
     *
     * @param title 标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取副标题
     *
     * @return sub_title - 副标题
     */
    public String getSubTitle() {
        return subTitle;
    }

    /**
     * 设置副标题
     *
     * @param subTitle 副标题
     */
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }

    /**
     * 获取名称
     *
     * @return name - 名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名称
     *
     * @param name 名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取图片地址
     *
     * @return img_url - 图片地址
     */
    public String getImgUrl() {
        return imgUrl;
    }

    /**
     * 设置图片地址
     *
     * @param imgUrl 图片地址
     */
    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    /**
     * 获取类型(1首页横幅，2帮助中心)
     *
     * @return type - 类型(1首页横幅，2帮助中心)
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置类型(1首页横幅，2帮助中心)
     *
     * @param type 类型(1首页横幅，2帮助中心)
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取链接url
     *
     * @return link_url - 链接url
     */
    public String getLinkUrl() {
        return linkUrl;
    }

    /**
     * 设置链接url
     *
     * @param linkUrl 链接url
     */
    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
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
     * 获取排序（数字越大越靠前）
     *
     * @return list_order - 排序（数字越大越靠前）
     */
    public Integer getListOrder() {
        return listOrder;
    }

    /**
     * 设置排序（数字越大越靠前）
     *
     * @param listOrder 排序（数字越大越靠前）
     */
    public void setListOrder(Integer listOrder) {
        this.listOrder = listOrder;
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
     * 获取是否启用
     *
     * @return is_enable - 是否启用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用
     *
     * @param isEnable 是否启用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }

    /**
     * 获取备注（内容）
     *
     * @return content - 备注（内容）
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置备注（内容）
     *
     * @param content 备注（内容）
     */
    public void setContent(String content) {
        this.content = content;
    }
}