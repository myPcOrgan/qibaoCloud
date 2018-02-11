package com.qibao.activity.entity.dto;

public class PrizeDTO {

    private Long id;
    private Long boxId;
    private String prizeName;
    private String prizeNo;
    private Double prizeNum;
    private Integer prizeType;
    private String prizeUnit;
    private Double prizeProbability;
    private Long imgId;
    private Boolean isDeleted;
    private Boolean isEnable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getBoxId() {
        return boxId;
    }

    public void setBoxId(Long boxId) {
        this.boxId = boxId;
    }

    public String getPrizeName() {
        return prizeName;
    }

    public void setPrizeName(String prizeName) {
        this.prizeName = prizeName;
    }

    public String getPrizeNo() {
        return prizeNo;
    }

    public void setPrizeNo(String prizeNo) {
        this.prizeNo = prizeNo;
    }

    public Double getPrizeNum() {
        return prizeNum;
    }

    public void setPrizeNum(Double prizeNum) {
        this.prizeNum = prizeNum;
    }

    public Integer getPrizeType() {
        return prizeType;
    }

    public void setPrizeType(Integer prizeType) {
        this.prizeType = prizeType;
    }

    public String getPrizeUnit() {
        return prizeUnit;
    }

    public void setPrizeUnit(String prizeUnit) {
        this.prizeUnit = prizeUnit;
    }

    public Double getPrizeProbability() {
        return prizeProbability;
    }

    public void setPrizeProbability(Double prizeProbability) {
        this.prizeProbability = prizeProbability;
    }

    public Long getImgId() {
        return imgId;
    }

    public void setImgId(Long imgId) {
        this.imgId = imgId;
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
}
