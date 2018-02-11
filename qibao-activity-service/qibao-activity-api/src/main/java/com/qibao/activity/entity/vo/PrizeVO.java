package com.qibao.activity.entity.vo;

import java.util.Date;

public class PrizeVO {
    /**
     * id
     */
    private Long id;

    /**
     * 奖品表编号
     */
    private String prizeNo;

    /**
     * 宝箱表id
     */
    private Long boxId;

    /**
     * 奖品名称
     */
    private String prizeName;

    /**
     * 奖品数量
     */
    private Double prizeNum;

    /**
     * 奖品类型
     */
    private Integer prizeType;

    /**
     * 奖品单位
     */
    private String prizeUnit;

    /**
     * 图片id
     */
    private Long imgId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 修改时间
     */
    private Date lastUpdateTime;

    /**
     * 0是否1是已删除
     */
    private Boolean isDeleted;

    /**
     * 是否启用
     */
    private Boolean isEnable;

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
     * 获取奖品表编号
     *
     * @return prize_no - 奖品表编号
     */
    public String getPrizeNo() {
        return prizeNo;
    }

    /**
     * 设置奖品表编号
     *
     * @param prizeNo 奖品表编号
     */
    public void setPrizeNo(String prizeNo) {
        this.prizeNo = prizeNo;
    }

    /**
     * 获取宝箱表id
     *
     * @return box_id - 宝箱表id
     */
    public Long getBoxId() {
        return boxId;
    }

    /**
     * 设置宝箱表id
     *
     * @param boxId 宝箱表id
     */
    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    /**
     * 获取奖品名称
     *
     * @return prize_name - 奖品名称
     */
    public String getPrizeName() {
        return prizeName;
    }

    /**
     * 设置奖品名称
     *
     * @param prizeName 奖品名称
     */
    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    /**
     * 获取奖品数量
     *
     * @return prize_num - 奖品数量
     */
    public Double getPrizeNum() {
        return prizeNum;
    }

    /**
     * 设置奖品数量
     *
     * @param prizeNum 奖品数量
     */
    public void setPrizeNum(Double prizeNum) {
        this.prizeNum = prizeNum;
    }

    /**
     * 获取奖品类型
     *
     * @return prize_type - 奖品类型
     */
    public Integer getPrizeType() {
        return prizeType;
    }

    /**
     * 设置奖品类型
     *
     * @param prizeType 奖品类型
     */
    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    /**
     * 获取奖品单位
     *
     * @return prize_unit - 奖品单位
     */
    public String getPrizeUnit() {
        return prizeUnit;
    }

    /**
     * 设置奖品单位
     *
     * @param prizeUnit 奖品单位
     */
    public void setPrizeUnit(String prizeUnit) {
        this.prizeUnit = prizeUnit;
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
}