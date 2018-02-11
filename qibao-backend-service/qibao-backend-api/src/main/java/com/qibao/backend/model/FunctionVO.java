package com.qibao.backend.model;

/**
 * Created by 周黎钢 on 2018/2/1.
 */
public class FunctionVO {
    private Long id;
    /**
     * 权限名字
     */
    private String functionName;

    /**
     * 权限路径
     */
    private String uri;

    /**
     * 权限描述
     */
    private String functionDesc;

    /**
     * 是否启用，1为启用
     */
    private Boolean isEnable;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getFunctionDesc() {
        return functionDesc;
    }

    public void setFunctionDesc(String functionDesc) {
        this.functionDesc = functionDesc;
    }
    /**
     * 获取是否启用，1为启用
     *
     * @return is_enable - 是否启用，1为启用
     */
    public Boolean getIsEnable() {
        return isEnable;
    }

    /**
     * 设置是否启用，1为启用
     *
     * @param isEnable 是否启用，1为启用
     */
    public void setIsEnable(Boolean isEnable) {
        this.isEnable = isEnable;
    }
}
