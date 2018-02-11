package com.qibao.activity.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_box")
public class BoxEO extends BaseEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 宝箱表编号
     */
    @Column(name = "box_no")
    private String boxNo;

    /**
     * 游戏no
     */
    @Column(name = "game_no")
    private String gameNo;

    /**
     * 宝箱名称
     */
    @Column(name = "box_name")
    private String boxName;

    /**
     * 支付数量
     */
    @Column(name = "box_num")
    private Double boxNum;

    /**
     * 支付类型
     */
    @Column(name = "box_type")
    private Integer boxType;

    /**
     * 支付单位
     */
    @Column(name = "box_unit")
    private String boxUnit;

    /**
     * 宝箱人气
     */
    @Column(name = "box_popularity")
    private Integer boxPopularity;

    /**
     * 宝箱今天人气
     */
    @Column(name = "box_popularity_today")
    private Integer boxPopularityToday;

    /**
     * 宝箱金币池系数
     */
    @Column(name = "box_gold_coefficient")
    private Double boxGoldCoefficient;

    /**
     * 宝箱金币池
     */
    @Column(name = "box_gold_pond")
    private Double boxGoldPond;

    /**
     * 宝箱金币池最大阀值
     */
    @Column(name = "box_gold_pond_max")
    private Double boxGoldPondMax;

    /**
     * 宝箱金币池溢出值
     */
    @Column(name = "box_gold_pond_overflow")
    private Double boxGoldPondOverflow;

    /**
     * 限制连续中奖次数
     */
    @Column(name = "box_exceed_count")
    private Integer boxExceedCount;

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
     * 是否启用
     */
    @Column(name = "is_enable")
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
     * 获取宝箱表编号
     *
     * @return box_no - 宝箱表编号
     */
    public String getBoxNo() {
        return boxNo;
    }

    /**
     * 设置宝箱表编号
     *
     * @param boxNo 宝箱表编号
     */
    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    /**
     * 获取游戏no
     *
     * @return game_no - 游戏no
     */
    public String getGameNo() {
        return gameNo;
    }

    /**
     * 设置游戏no
     *
     * @param gameNo 游戏no
     */
    public void setGameNo(String gameNo) {
        this.gameNo = gameNo;
    }

    /**
     * 获取宝箱名称
     *
     * @return box_name - 宝箱名称
     */
    public String getBoxName() {
        return boxName;
    }

    /**
     * 设置宝箱名称
     *
     * @param boxName 宝箱名称
     */
    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    /**
     * 获取支付数量
     *
     * @return box_num - 支付数量
     */
    public Double getBoxNum() {
        return boxNum;
    }

    /**
     * 设置支付数量
     *
     * @param boxNum 支付数量
     */
    public void setBoxNum(Double boxNum) {
        this.boxNum = boxNum;
    }

    /**
     * 获取支付类型
     *
     * @return box_type - 支付类型
     */
    public Integer getBoxType() {
        return boxType;
    }

    /**
     * 设置支付类型
     *
     * @param boxType 支付类型
     */
    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    /**
     * 获取支付单位
     *
     * @return box_unit - 支付单位
     */
    public String getBoxUnit() {
        return boxUnit;
    }

    /**
     * 设置支付单位
     *
     * @param boxUnit 支付单位
     */
    public void setBoxUnit(String boxUnit) {
        this.boxUnit = boxUnit;
    }

    /**
     * 获取宝箱人气
     *
     * @return box_popularity - 宝箱人气
     */
    public Integer getBoxPopularity() {
        return boxPopularity;
    }

    /**
     * 设置宝箱人气
     *
     * @param boxPopularity 宝箱人气
     */
    public void setBoxPopularity(Integer boxPopularity) {
        this.boxPopularity = boxPopularity;
    }

    /**
     * 获取宝箱今天人气
     *
     * @return box_popularity_today - 宝箱今天人气
     */
    public Integer getBoxPopularityToday() {
        return boxPopularityToday;
    }

    /**
     * 设置宝箱今天人气
     *
     * @param boxPopularityToday 宝箱今天人气
     */
    public void setBoxPopularityToday(Integer boxPopularityToday) {
        this.boxPopularityToday = boxPopularityToday;
    }

    /**
     * 获取宝箱金币池系数
     *
     * @return box_gold_coefficient - 宝箱金币池系数
     */
    public Double getBoxGoldCoefficient() {
        return boxGoldCoefficient;
    }

    /**
     * 设置宝箱金币池系数
     *
     * @param boxGoldCoefficient 宝箱金币池系数
     */
    public void setBoxGoldCoefficient(Double boxGoldCoefficient) {
        this.boxGoldCoefficient = boxGoldCoefficient;
    }

    /**
     * 获取宝箱金币池
     *
     * @return box_gold_pond - 宝箱金币池
     */
    public Double getBoxGoldPond() {
        return boxGoldPond;
    }

    /**
     * 设置宝箱金币池
     *
     * @param boxGoldPond 宝箱金币池
     */
    public void setBoxGoldPond(Double boxGoldPond) {
        this.boxGoldPond = boxGoldPond;
    }

    /**
     * 获取宝箱金币池最大阀值
     *
     * @return box_gold_pond_max - 宝箱金币池最大阀值
     */
    public Double getBoxGoldPondMax() {
        return boxGoldPondMax;
    }

    /**
     * 设置宝箱金币池最大阀值
     *
     * @param boxGoldPondMax 宝箱金币池最大阀值
     */
    public void setBoxGoldPondMax(Double boxGoldPondMax) {
        this.boxGoldPondMax = boxGoldPondMax;
    }

    /**
     * 获取宝箱金币池溢出值
     *
     * @return box_gold_pond_overflow - 宝箱金币池溢出值
     */
    public Double getBoxGoldPondOverflow() {
        return boxGoldPondOverflow;
    }

    /**
     * 设置宝箱金币池溢出值
     *
     * @param boxGoldPondOverflow 宝箱金币池溢出值
     */
    public void setBoxGoldPondOverflow(Double boxGoldPondOverflow) {
        this.boxGoldPondOverflow = boxGoldPondOverflow;
    }

    /**
     * 获取限制连续中奖次数
     *
     * @return box_exceed_count - 限制连续中奖次数
     */
    public Integer getBoxExceedCount() {
        return boxExceedCount;
    }

    /**
     * 设置限制连续中奖次数
     *
     * @param boxExceedCount 限制连续中奖次数
     */
    public void setBoxExceedCount(Integer boxExceedCount) {
        this.boxExceedCount = boxExceedCount;
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