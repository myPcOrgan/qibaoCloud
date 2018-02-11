package com.qibao.user.controller.impl;

import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.ParamUtils;
import com.qibao.user.context.dto.UpdateGoldInfoDTO;
import com.qibao.user.context.dto.UserAccountDTO;
import com.qibao.user.context.enums.GoldTypeEnum;
import com.qibao.user.context.enums.UpdateUserTypeEnum;
import com.qibao.user.context.enums.UserGradeEnum;
import com.qibao.user.context.vo.UserAccountVO;
import com.qibao.user.context.service.IUserController;
import com.qibao.user.entity.UserAccountEO;
import com.qibao.user.exceptions.UserException;
import com.qibao.user.service.IUserService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 340067 on 2018/1/10.
 */
@RestController
@RequestMapping("user")
public class UserController extends BaseController implements IUserController{

    @Autowired
    private IUserService userService;
    

    /**
     * 获取用户的基本信息
     * @param userId
     * @return
     */
    @ApiOperation(value = "获取用户的基本信息", notes = "获取用户的基本信息")
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    public BaseResponse<UserAccountVO> getUserInfo(@RequestParam("userId") Long userId){
        BaseResponse<UserAccountVO> baseResponse = new BaseResponse<>();
        UserAccountEO userAccountEO;
        if (userId == null){
            throw new UserException(UserException.USERID_EMPTY);
        }
        userAccountEO = userService.getUserInfoById(userId);
        UserAccountVO userAccountVO = null;
        if (userAccountEO != null) {
            if (userAccountEO.getIsDeleted() == null || !userAccountEO.getIsDeleted()){
                userAccountVO = getUserAccountVO(userAccountEO);
            }
        }
        baseResponse.setResult(userAccountVO);

        return baseResponse;
    }


    /**
     * 修改用户基本信息，不包含QQ，微信
     * @param userAccountVO
     * @return
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户基本信息，不包含QQ，微信", notes = "修改用户基本信息，不包含QQ，微信")
    public BaseResponse<String> updateUserInfo(@RequestBody UserAccountVO userAccountVO){
        BaseResponse<String> baseResponse = new BaseResponse();
        if (userAccountVO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        UserAccountEO userAccountEO = getUserAccountEo(userAccountVO);
        if (userAccountEO.getId() == null){
            throw new UserException(UserException.USERID_EMPTY);
        }
        userAccountEO.setQqCode(null);
        userAccountEO.setQq(null);
        userAccountEO.setWechartCode(null);
        userAccountEO.setWechart(null);
        userService.updateUserInfo(userAccountEO);

        return baseResponse;

    }



    /**
     * 获取多个用户的基本信息
     * @param ids
     * @return
     */
    @ApiOperation(value = "获取多个用户的基本信息", notes = "获取多个用户的基本信息")
    @RequestMapping(value = "getUserInfoByIds", method = RequestMethod.GET)
    public BaseResponse<UserAccountVO> getUserInfoByIds(@RequestParam("ids") List<Long> ids){
        BaseResponse<UserAccountVO> baseResponse = new BaseResponse<>();
        if (CollectionUtils.isEmpty(ids)){
            throw new UserException(UserException.USERID_EMPTY);
        }
        List<UserAccountEO> userAccountEOS = userService.listUserInfoByIds(ids);
        List<UserAccountVO> userAccountVOS;
        if (!CollectionUtils.isEmpty(userAccountEOS)){
            userAccountVOS = BeanMapper.mapList(userAccountEOS, UserAccountVO.class);
        } else {
            userAccountVOS = new ArrayList<>();
        }
        baseResponse.setData(userAccountVOS);

        return baseResponse;
    }

    @Override
    @RequestMapping(value = "listUserAccount", method = RequestMethod.POST)
    @ApiOperation(value = "查询用户基本信息，分页", notes = "查询用户基本信息，分页")
    public BaseResponse<UserAccountVO> listUserAccount(@RequestBody UserAccountDTO userAccountDTO) {
        BaseResponse<UserAccountVO> baseResponse = new BaseResponse<>();
        if (userAccountDTO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }

        Map<String, Object> params = this.getMapByDTO(userAccountDTO);
        int num = userService.countByMap(params);
        List<UserAccountVO> userAccountVOs = new ArrayList<>();
        if (num != 0) {
            List<UserAccountEO> userAccountEOs = userService.listUserAccountByMap(params);
            if (!CollectionUtils.isEmpty(userAccountEOs)) {
                userAccountVOs = BeanMapper.mapList(userAccountEOs, UserAccountVO.class);
            }
        }
        baseResponse.setData(userAccountVOs);
        baseResponse.setTotalCount(num);
        baseResponse.setPageIndex(userAccountDTO.getPage() == null?0:userAccountDTO.getPage());
        baseResponse.setPageSize(userAccountDTO.getSize() == null?0:userAccountDTO.getSize());

        return baseResponse;
    }

