package com.qibao.frontend.api.activity.controller;

import com.qibao.common.dto.BaseResponse;
import com.qibao.frontend.api.user.utils.UserContext;
import com.qibao.frontend.feign.IUpvoteFeign;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/upvote")
public class UpvoteControllImpl {

    @Autowired
    private IUpvoteFeign upvoteFeign;

    /**
     * 15.房间活动点赞
     * @return
     */
    @ApiOperation(value = "房间活动点赞", notes = "房间活动点赞")
    @RequestMapping(value = "addUpvote",method = RequestMethod.GET)
    public BaseResponse addUpvote(@RequestParam("roomId") Long roomId){
        Long userId = UserContext.getCurrentUserId();
        BaseResponse baseResponse = upvoteFeign.addUpvote(roomId, userId);
        return baseResponse;
    }
}
