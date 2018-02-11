package com.qibao.activity.service;

import com.qibao.activity.entity.UpvoteEO;
import com.qibao.common.service.IBaseService;

public interface IUpvoteService extends IBaseService<UpvoteEO> {

    /**
     * 根据房间id用户点赞
     * @param roomId
     * @return
     */
    boolean addUpvote(Long roomId, Long userId);
}
