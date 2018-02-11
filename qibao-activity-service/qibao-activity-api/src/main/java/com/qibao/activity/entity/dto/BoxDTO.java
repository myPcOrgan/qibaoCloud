package com.qibao.activity.entity.dto;

public class BoxDTO {

    private Long id;
    private String boxName;
    private String boxNo;
    private String gameNo;
    private Double boxNum;
    private Integer boxType;
    private String boxUnit;
    private Integer boxPopularity;
    private Integer boxPopularityToday;
    private Double boxGoldCoefficient;
    private Double boxGoldPondMax;
    private Integer boxExceedCount;
    private Long imgId;
    private Boolean isDeleted;
    private Boolean isEnable;
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBoxName() {
        return boxName;
    }

    public void setBoxName(String boxName) {
        this.boxName = boxName;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getGameNo() {
        return gameNo;
    }

    public void setGameNo(String gameNo) {
        this.gameNo = gameNo;
    }

    public Double getBoxNum() {
        return boxNum;
    }

    public void setBoxNum(Double boxNum) {
        this.boxNum = boxNum;
    }

    public Integer getBoxType() {
        return boxType;
    }

    public void setBoxType(Integer boxType) {
        this.boxType = boxType;
    }

    public String getBoxUnit() {
        return boxUnit;
    }

    public void setBoxUnit(String boxUnit) {
        this.boxUnit = boxUnit;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
    }

    public Double getBoxGoldCoefficient() {
        return boxGoldCoefficient;
    }

    public void setBoxGoldCoefficient(Double boxGoldCoefficient) {
        this.boxGoldCoefficient = boxGoldCoefficient;
    }

    public Double getBoxGoldPondMax() {
        return boxGoldPondMax;
    }

    public void setBoxGoldPondMax(Double boxGoldPondMax) {
        this.boxGoldPondMax = boxGoldPondMax;
    }

    public Integer getBoxExceedCount() {
        return boxExceedCount;
    }

    public void setBoxExceedCount(Integer boxExceedCount) {
        this.boxExceedCount = boxExceedCount;
    }

    public Integer getBoxPopularity() {
        return boxPopularity;
    }

    public void setBoxPopularity(Integer boxPopularity) {
        this.boxPopularity = boxPopularity;
    }

    public Integer getBoxPopularityToday() {
        return boxPopularityToday;
    }

    public void setBoxPopularityToday(Integer boxPopularityToday) {
        this.boxPopularityToday = boxPopularityToday;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }

    public Boolean getEnable() {
        return isEnable;
    }

    public void setEnable(Boolean enable) {
        isEnable = enable;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
