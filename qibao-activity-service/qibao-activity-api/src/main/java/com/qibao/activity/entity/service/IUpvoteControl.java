package com.qibao.activity.entity.service;

import com.qibao.common.controller.IBaseController;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/upvote")
public interface IUpvoteControl {

    /**
     * 15.房间活动点赞
     * @return
     */
    @RequestMapping(value = "addUpvote",method = RequestMethod.GET)
    public BaseResponse addUpvote(@RequestParam("roomId") Long roomId,@RequestParam("userId") Long userId);
}
