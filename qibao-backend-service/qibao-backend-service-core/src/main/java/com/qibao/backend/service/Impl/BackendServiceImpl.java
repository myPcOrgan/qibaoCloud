package com.qibao.backend.service.Impl;

import com.qibao.backend.entity.*;
import com.qibao.backend.exception.BackendException;
import com.qibao.backend.mapper.*;
import com.qibao.backend.model.BackendRequest;
import com.qibao.backend.redis.IBackendRedisDao;
import com.qibao.backend.service.IBackendService;
import com.qibao.common.service.abs.BaseService;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.IpUtil;
import com.qibao.common.utils.PassWordHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by 周黎钢 on 2018/1/22.
 */
@Service
public class BackendServiceImpl extends BaseService<UserEO> implements IBackendService {
    @Autowired
    private UserEOMapper userEOMapper;
    @Autowired
    private UserRoleEOMapper userRoleEOMapper;
    @Autowired
    private RoleEOMapper roleEOMapper;
    @Autowired
    private FunctionEOMapper functionEOMapper;
    @Autowired
    private RoleFunctionEOMapper roleFunctionEOMapper;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @Autowired
    private IBackendRedisDao backendRedisDao;

    @Override
    public void insertUser(BackendRequest request) {
        if (StringUtils.isBlank(request.getLoginAccount())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.LOGIN_ACCOUNT_NOT_NULL);
        }
        if (StringUtils.isBlank(request.getPassword())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.PASSWORD_NOT_NULL);
        }
        if (StringUtils.isBlank(request.getRealName())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.REAL_NAME_NOT_NULL);
        }
        UserEO userEO = new UserEO();
        if (request.getIsService()) {
            if (StringUtils.isBlank(request.getServiceQq())) {
                throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.SERVICE_QQ_NOT_NULL);
            }
            if (StringUtils.isBlank(request.getServiceName())) {
                throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.SERVICE_NAME_NOT_NULL);
            }
            userEO.setServiceQq(request.getServiceQq());
            userEO.setServiceName(request.getServiceName());
        }
        if (StringUtils.isNotBlank(request.getPhoneNumber())) {
            userEO.setPhoneNumber(request.getPhoneNumber());
        }
        if (StringUtils.isNotBlank(request.getEmail())) {
            userEO.setEmail(request.getEmail());
        }
        userEO.setLoginAccount(request.getLoginAccount());
        userEO.setPassword(request.getPassword());
        userEO.setRealName(request.getRealName());
        userEO.setPassword(PassWordHelper.encyptPassword(request.getPassword(), request.getLoginAccount()));
        userEO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        userEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        userEO.setIsDeleted(false);
        userEO.setIsEnable(true);
        this.insert(userEO);
    }

    @Override
    public void updateUser(BackendRequest request) {
        if (request.getId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ID_NOT_NULL);
        }
        if (request.getIsDeleted() != null && !request.getIsDeleted()) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.IS_DELETED);
        }
        UserEO userEO = new UserEO();
        BeanMapper.copyPropertiesIgnoreNull(request, userEO);
        userEO.setPassword(PassWordHelper.encyptPassword(request.getPassword(), request.getLoginAccount()));
        userEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        userEOMapper.updateByPrimaryKeySelective(userEO);
    }

    @Override
    public UserEO getUserById(Long id) {
        UserEO userEO = this.selectById(id);
        if (userEO.getIsDeleted() != null && userEO.getIsDeleted()) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.IS_DELETED);
        }
        return userEO;
    }

    @Override
    public List<UserEO> findBackUsers(BackendRequest request) {
        if (request.getIsService() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.IS_SERVICE_NOT_NULL);
        }
        Map map = BeanMapper.map(request, Map.class);
        map.put("orderBy", "create_time");
        map.put("order", "desc");
        map.put("isDeleted", false);
        if (request.getIsAsc() != null && request.getIsAsc()) {
            map.put("order", "asc");
        }
        if (request.getPage() != null && request.getPageSize() != null) {
            Integer start = (request.getPage() - 1) * request.getPageSize();
            map.put("start", start);
        }
        if (request.getIsService()) {
            //客服的ID
            map.put("is_service", "isService");
        }
        List<UserEO> userEOs = userEOMapper.selectWithRole(map);
        return userEOs;
    }

    @Override
    public Boolean loginCheck(BackendRequest request) {
        if (StringUtils.isBlank(request.getLoginAccount())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.LOGIN_ACCOUNT_NOT_NULL);
        }
        if (StringUtils.isBlank(request.getPassword())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.PASSWORD_NOT_NULL);
        }
        UserEO user = new UserEO();
        user.setLoginAccount(request.getLoginAccount());
        UserEO userEO = this.selectOne(user);
        String password = userEO.getPassword();
        Boolean success = PassWordHelper.verifyPassword(request.getPassword(), request.getLoginAccount(), password);
        if (success) {
            userEO.setLastLoginTime(new Timestamp(System.currentTimeMillis()));
            userEO.setLastLoginIp(IpUtil.getIpAddr(httpServletRequest));
            System.out.println(userEO.getLoginAccount() + "登录成功，IP：" + userEO.getLastLoginIp());
            this.update(userEO);
        }
        return success;
    }

    @Override
    public void insertRole(BackendRequest request) {
        if (StringUtils.isBlank(request.getRoleName())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ROLE_NAME_NOT_NULL);
        }
        if (StringUtils.isBlank(request.getRoleDesc())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ROLE_DESC_NOT_NULL);
        }
        RoleEO roleEO = new RoleEO();
        roleEO.setRoleName(request.getRoleName());
        roleEO.setRoleDesc(request.getRoleDesc());
        roleEO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        roleEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        roleEO.setIsDeleted(false);
        roleEOMapper.insert(roleEO);
    }

    @Override
    public void updateRole(BackendRequest request) {
        if (request.getId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ID_NOT_NULL);
        }
        if (request.getIsDeleted() != null && !request.getIsDeleted()) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.IS_DELETED);
        }
        RoleEO roleEO = new RoleEO();
        BeanMapper.copyPropertiesIgnoreNull(request, roleEO);
        roleEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        roleEOMapper.updateByPrimaryKeySelective(roleEO);
    }

    @Override
    public List<RoleEO> findBackRoles(BackendRequest request) {
        if (request.getIsDeleted() != null && request.getIsDeleted()) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.IS_DELETED);
        }
        RoleEO roleEO = new RoleEO();
        BeanMapper.copyPropertiesIgnoreNull(request, roleEO);
        roleEO.setIsDeleted(false);
        return roleEOMapper.select(roleEO);
    }

    @Override
    public void insertFunction(BackendRequest request) {
        if (StringUtils.isBlank(request.getFunctionName())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.FUNCTION_NAME_NOT_NULL);
        }
        if (StringUtils.isBlank(request.getFunctionDesc())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.FUNCTION_DESC_NOT_NULL);
        }
        if (StringUtils.isBlank(request.getUri())) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.URI_NOT_NULL);
        }
        if (request.getParentId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.PARENT_ID_NOT_NULL);
        }
        FunctionEO functionEO = new FunctionEO();
        functionEO.setFunctionName(request.getFunctionName());
        functionEO.setFunctionDesc(request.getFunctionDesc());
        functionEO.setUri(request.getUri());
        functionEO.setParentId(request.getParentId());
        functionEO.setCreateTime(new Timestamp(System.currentTimeMillis()));
        functionEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        functionEO.setIsDeleted(false);
        functionEO.setIsEnable(true);
        functionEOMapper.insert(functionEO);
        List<FunctionEO> functionEOs = backendRedisDao.getBackendFunctions();
        //第一次添加权限时同步数据库数据到redis
        if (functionEOs == null || functionEOs.size() == 0) {
            FunctionEO functionEO1 = new FunctionEO();
            functionEO1.setIsDeleted(false);
            functionEOs = functionEOMapper.select(functionEO);
        } else {
            functionEOs.add(functionEO);
        }
        backendRedisDao.setBackendFunctions(functionEOs);
    }

    @Override
    public void updateFunction(BackendRequest request) {
        if (request.getId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ID_NOT_NULL);
        }
        if (request.getIsDeleted() != null && !request.getIsDeleted()) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.IS_DELETED);
        }
        FunctionEO functionEO = new FunctionEO();
        BeanMapper.copyPropertiesIgnoreNull(request, functionEO);
        functionEO.setLastUpdateTime(new Timestamp(System.currentTimeMillis()));
        functionEOMapper.updateByPrimaryKeySelective(functionEO);
        List<FunctionEO> functionEOs = backendRedisDao.getBackendFunctions();
        if (functionEOs == null || functionEOs.size() == 0) {
            FunctionEO functionEO1 = new FunctionEO();
            functionEO1.setIsDeleted(false);
            functionEOs = functionEOMapper.select(functionEO);
        }
        List<FunctionEO> result=new ArrayList<>(functionEOs);
        for(FunctionEO functionEO1:functionEOs){
            if(functionEO1.getId().equals(functionEO.getId())){
                result.remove(functionEO1);
                result.add(functionEO);
            }
        }
        backendRedisDao.setBackendFunctions(result);
    }

    @Override
    public List<FunctionEO> findBackFunctions(BackendRequest request) {
        if (request.getIsDeleted() != null && request.getIsDeleted()) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.IS_DELETED);
        }
        List<FunctionEO> functionEOs = backendRedisDao.getBackendFunctions();
        if (functionEOs == null || functionEOs.size() == 0) {
            FunctionEO functionEO = new FunctionEO();
            functionEO.setIsDeleted(false);
            functionEOs = functionEOMapper.select(functionEO);
            backendRedisDao.setBackendFunctions(functionEOs);
            if (request == null) {
                return functionEOs;
            }
        }
        List<FunctionEO> result = new LinkedList<>();
        //根据request中的条件筛选结果
        for (FunctionEO functionEO : functionEOs) {
            BeanMapper.getSamePropertyValuesToList(request, functionEO, result);
        }
        return result;
    }

    @Override
    public void insertUserRole(BackendRequest request) {
        if (request.getUserId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.USER_ID_NOT_NULL);
        }
        if (request.getRoleId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ROLE_ID_NOT_NULL);
        }
        UserRoleEO userRoleEO = new UserRoleEO();
        userRoleEO.setRoleId(request.getRoleId());
        userRoleEO.setUserId(request.getUserId());
        userRoleEOMapper.insert(userRoleEO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertUserRoles(BackendRequest request) {
        if (request.getUserId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.USER_ID_NOT_NULL);
        }
        if (request.getRoleIds() == null || request.getRoleIds().size() == 0) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ROLE_IDS_NOT_NULL);
        }
        List<UserRoleEO> userRoleEOs = new LinkedList<>();
        for (Long roleId : request.getRoleIds()) {
            if (roleId == null) {
                throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ROLE_ID_NOT_NULL);
            }
            UserRoleEO userRoleEO = new UserRoleEO();
            userRoleEO.setUserId(request.getUserId());
            userRoleEO.setRoleId(roleId);
            userRoleEOs.add(userRoleEO);
        }
        UserRoleEO userRoleEO = new UserRoleEO();
        userRoleEO.setUserId(request.getUserId());
        userRoleEOMapper.delete(userRoleEO);
        userRoleEOMapper.batchInsertUserRoles(userRoleEOs);
    }

    @Override
    public void updateUserRole(BackendRequest request) {
        if (request.getId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ID_NOT_NULL);
        }
        UserRoleEO userRoleEO = new UserRoleEO();
        BeanMapper.copyPropertiesIgnoreNull(request, userRoleEO);
        userRoleEOMapper.updateByPrimaryKeySelective(userRoleEO);
    }

    @Override
    public List<UserWithRoleNameEO> findBackUserRoles(BackendRequest request) {
        Map map = BeanMapper.map(request, Map.class);
        return userRoleEOMapper.findUserRoles(map);
    }

    @Override
    public void insertRoleFunction(BackendRequest request) {
        if (request.getRoleId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ROLE_ID_NOT_NULL);
        }
        if (request.getFunctionId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.FUNCTION_ID_NOT_NULL);
        }
        RoleFunctionEO roleFunctionEO = new RoleFunctionEO();
        roleFunctionEO.setRoleId(request.getRoleId());
        roleFunctionEO.setFunctionId(request.getFunctionId());
        roleFunctionEOMapper.insert(roleFunctionEO);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void batchInsertRoleFunctions(BackendRequest request) {
        if (request.getRoleId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.USER_ID_NOT_NULL);
        }
        if (request.getFunctionIds() == null || request.getFunctionIds().size() == 0) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.FUNCTION_IDS_NOT_NULL);
        }
        List<RoleFunctionEO> roleFunctionEOs = new LinkedList<>();
        for (Long functionId : request.getFunctionIds()) {
            if (functionId == null) {
                throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ROLE_ID_NOT_NULL);
            }
            RoleFunctionEO roleFunctionEO = new RoleFunctionEO();
            roleFunctionEO.setRoleId(request.getRoleId());
            roleFunctionEO.setFunctionId(functionId);
            roleFunctionEOs.add(roleFunctionEO);
        }
        RoleFunctionEO roleFunctionEO = new RoleFunctionEO();
        roleFunctionEO.setRoleId(request.getRoleId());
        roleFunctionEOMapper.delete(roleFunctionEO);
        roleFunctionEOMapper.batchInsertRoleFunctions(roleFunctionEOs);
    }

    @Override
    public void updateRoleFunction(BackendRequest request) {
        if (request.getId() == null) {
            throw new BackendException(BackendException.COMMON_EXCEPTION, BackendException.ID_NOT_NULL);
        }
        RoleFunctionEO roleFunctionEO = new RoleFunctionEO();
        BeanMapper.copyPropertiesIgnoreNull(request, roleFunctionEO);
        roleFunctionEOMapper.updateByPrimaryKeySelective(roleFunctionEO);
    }

    @Override
    public List<RoleWithFuncNameEO> findBackRoleFunctions(BackendRequest request) {
        Map map = BeanMapper.map(request, Map.class);
        return roleFunctionEOMapper.findRoleFunctions(map);
    }
}
