package com.qibao.backend.controller;

import com.qibao.backend.model.BackendLogVO;
import com.qibao.backend.model.QueryBackendLogDTO;
import com.qibao.common.dto.BaseResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by 340067 on 2018/1/25.
 */
@RequestMapping(value = "backendLog")
public interface IBackendLogController {

    @RequestMapping(value = "insertBackendLog", method = RequestMethod.POST)
    BaseResponse<String> insertBackendLog(BackendLogVO backendLogVO);

    @RequestMapping(value = "listBackendLog", method = RequestMethod.POST)
    BaseResponse<BackendLogVO> listBackendLog(QueryBackendLogDTO queryBackendLogDTO);
}
