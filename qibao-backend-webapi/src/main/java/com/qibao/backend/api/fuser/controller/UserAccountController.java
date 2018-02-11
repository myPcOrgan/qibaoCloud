package com.qibao.backend.api.fuser.controller;

import com.qibao.backend.feign.ISysMessageFeign;
import com.qibao.backend.feign.IUserInfoFeign;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.user.context.dto.MessageDTO;
import com.qibao.user.context.dto.UserAccountDTO;
import com.qibao.user.context.enums.SysMessageTypeEnum;
import com.qibao.user.context.enums.UpdateUserTypeEnum;
import com.qibao.user.context.vo.MessageVO;
import com.qibao.user.context.vo.UserAccountVO;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/userAccountController")
public class UserAccountController extends BaseController {



    @Autowired
    IUserInfoFeign userInfoFeign;
    @Autowired
    ISysMessageFeign sysMessage;

    @ApiOperation(value = "根据条件获取用户信息", notes = "根据条件获取用户信息")
    @RequestMapping(value = "queryUserByMap", method = RequestMethod.POST)
    public BaseResponse<UserAccountVO> queryUserByMap(@RequestBody UserAccountDTO userAccountDTO){
        return userInfoFeign.listUserAccount(userAccountDTO);
    }

    @ApiOperation(value = "用户启用，禁用，删除", notes = "用户启用，禁用，删除")
    @RequestMapping(value = "updateUserByType", method = RequestMethod.GET)
    public BaseResponse<String> updateUserByType(@RequestParam("type") Integer type,
                                                 @RequestParam("userId") Long userId){

        return userInfoFeign.updateUserByType(type,userId);
    }

    @ApiOperation(value = "用户日志", notes = "根据条件获取用户信息")
    @RequestMapping(value = "queryUserLogByMap", method = RequestMethod.GET)
    public BaseResponse<MessageVO> queryUserLogByMap(@RequestParam("userId") Long userId,
                                                     @RequestParam("page") Integer page,
                                                     @RequestParam("size") Integer size){
        if (userId == null){
            throw new BaseException(01,"用户id不能为空");
        }
        if (page == null){
            page = 1;
        }
        if (size == null){
            size = 20;
        }
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setUserId(userId);
        messageDTO.setPage(page);
        messageDTO.setSize(size);
        messageDTO.setAsc(false);
        messageDTO.setOrderBy("id");
        messageDTO.setMessageType(SysMessageTypeEnum.SYSTEM.getCode());
        BaseResponse<MessageVO> baseResponse = sysMessage.listSysMessage(messageDTO);

        return baseResponse;
    }

    @ApiOperation(value = "添加用户", notes = "添加用户")
    @RequestMapping(value = "insertUser", method = RequestMethod.GET)
    public BaseResponse<String> insertUser(@RequestParam("userAccount") String userAccount,
                                                     @RequestParam("nickName") String nickName,
                                                     @RequestParam("mobilePhone") String phone,
                                                     @RequestParam("userGrade") Integer userGrade){
        if (userGrade == null){
            throw new BaseException(01,"用户类型不能为空");
        }
        if (StringUtils.isBlank(phone)){
            throw new BaseException(01,"用户手机号不能为空");
        }
        UserAccountVO userAccountVO = new UserAccountVO();
        userAccountVO.setMobilePhone(phone);
        userAccountVO.setUserGrade(userGrade);
        if (StringUtils.isNotBlank(nickName)){
            userAccountVO.setNickName(nickName);
        }
        if (StringUtils.isNotBlank(userAccount)){
            userAccountVO.setUserAccount(userAccount);
        }
        BaseResponse<String> baseResponse = userInfoFeign.insertUser(userAccountVO);

        return baseResponse;
    }

    @ApiOperation(value = "修改用户信息", notes = "修改用户信息")
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    public BaseResponse<String> updateUserInfo(@RequestBody UserAccountVO userAccountVO){
        BaseResponse<String> baseResponse = userInfoFeign.updateUserInfo(userAccountVO);
        return baseResponse;
    }

    @ApiOperation(value = "房主启用，禁用，删除", notes = "房主启用，禁用，删除")
    @RequestMapping(value = "updateUserRoomByType", method = RequestMethod.GET)
    public BaseResponse<String> updateUserRoomByType(@RequestParam("type") Integer type,
                                                 @RequestParam("userId") Long userId){
        if (type == null){
            throw new BaseException(01,"用户操作类型不能为空");
        }
        if (userId == null){
            throw new BaseException(01,"用户id不能为空");
        }
        UserAccountVO userAccountVO = new UserAccountVO();
        userAccountVO.setId(userId);
        if (type.equals(UpdateUserTypeEnum.ENABLEUSER.getCode())){
            userAccountVO.setForbidCreateRoom(false);
        } else if (type.equals(UpdateUserTypeEnum.DISABLEUSER.getCode())){
            userAccountVO.setForbidCreateRoom(true);
        } else if (type.equals(UpdateUserTypeEnum.DELUSER.getCode())){
            userAccountVO.setIsDeleted(true);
        } else {
            throw new BaseException(01,"用户操作类型错误");
        }
        return userInfoFeign.updateUserInfo(userAccountVO);
    }
}
