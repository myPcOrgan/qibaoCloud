package com.qibao.activity.entity;

import com.qibao.common.entity.BaseEntity;

import javax.persistence.*;
import java.util.Date;

@Table(name = "t_user_prize")
public class UserPrizeEO extends BaseEntity {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 关联ID(奖品id或者活动id)
     */
    @Column(name = "relate_id")
    private Long relateId;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 1.房间中奖 2 夺金中奖 3 开箱消耗金币4创建房间消耗金币
     */
    @Column(name = "prize_type")
    private Integer prizeType;

    /**
     * 获奖金币数
     */
    @Column(name = "win_gold")
    private Double winGold;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * 宝箱金币池
     */
    @Column(name = "box_gold_pond")
    private Double boxGoldPond;

    /**
     * 宝箱金币池溢出值
     */
    @Column(name = "box_gold_pond_overflow")
    private Double boxGoldPondOverflow;

    /**
     * 支付数量
     */
    @Column(name = "box_num")
    private Double boxNum;

    /**
     * 备注
     */
    @Column(name = "remark")
    private String remark;

    /**
     * 获取id
     *
     * @return id - id
     */
    public Long getId() {
        return id;
    }

    /**
     * 奖品单位
     */
    private String prizeUnit;

    /**
     * 设置id
     *
     * @param id id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取关联ID(奖品id或者活动id)
     *
     * @return relate_id - 关联ID(奖品id或者活动id)
     */
    public Long getRelateId() {
        return relateId;
    }

    /**
     * 设置关联ID(奖品id或者活动id)
     *
     * @param relateId 关联ID(奖品id或者活动id)
     */
    public void setRelateId(Long relateId) {
        this.relateId = relateId;
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
     * 获取1.房间中奖 2 夺金中奖 3 消耗金币
     *
     * @return prize_type - 1.房间中奖 2 夺金中奖 3 消耗金币
     */
    public Integer getPrizeType() {
        return prizeType;
    }

    /**
     * 设置1.房间中奖 2 夺金中奖 3 消耗金币
     *
     * @param prizeType 1.房间中奖 2 夺金中奖 3 消耗金币
     */
    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    /**
     * 获取获奖金币数
     *
     * @return win_gold - 获奖金币数
     */
    public Double getWinGold() {
        return winGold;
    }

    /**
     * 设置获奖金币数
     *
     * @param winGold 获奖金币数
     */
    public void setWinGold(Double winGold) {
        this.winGold = winGold;
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

    public String getPrizeUnit() {
        return prizeUnit;
    }

    public void setPrizeUnit(String prizeUnit) {
        this.prizeUnit = prizeUnit;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}