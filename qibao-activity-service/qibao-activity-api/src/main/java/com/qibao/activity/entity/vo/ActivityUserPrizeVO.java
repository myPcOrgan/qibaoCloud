package com.qibao.activity.entity.vo;

/**
 * 房间活动中奖用户信息
 */
public class ActivityUserPrizeVO {

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 头像
     */
    private String userImg;

    /**
     * 获奖金币数
     */
    private Double winGold;

    /**
     * 奖品单位
     */
    private String prizeUnit;

    /**
     * 奖品对应人民币
     */
    private Double totalRmb;

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

    public Double getWinGold() {
        return winGold;
    }

    public void setWinGold(Double winGold) {
        this.winGold = winGold;
    }

    public String getPrizeUnit() {
        return prizeUnit;
    }

    public void setPrizeUnit(String prizeUnit) {
        this.prizeUnit = prizeUnit;
    }

    public Double getTotalRmb() {
        return totalRmb;
    }

    public void setTotalRmb(Double totalRmb) {
        this.totalRmb = totalRmb;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
