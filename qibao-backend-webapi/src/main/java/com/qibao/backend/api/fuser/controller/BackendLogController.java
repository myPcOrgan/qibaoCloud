package com.qibao.backend.api.fuser.controller;

import com.qibao.backend.feign.IBackendLogFeign;
import com.qibao.backend.model.BackendLogVO;
import com.qibao.backend.model.QueryBackendLogDTO;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/backendLogController")
public class BackendLogController extends BaseController {



    @Autowired
    IBackendLogFeign backendLogFeign;

    @ApiOperation(value = "根据条件获取用户操作信息", notes = "根据条件获取用户操作信息")
    @RequestMapping(value = "queryBackendLogByMap", method = RequestMethod.POST)
    public BaseResponse<BackendLogVO> queryBackendLogByMap(@RequestBody QueryBackendLogDTO backendLogDTO){
        return backendLogFeign.listBackendLog(backendLogDTO);
    }
}
