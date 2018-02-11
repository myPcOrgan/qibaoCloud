package com.qibao.backend.api.backend.controller;

import com.qibao.backend.api.backend.service.IBackendFeign;
import com.qibao.backend.common.Constants;
import com.qibao.backend.common.CookieUtils;
import com.qibao.backend.model.*;
import com.qibao.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by 周黎钢 on 2018/1/24.
 */
@RestController
@RequestMapping("backend")
public class BackendController {
    @Autowired
    private IBackendFeign backendFeign;

    @RequestMapping(value = "findBackUsers", method = RequestMethod.POST)
    BaseResponse<UserVO> findBackUsers(@RequestBody BackendRequest request) {
        return backendFeign.findBackUsers(request);
    }

    @RequestMapping(value = "insertBackUser", method = RequestMethod.POST)
    BaseResponse insertBackUser(@RequestBody BackendRequest request) {
        return backendFeign.insertBackUser(request);
    }

    @RequestMapping(value = "updateBackUser", method = RequestMethod.POST)
    BaseResponse updateBackUser(@RequestBody BackendRequest request) {
        return backendFeign.updateBackUser(request);
    }

    @RequestMapping(value = "login", method = RequestMethod.POST)
    @ResponseBody
    BaseResponse login(@RequestBody BackendRequest request, HttpServletRequest httpRequest, HttpServletResponse response) {
        BaseResponse baseResponse = backendFeign.login(request);
        if (baseResponse.getCode().equals("00")) {
            HttpSession session = httpRequest.getSession();
            session.setAttribute(Constants.SERVICE_REQUEST_HEADER_ACCOUNT, request.getLoginAccount());
            CookieUtils.saveCookie(httpRequest, response);
        }
        return baseResponse;
    }

    @RequestMapping(value = "logout", method = RequestMethod.POST)
    BaseResponse logout() {
        BaseResponse baseResponse = new BaseResponse();
        baseResponse.setSuccess();
        baseResponse.setMessage("退出登录成功");
        return baseResponse;
    }

    /**
     * 添加角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertRole", method = RequestMethod.POST)
    BaseResponse insertRole(@RequestBody BackendRequest request) {
        return backendFeign.insertRole(request);
    }

    /**
     * 修改角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    BaseResponse updateRole(@RequestBody BackendRequest request) {
        return backendFeign.updateRole(request);
    }

    /**
     * 查找角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findRoles", method = RequestMethod.POST)
    BaseResponse<RoleVO> findRoles(@RequestBody BackendRequest request) {
        return backendFeign.findRoles(request);
    }

    /**
     * 给用户分配角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertUserRole", method = RequestMethod.POST)
    BaseResponse insertUserRole(@RequestBody BackendRequest request) {
        return backendFeign.insertUserRole(request);
    }

    /**
     * 给用户批量分配角色
     * @param request
     * @return
     */
    @RequestMapping(value = "batchInsertUserRoles", method = RequestMethod.POST)
    BaseResponse batchInsertUserRoles(@RequestBody BackendRequest request) {
        return backendFeign.batchInsertUserRoles(request);
    }

    /**
     * 查找用户的角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findUserRoles", method = RequestMethod.POST)
    BaseResponse<UserRoleVO> findUserRoles(@RequestBody BackendRequest request) {
        return backendFeign.findUserRoles(request);
    }

    /**
     * 更新用户的角色
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateUserRole", method = RequestMethod.POST)
    BaseResponse updateUserRole(@RequestBody BackendRequest request) {
        return backendFeign.updateUserRole(request);
    }

    /**
     * 插入权限页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertFunction", method = RequestMethod.POST)
    BaseResponse insertFunction(@RequestBody BackendRequest request) {
        return backendFeign.insertFunction(request);
    }

    /**
     * 更新权限页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateFunction", method = RequestMethod.POST)
    BaseResponse updateFunction(@RequestBody BackendRequest request) {
        return backendFeign.updateFunction(request);
    }

    /**
     * 查找后台的权限页面
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findFunctions", method = RequestMethod.POST)
    BaseResponse<FunctionVO> findFunctions(@RequestBody BackendRequest request) {
        return backendFeign.findFunctions(request);
    }

    /**
     * 分配角色的权限
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "insertRoleFunction", method = RequestMethod.POST)
    BaseResponse insertRoleFunction(@RequestBody BackendRequest request) {
        return backendFeign.insertRoleFunction(request);
    }

    /**
     * 给角色批量添加权限成功
     * @param request
     * @return
     */
    @RequestMapping(value = "batchInsertRoleFunctions", method = RequestMethod.POST)
    BaseResponse batchInsertRoleFunctions(@RequestBody BackendRequest request) {
        return backendFeign.batchInsertRoleFunctions(request);
    }

    /**
     * 更改角色的权限
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "updateRoleFunction", method = RequestMethod.POST)
    BaseResponse updateRoleFunction(@RequestBody BackendRequest request) {
        return backendFeign.updateRoleFunction(request);
    }

    /**
     * 查找角色的权限
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "findRoleFunctions", method = RequestMethod.POST)
    BaseResponse<RoleFunctionVO> findRoleFunctions(@RequestBody BackendRequest request) {
        return backendFeign.findRoleFunctions(request);
    }
}
