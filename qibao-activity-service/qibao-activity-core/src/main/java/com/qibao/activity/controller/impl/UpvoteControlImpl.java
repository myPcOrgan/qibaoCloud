package com.qibao.activity.controller.impl;

import com.qibao.activity.entity.service.IUpvoteControl;
import com.qibao.activity.service.IUpvoteService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 活动点赞表对应
 */
@RestController
@RequestMapping("/upvote")
public class UpvoteControlImpl extends BaseController implements IUpvoteControl {

    @Autowired
    private IUpvoteService upvoteService;

    /**
     * 15.房间活动点赞
     * @return
     */
    @RequestMapping(value = "addUpvote",method = RequestMethod.GET)
    public BaseResponse addUpvote(@RequestParam("roomId") Long roomId,@RequestParam("userId") Long userId){
        BaseResponse baseResponse = new BaseResponse();
        boolean result = upvoteService.addUpvote(roomId,userId);
        baseResponse.setResult(result);
        return baseResponse;
    }

}
