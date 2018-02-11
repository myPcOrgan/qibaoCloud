package com.qibao.activity.entity.vo;

import java.util.Date;

public class UserPrizeRecentlyVO {
    /**
     * id
     */
    private Long id;

    /**
     * 关联ID(奖品id或者活动id)
     */
    private Long relateId;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 1.房间中奖 2 夺金中奖 3 消耗金币
     */
    private int prizeType;

    /**
     * 获奖金币数
     */
    private Double winGold;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String userImg;

    /**
     * 奖品单位
     */
    private String prizeUnit;

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
    public int getPrizeType() {
        return prizeType;
    }

    /**
     * 设置1.房间中奖 2 夺金中奖 3 消耗金币
     *
     * @param prizeType 1.房间中奖 2 夺金中奖 3 消耗金币
     */
    public void setPrizeType(int prizeType) {
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

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getUserImg() {
        return userImg;
    }

    public void setUserImg(String userImg) {
        this.userImg = userImg;
    }

    public String getPrizeUnit() {
        return prizeUnit;
    }

    public void setPrizeUnit(String prizeUnit) {
        this.prizeUnit = prizeUnit;
    }
}