package com.qibao.goods.model;

/**
 * Created by 周黎钢 on 2018/2/1.
 */
public class CategoryVO {
    private Long id;
    /**
     * 分类code,1表示区服，2地下城游戏币
     */
    private String cateCode;

    /**
     * 名称
     */
    private String cateName;

    /**
     * 商品单位
     */
    private String goodsUnit;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCateCode() {
        return cateCode;
    }

    public void setCateCode(String cateCode) {
        this.cateCode = cateCode;
    }

    public String getCateName() {
        return cateName;
    }

    public void setCateName(String cateName) {
        this.cateName = cateName;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public void setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
    }
}
