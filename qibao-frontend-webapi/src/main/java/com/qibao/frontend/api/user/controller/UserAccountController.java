package com.qibao.frontend.api.user.controller;

import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.ISysMessage;
import com.qibao.frontend.feign.IUserInfoFeign;
import com.qibao.user.context.dto.MessageDTO;
import com.qibao.user.context.enums.SysMessageTypeEnum;
import com.qibao.user.context.vo.MessageVO;
import com.qibao.user.context.vo.UserAccountInfoVO;
import com.qibao.user.context.vo.UserAccountVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserAccountController{



    @Autowired
    IUserInfoFeign userInfoFeign;
    @Autowired
    ISysMessage sysMessage;



    @ApiOperation(value = "获取用户的基本信息编排层", notes = "获取用户的基本信息编排层")
    @RequestMapping(value = "queryUserInfo", method = RequestMethod.GET)
    public BaseResponse getUserInfo(){
        BaseResponse<UserAccountInfoVO> baseResponse = new BaseResponse<>();
        Long userId = UserContext.getCurrentUserId();
        if (userId == null){
            throw new BaseException(01,"用户未登录");
        }
        //获取用户基本信息
        BaseResponse<UserAccountVO> userInfo = userInfoFeign.getUserInfo(userId);
        UserAccountVO userAccountVO = userInfo.getResult();
        UserAccountInfoVO userAccountInfoVO = null;
        if (userAccountVO != null) {
            //获取系统消息数
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setUserId(userId);
            messageDTO.setMessageType(SysMessageTypeEnum.SYSTEM.getCode());
            messageDTO.setIsView(false);
            BaseResponse<Integer> baseResponse1 = sysMessage.countSysMessage(messageDTO);
            userAccountInfoVO = new UserAccountInfoVO();
            BeanMapper.copyProperties(userAccountVO, userAccountInfoVO);
            userAccountInfoVO.setEmailNum(baseResponse1.getResult());
            userAccountInfoVO.setUserId(userAccountVO.getId());
            userAccountInfoVO.setTotalGold(userAccountVO.getTotalAmount());
        }
        baseResponse.setResult(userAccountInfoVO);

        return baseResponse;
    }


    @ApiOperation(value = "获取用户的系统消息", notes = "获取用户的系统消息")
    @RequestMapping(value = "listUserSysMessage", method = RequestMethod.POST)
    public BaseResponse<MessageVO> listUserSysMessage(@RequestBody MessageDTO messageDTO){
        BaseResponse<MessageVO> baseResponse;
        Long userId = UserContext.getCurrentUserId();
        if (userId == null){
            throw new BaseException(01,"用户未登录");
        }
        if (messageDTO == null){
            throw new BaseException(01,"参数不能为空");
        }
        if (messageDTO.getPage() == null){
            messageDTO.setPage(1);
        }
        if (messageDTO.getSize() == null){
            messageDTO.setSize(20);
        }
        messageDTO.setUserId(userId);
        messageDTO.setMessageType(SysMessageTypeEnum.SYSTEM.getCode());
        messageDTO.setOrderBy("id");
        messageDTO.setAsc(false);
        //获取系统消息数
        baseResponse = sysMessage.listSysMessage(messageDTO);


        return baseResponse;
    }

    @ApiOperation(value = "用户系统消息标记读取", notes = "用户系统消息标记读取")
    @RequestMapping(value = "updateUserSysMessage", method = RequestMethod.GET)
    public BaseResponse<String> updateUserSysMessage(@RequestParam("id") Long id){
        BaseResponse<String> baseResponse;
        if (id == null){
            throw new BaseException(01,"系统消息id不能为空");
        }
        MessageVO messageVO = new MessageVO();
        messageVO.setId(id);
        messageVO.setIsView(true);
        baseResponse = sysMessage.updateSysMessage(messageVO);
        return baseResponse;
    }


}
