package com.qibao.activity.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;

@Table(name = "t_image")
public class ImageEO extends BaseEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 游戏id
     */
    @Column(name = "img_name")
    private String imgName;

    /**
     * 游戏名称
     */
    @Column(name = "img_path")
    private String imgPath;

    /**
     * 创建时间
     */
    @Column(name = "img_remark")
    private String imgRemark;

    /**
     * 修改时间
     */
    @Column(name = "img_type")
    private Integer imgType;

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
     * 获取游戏id
     *
     * @return img_name - 游戏id
     */
    public String getImgName() {
        return imgName;
    }

    /**
     * 设置游戏id
     *
     * @param imgName 游戏id
     */
    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    /**
     * 获取游戏名称
     *
     * @return img_path - 游戏名称
     */
    public String getImgPath() {
        return imgPath;
    }

    /**
     * 设置游戏名称
     *
     * @param imgPath 游戏名称
     */
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    /**
     * 获取创建时间
     *
     * @return img_remark - 创建时间
     */
    public String getImgRemark() {
        return imgRemark;
    }

    /**
     * 设置创建时间
     *
     * @param imgRemark 创建时间
     */
    public void setImgRemark(String imgRemark) {
        this.imgRemark = imgRemark;
    }

    /**
     * 获取修改时间
     *
     * @return img_type - 修改时间
     */
    public Integer getImgType() {
        return imgType;
    }

    /**
     * 设置修改时间
     *
     * @param imgType 修改时间
     */
    public void setImgType(Integer imgType) {
        this.imgType = imgType;
    }
}