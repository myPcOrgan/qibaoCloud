package com.qibao.user.controller.impl;

import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.ParamUtils;
import com.qibao.user.context.dto.MessageDTO;
import com.qibao.user.context.service.IMessageController;
import com.qibao.user.context.vo.MessageVO;
import com.qibao.user.entity.MessageEO;
import com.qibao.user.exceptions.UserException;
import com.qibao.user.service.IMessageService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("messageController")
public class MessageController extends BaseController implements IMessageController {

    @Autowired
    IMessageService messageService;

    @Override
    @RequestMapping(value = "insertSysMessage", method = RequestMethod.POST,produces = "application/json;charset=UTF-8" )
    @ApiOperation(value = "插入系统消息", notes = "插入系统消息")
    public BaseResponse<String> insertSysMessage(@RequestBody MessageVO messageVO) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        if (messageVO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        if (StringUtils.isBlank(messageVO.getContent())){
            throw new BaseException(01,"消息内容不能为空");
        }
        if (messageVO.getMessageType() == null){
            throw new BaseException(01,"消息类型不能为空");
        }
        MessageEO messageEO = getEoByVo(messageVO);
        messageEO.setCreateTime(new Date());
        messageService.insert(messageEO);
        return baseResponse;
    }

    @Override
    @RequestMapping(value = "listSysMessage", method = RequestMethod.POST)
    @ApiOperation(value = "获取系统消息", notes = "获取系统消息")
    public BaseResponse<MessageVO> listSysMessage(@RequestBody MessageDTO messageDTO) {
        BaseResponse<MessageVO> baseResponse = new BaseResponse<>();

        if (messageDTO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }

        Map<String, Object> params = this.getMapByDTO(messageDTO);
        int num = messageService.countByMap(params);
        List<MessageVO> messageVOs;
        if (num != 0) {
            List<MessageEO> messageEOs = messageService.listMessageByMap(params);
            if (!CollectionUtils.isEmpty(messageEOs)){
                messageVOs = BeanMapper.mapList(messageEOs, MessageVO.class);
            } else {
                messageVOs = new ArrayList<>();
            }
        } else {
            messageVOs = new ArrayList<>();
        }
        baseResponse.setData(messageVOs);
        baseResponse.setTotalCount(num);
        baseResponse.setPageIndex(messageDTO.getPage() == null?0:messageDTO.getPage());
        baseResponse.setPageSize(messageDTO.getSize() == null?0:messageDTO.getSize());
        return baseResponse;
    }

    @Override
    @RequestMapping(value = "countSysMessage", method = RequestMethod.POST)
    @ApiOperation(value = "获取满足条件的系统消息数", notes = "获取满足条件的系统消息数")
    public BaseResponse<Integer> countSysMessage(@RequestBody MessageDTO messageDTO) {
        BaseResponse<Integer> baseResponse = new BaseResponse<>();
        if (messageDTO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        Map<String, Object> params = this.getMapByDTO(messageDTO);
        int num = messageService.countByMap(params);
        baseResponse.setResult(num);

        return baseResponse;
    }


    @Override
    @RequestMapping(value = "updateSysMessage", method = RequestMethod.POST)
    @ApiOperation(value = "修改用户系统消息", notes = "修改用户系统消息")
    public BaseResponse<String> updateSysMessage(@RequestBody MessageVO messageVO) {
        BaseResponse<String> baseResponse = new BaseResponse<>();
        MessageEO messageEO = this.getEoByVo(messageVO);
        if (messageEO.getId() == null){
            throw new BaseException(01,"系统消息id不能为空");
        }
        messageService.update(messageEO);
        return baseResponse;
    }

    @Override
    @RequestMapping(value = "getSumByMap", method = RequestMethod.POST)
    @ApiOperation(value = "获取满足条件的金币变化数量", notes = "获取满足条件的金币变化数量")
    public BaseResponse<Double> getSumByMap(@RequestBody MessageDTO messageDTO) {
        BaseResponse<Double> baseResponse = new BaseResponse<>();
        if (messageDTO == null){
            throw new UserException(UserException.PARAM_EMPTY);
        }
        Map<String, Object> params = this.getMapByDTO(messageDTO);
        Double updateGolds = messageService.getSumByMap(params);
        baseResponse.setResult(updateGolds);
        return baseResponse;
    }

    private MessageEO getEoByVo(MessageVO messageVO){
        MessageEO messageEO = new MessageEO();
        BeanMapper.copyProperties(messageVO,messageEO);
        return messageEO;
    }

    private Map<String, Object> getMapByDTO(MessageDTO messageDTO){
        Map<String, Object> params = ParamUtils.convertMap(messageDTO);
        return params;
    }
}
