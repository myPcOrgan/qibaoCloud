package com.qibao.backend.entity;

/**
 * Created by 周黎钢 on 2018/2/3.
 */
public class RoleWithFuncNameEO {
    private Long id;

    private Long roleId;

    private Long functionId;

    private String functionName;
    /**
     * @return role_id
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * @param roleId
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * @return function_id
     */
    public Long getFunctionId() {
        return functionId;
    }

    /**
     * @param functionId
     */
    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

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
}
