package com.qibao.activity.entity.dto;

public class UserPrizeDTO {

    /**
     * 关联ID(奖品id或者活动id)
     */
    private Long relateId;
    private Long userId;
    /**
     * 1.房间中奖 2 夺金中奖 3 开箱消耗金币4创建房间消耗金币
     */
    private Integer prizeType;
    private Double winGold;
    private String prizeUnit;
    private Double boxGoldPond;
    private Double boxGoldPondOverflow;
    private Double boxNum;
    private String remark;
    private String beginDateStr;
    private String endDateStr;

    public Long getRelateId() {
        return relateId;
    }

    public void setRelateId(Long relateId) {
        this.relateId = relateId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Integer getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
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

    public Double getBoxGoldPond() {
        return boxGoldPond;
    }

    public void setBoxGoldPond(Double boxGoldPond) {
        this.boxGoldPond = boxGoldPond;
    }

    public Double getBoxGoldPondOverflow() {
        return boxGoldPondOverflow;
    }

    public void setBoxGoldPondOverflow(Double boxGoldPondOverflow) {
        this.boxGoldPondOverflow = boxGoldPondOverflow;
    }

    public Double getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Double boxNum) {
        this.boxNum = boxNum;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getBeginDateStr() {
        return beginDateStr;
    }

    public void setBeginDateStr(String beginDateStr) {
        this.beginDateStr = beginDateStr;
    }

    public String getEndDateStr() {
        return endDateStr;
    }

    public void setEndDateStr(String endDateStr) {
        this.endDateStr = endDateStr;
    }
}