    @Override
    @RequestMapping(value = "updateUserGoldInfos", method = RequestMethod.POST)
    @ApiOperation(value = "更新用户总金币信息", notes = "更新用户总金币信息")
    public BaseResponse<String> updateUserGoldInfos(@RequestBody UpdateGoldInfoDTO updateGoldInfoDTO) {
        BaseResponse<String> baseResponse = new BaseResponse();
        if (updateGoldInfoDTO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        Long userId = updateGoldInfoDTO.getUserId();
        Double goldNum = updateGoldInfoDTO.getGoldNum();
        Integer type = updateGoldInfoDTO.getType();
        String desp = updateGoldInfoDTO.getDesp();
        if (userId == null){
            throw new UserException(UserException.USERID_EMPTY);
        }
        if (type == null){
            throw new BaseException(01,"用户操作类型不能为空");
        }
        if (!type.equals(GoldTypeEnum.ADDTOTALGOLD.getCode()) &&
                !type.equals(GoldTypeEnum.REDUCETOTALGOLD.getCode()) &&
                !type.equals(GoldTypeEnum.ALLWINNUM.getCode()) &&
                !type.equals(GoldTypeEnum.ALLWINNUMNULL.getCode())){
            throw new BaseException(01,"用户操作类型错误");
        }
        if (goldNum == null){
            throw new BaseException(01,"金币数量不能为空");
        }
        if (goldNum < 0){
            throw new BaseException(01,"金币数量变化不能小于零");
        }
        if (StringUtils.isBlank(desp)){
            throw new BaseException(01,"描述信息不能为空");
        }
        userService.updateUserGold(userId, type, goldNum, desp);

        return baseResponse;
    }

    @Override
    @ApiOperation(value = "用户与房主启用，禁用，删除", notes = "用户与房主启用，禁用，删除")
    @RequestMapping(value = "updateUserByType", method = RequestMethod.GET)
    public BaseResponse<String> updateUserByType(@RequestParam("type") Integer type, @RequestParam("userId") Long userId) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        if (userId == null){
            throw new UserException(UserException.USERID_EMPTY);
        }
        if (type == null){
            throw new BaseException(01,"用户操作类型不能为空");
        }
        UserAccountEO userAccountEO = new UserAccountEO();
        userAccountEO.setId(userId);
        if (type.equals(UpdateUserTypeEnum.ENABLEUSER.getCode())){
            userAccountEO.setIsForbid(false);
        } else if (type.equals(UpdateUserTypeEnum.DISABLEUSER.getCode())){
            userAccountEO.setIsForbid(true);
        } else if (type.equals(UpdateUserTypeEnum.DELUSER.getCode())){
            userAccountEO.setIsDeleted(true);
        } else if (type.equals(UpdateUserTypeEnum.ENABLEROOM.getCode())){
            userAccountEO.setForbidCreateRoom(false);
        } else if (type.equals(UpdateUserTypeEnum.DISABLEROOM.getCode())){
            userAccountEO.setForbidCreateRoom(true);
        } else {
            throw new BaseException(01,"用户操作类型错误");
        }
        userService.updateUserInfo(userAccountEO);
        return baseResponse;
    }


    @Override
    @RequestMapping(value = "getNumByUserGrade", method = RequestMethod.POST)
    @ApiOperation(value = "获取某类用户总金币总和", notes = "获取某类用户总金币总和")
    public BaseResponse<Double> getNumByUserGrade(@RequestBody UserAccountDTO userAccountDTO) {
        BaseResponse<Double> baseResponse = new BaseResponse<>();
        if (userAccountDTO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        Map<String, Object> params = new HashMap<>();
        if (userAccountDTO.getUserGrade() == null){
            throw new BaseException(01,"用户类型不能为空");
        }
        if (userAccountDTO.getUserGrade().equals(UserGradeEnum.GENERAL.getCode()) ||
                userAccountDTO.getUserGrade().equals(UserGradeEnum.OFFICIAL.getCode()) ||
                userAccountDTO.getUserGrade().equals(UserGradeEnum.ANCHOR.getCode())
                ){
            params = getMapByDTO(userAccountDTO);
        }
        Double num = userService.sumToatalGold(params);
        baseResponse.setResult(num);
        return baseResponse;
    }

    @Override
    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    @ApiOperation(value = "插入用户，用于后台添加", notes = "插入用户，用于后台添加")
    public BaseResponse<String> insertUser(@RequestBody UserAccountVO userAccountVO) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        if (userAccountVO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        UserAccountEO userAccountEo = this.getUserAccountEo(userAccountVO);
        userService.insertUserInfo(userAccountEo);
        return baseResponse;
    }

    private UserAccountEO getUserAccountEo(UserAccountVO userAccountVO){
        UserAccountEO userAccountEO = new UserAccountEO();
        BeanMapper.copyProperties(userAccountVO,userAccountEO);
        return userAccountEO;
    }

    private UserAccountVO getUserAccountVO(UserAccountEO userAccountEO){
        UserAccountVO userAccountVO  = new UserAccountVO();
        BeanMapper.copyProperties(userAccountEO,userAccountVO);
        return userAccountVO;
    }

    private Map<String, Object> getMapByDTO(UserAccountDTO userAccountDTO){
        Map<String, Object> map = ParamUtils.convertMap(userAccountDTO);
        return map;
    }

}
