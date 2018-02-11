package com.qibao.user.context.dto;

/**
 * Created by 340067 on 2018/1/30.
 */
public class UpdateGoldInfoDTO {

    private Long userId;

    private Double goldNum;

    private Integer type;

    private String desp;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(Double goldNum) {
        this.goldNum = goldNum;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getDesp() {
        return desp;
    }

    public void setDesp(String desp) {
        this.desp = desp;
    }
}
