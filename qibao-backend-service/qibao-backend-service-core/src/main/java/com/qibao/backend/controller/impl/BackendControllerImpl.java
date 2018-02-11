package com.qibao.backend.controller.impl;

import com.qibao.backend.controller.IBackendController;
import com.qibao.backend.entity.*;
import com.qibao.backend.exception.BackendException;
import com.qibao.backend.model.*;
import com.qibao.backend.service.IBackendService;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.BeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

/**
 * Created by 周黎钢 on 2018/1/24.
 */
@RestController
@RequestMapping(value = "backService")
public class BackendControllerImpl implements IBackendController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private IBackendService backendService;

    @Override
    @RequestMapping(value = "findBackUsers", method = RequestMethod.POST)
    public BaseResponse findBackUsers(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        List<UserEO> userEOs = backendService.findBackUsers(request);
        if (request.getIsService()) {
            List<ServiceVO> serviceVOs = BeanMapper.mapList(userEOs, ServiceVO.class);
            response.setData(serviceVOs);
            response.setTotalCount(serviceVOs.size());
        } else {
            List<UserVO> userVOs = BeanMapper.mapList(userEOs, UserVO.class);
            response.setData(userVOs);
            response.setTotalCount(userVOs.size());
        }
        logger.info("查找后台用户成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("查找后台用户成功");
        return response;
    }

    @Override
    @RequestMapping(value = "insertBackUser", method = RequestMethod.POST)
    public BaseResponse insertBackUser(@RequestBody BackendRequest request) {
        BaseResponse<UserVO> response = new BaseResponse<>();
        backendService.insertUser(request);
        logger.info("添加后台用户成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("添加后台用户成功");
        return response;
    }

    @Override
    @RequestMapping(value = "updateBackUser", method = RequestMethod.POST)
    public BaseResponse updateBackUser(@RequestBody BackendRequest request) {
        BaseResponse<UserVO> response = new BaseResponse<>();
        backendService.updateUser(request);
        logger.info("更新后台用户数据成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("更新后台用户数据成功");
        return response;
    }

    @Override
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public BaseResponse<UserEO> login(@RequestBody BackendRequest request) {
        BaseResponse<UserEO> response = new BaseResponse<>();
        Boolean success = backendService.loginCheck(request);
        if (success) {
            logger.info("登录成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
            response.setSuccess();
            response.setMessage("登录成功");
        } else {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.LOGIN_FAILED);
        }
        return response;
    }

    @Override
    @RequestMapping(value = "insertRole", method = RequestMethod.POST)
    public BaseResponse insertRole(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.insertRole(request);
        logger.info("添加角色成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("添加角色成功");
        return response;
    }

    @Override
    @RequestMapping(value = "insertUserRole", method = RequestMethod.POST)
    public BaseResponse insertUserRole(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.insertUserRole(request);
        logger.info("分配角色成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("分配角色成功");
        return response;
    }

    @Override
    @RequestMapping(value = "updateRole", method = RequestMethod.POST)
    public BaseResponse updateRole(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.updateRole(request);
        logger.info("更新角色成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("更新角色成功");
        return response;
    }

    @Override
    @RequestMapping(value = "findRoles", method = RequestMethod.POST)
    public BaseResponse<RoleVO> findRoles(@RequestBody BackendRequest request) {
        BaseResponse<RoleVO> response = new BaseResponse<>();
        List<RoleEO> roleEOs = backendService.findBackRoles(request);
        List<RoleVO> roleVOs = BeanMapper.mapList(roleEOs, RoleVO.class);
        logger.info("查找角色成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setData(roleVOs);
        response.setSuccess();
        response.setMessage("查找角色成功");
        return response;
    }

    @Override
    @RequestMapping(value = "batchInsertUserRoles", method = RequestMethod.POST)
    public BaseResponse batchInsertUserRoles(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.batchInsertUserRoles(request);
        logger.info("给用户批量添加角色成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("给用户批量添加角色成功");
        return response;
    }

    @Override
    @RequestMapping(value = "findUserRoles", method = RequestMethod.POST)
    public BaseResponse<UserRoleVO> findUserRoles(@RequestBody BackendRequest request) {
        BaseResponse<UserRoleVO> response = new BaseResponse<>();
        List<UserWithRoleNameEO> userRoleEOs = backendService.findBackUserRoles(request);
        List<UserRoleVO> userRoleVOs = BeanMapper.mapList(userRoleEOs, UserRoleVO.class);
        logger.info("查找用户角色成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setData(userRoleVOs);
        response.setSuccess();
        response.setMessage("查找用户角色成功");
        return response;
    }

    @Override
    @RequestMapping(value = "updateUserRole", method = RequestMethod.POST)
    public BaseResponse updateUserRole(@RequestBody BackendRequest request) {
        BaseResponse<UserRoleEO> response = new BaseResponse<>();
        backendService.updateUserRole(request);
        logger.info("更新用户角色成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("更新用户角色成功");
        return response;
    }

    @Override
    @RequestMapping(value = "insertFunction", method = RequestMethod.POST)
    public BaseResponse insertFunction(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.insertFunction(request);
        logger.info("添加权限成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("添加权限成功");
        return response;
    }

    @Override
    @RequestMapping(value = "updateFunction", method = RequestMethod.POST)
    public BaseResponse updateFunction(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.updateFunction(request);
        logger.info("修改权限成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("修改权限成功");
        return response;
    }

    @Override
    @RequestMapping(value = "findFunctions", method = RequestMethod.POST)
    public BaseResponse<FunctionVO> findFunctions(@RequestBody BackendRequest request) {
        BaseResponse<FunctionVO> response = new BaseResponse<>();
        List<FunctionEO> functionEOs = backendService.findBackFunctions(request);
        List<FunctionVO> functionVOs = BeanMapper.mapList(functionEOs, FunctionVO.class);
        logger.info("查找权限成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setData(functionVOs);
        response.setSuccess();
        response.setMessage("查找权限成功");
        return response;
    }

    @Override
    @RequestMapping(value = "insertRoleFunction", method = RequestMethod.POST)
    public BaseResponse insertRoleFunction(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.insertRoleFunction(request);
        logger.info("添加角色权限成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("添加角色权限成功");
        return response;
    }

    @Override
    @RequestMapping(value = "batchInsertRoleFunctions", method = RequestMethod.POST)
    public BaseResponse batchInsertRoleFunctions(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.batchInsertRoleFunctions(request);
        logger.info("给角色批量添加权限成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("给角色批量添加权限成功");
        return response;
    }

    @Override
    @RequestMapping(value = "updateRoleFunction", method = RequestMethod.POST)
    public BaseResponse updateRoleFunction(@RequestBody BackendRequest request) {
        BaseResponse response = new BaseResponse<>();
        backendService.updateRoleFunction(request);
        logger.info("修改角色权限成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setSuccess();
        response.setMessage("修改角色权限成功");
        return response;
    }

    @Override
    @RequestMapping(value = "findRoleFunctions", method = RequestMethod.POST)
    public BaseResponse<RoleFunctionVO> findRoleFunctions(@RequestBody BackendRequest request) {
        BaseResponse<RoleFunctionVO> response = new BaseResponse<>();
        List<RoleWithFuncNameEO> roleFunctionEOs = backendService.findBackRoleFunctions(request);
        List<RoleFunctionVO> roleFunctionVOs = BeanMapper.mapList(roleFunctionEOs, RoleFunctionVO.class);
        logger.info("查找角色权限成功，时间：{}，参数：{}", new Timestamp(System.currentTimeMillis()), request);
        response.setData(roleFunctionVOs);
        response.setSuccess();
        response.setMessage("查找角色权限成功");
        return response;
    }
}
