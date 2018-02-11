package com.qibao.activity.entity.vo;

/**
 * 用户信息统计
 */
public class UserPrizeTotalVO {

    /**
     * 我创建的房间总数
     */
    private int createRoomNum;

    /**
     * 我加入的房间活动总数
     */
    private int joinRoomActivityNum;

    /**
     * 我中奖的活动总数
     */
    private int prizeActivityNum;

    /**
     * 我参加活动中奖的总金额
     */
    private double totalGold;

    public int getCreateRoomNum() {
        return createRoomNum;
    }

    public void setCreateRoomNum(int createRoomNum) {
        this.createRoomNum = createRoomNum;
    }

    public int getJoinRoomActivityNum() {
        return joinRoomActivityNum;
    }

    public void setJoinRoomActivityNum(int joinRoomActivityNum) {
        this.joinRoomActivityNum = joinRoomActivityNum;
    }

    public int getPrizeActivityNum() {
        return prizeActivityNum;
    }

    public void setPrizeActivityNum(int prizeActivityNum) {
        this.prizeActivityNum = prizeActivityNum;
    }

    public double getTotalGold() {
        return totalGold;
    }

    public void setTotalGold(double totalGold) {
        this.totalGold = totalGold;
    }
}
