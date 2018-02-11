package com.qibao.user.context.service;

import com.qibao.common.dto.BaseResponse;
import com.qibao.user.context.dto.MessageDTO;
import com.qibao.user.context.vo.MessageVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("messageController")
public interface IMessageController {



    /**
     * 插入系统消息
     * @param messageVO
     * @return
     */
    @RequestMapping(value = "insertSysMessage", method = RequestMethod.POST)
    BaseResponse<String> insertSysMessage(@RequestBody MessageVO messageVO);

    /**
     * 获取系统消息 包括分页与排序
     * @param messageDTO
     * @return
     */
    @RequestMapping(value = "listSysMessage", method = RequestMethod.POST)
    BaseResponse<MessageVO> listSysMessage(@RequestBody MessageDTO messageDTO);

    /**
     * 查询满足条件的系统消息数
     * @param messageDTO
     * @return
     */
    @RequestMapping(value = "countSysMessage", method = RequestMethod.POST)
    BaseResponse<Integer> countSysMessage(@RequestBody MessageDTO messageDTO);

    /**
     * 修改用户系统消息
     * @param messageVO
     * @return
     */
    @RequestMapping(value = "updateSysMessage", method = RequestMethod.POST)
    BaseResponse<String> updateSysMessage(@RequestBody MessageVO messageVO);

    /**
     * 获取满足条件的金币变化数量
     * @param messageDTO
     * @return
     */
    @RequestMapping(value = "getSumByMap", method = RequestMethod.POST)
    BaseResponse<Double> getSumByMap(@RequestBody MessageDTO messageDTO);
}
