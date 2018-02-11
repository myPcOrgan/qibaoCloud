package com.qibao.backend.api.fuser.controller;

import com.qibao.backend.feign.ISysMessageFeign;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.user.context.dto.MessageDTO;
import com.qibao.user.context.vo.MessageVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/sysMessageController")
public class SysMessageController extends BaseController {


    @Autowired
    ISysMessageFeign sysMessageFeign;

    @ApiOperation(value = "根据条件查询系统信息", notes = "根据条件查询系统信息")
    @RequestMapping(value = "querySysMessageByMap", method = RequestMethod.GET)
    public BaseResponse<MessageVO> querySysMessageByMap(@RequestBody MessageDTO messageDTO){
        return sysMessageFeign.listSysMessage(messageDTO);
    }
}
