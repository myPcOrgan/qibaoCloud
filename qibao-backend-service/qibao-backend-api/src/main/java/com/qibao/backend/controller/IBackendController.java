package com.qibao.backend.controller;

import com.qibao.backend.model.*;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by 周黎钢 on 2018/1/24.
 */
@RestController
@RequestMapping(value = "backService")
public interface IBackendController {
    /**
     * 获取后台用户
     * 后台用户分两种，如果是客服返回的是ServiceVO，其他用户返回UserVO
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findBackUsers", method = RequestMethod.POST)
    BaseResponse findBackUsers(@RequestBody BackendRequest request);

    /**
     * 添加后台用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertBackUser", method = RequestMethod.POST)
    BaseResponse insertBackUser(@RequestBody BackendRequest request);

    /**
     * 更新后台用户
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateBackUser", method = RequestMethod.POST)
    BaseResponse updateBackUser(@RequestBody BackendRequest request);

    /**
     * 登录接口
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    BaseResponse login(@RequestBody BackendRequest request);

    /**
     * 添加角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertRole", method = RequestMethod.POST)
    BaseResponse insertRole(@RequestBody BackendRequest request);

    /**
     * 修改角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    BaseResponse updateRole(@RequestBody BackendRequest request);

    /**
     * 查找角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findRoles", method = RequestMethod.POST)
    BaseResponse<RoleVO> findRoles(@RequestBody BackendRequest request);

    /**
     * 给用户分配角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertUserRole", method = RequestMethod.POST)
    BaseResponse insertUserRole(@RequestBody BackendRequest request);

    /**
     * 给用户批量分配角色
     * @param request
     * @return
     */
    @RequestMapping(value = "batchInsertUserRoles", method = RequestMethod.POST)
    BaseResponse batchInsertUserRoles(@RequestBody BackendRequest request);

    /**
     * 查找用户的角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findUserRoles", method = RequestMethod.POST)
    BaseResponse<UserRoleVO> findUserRoles(@RequestBody BackendRequest request);

    /**
     * 更新用户的角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateUserRole", method = RequestMethod.POST)
    BaseResponse updateUserRole(@RequestBody BackendRequest request);

    /**
     * 插入权限页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertFunction", method = RequestMethod.POST)
    BaseResponse insertFunction(@RequestBody BackendRequest request);

    /**
     * 更新权限页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateFunction", method = RequestMethod.POST)
    BaseResponse updateFunction(@RequestBody BackendRequest request);

    /**
     * 查找后台的权限页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findFunctions", method = RequestMethod.POST)
    BaseResponse<FunctionVO> findFunctions(@RequestBody BackendRequest request);

    /**
     * 分配角色的权限
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertRoleFunction", method = RequestMethod.POST)
    BaseResponse insertRoleFunction(@RequestBody BackendRequest request);

    /**
     * 给角色批量添加权限成功
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "batchInsertRoleFunctions", method = RequestMethod.POST)
    BaseResponse batchInsertRoleFunctions(@RequestBody BackendRequest request);

    /**
     * 更改角色的权限
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateRoleFunction", method = RequestMethod.POST)
    BaseResponse updateRoleFunction(@RequestBody BackendRequest request);

    /**
     * 查找角色的权限
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findRoleFunctions", method = RequestMethod.POST)
    BaseResponse<RoleFunctionVO> findRoleFunctions(@RequestBody BackendRequest request);
}
