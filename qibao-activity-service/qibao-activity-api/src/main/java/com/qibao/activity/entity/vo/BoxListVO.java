package com.qibao.activity.entity.vo;

public class BoxListVO {
    /**
     * id
     */
    private Long id;

    /**
     * 游戏no
     */
    private String gameNo;

    /**
     * 宝箱名称
     */
    private String boxName;

    /**
     * 支付数量
     */
    private Double boxNum;

    /**
     * 支付类型
     */
    private Integer boxType;

    /**
     * 支付单位
     */
    private String boxUnit;

    /**
     * 图片id
     */
    private Long imgId;

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
}