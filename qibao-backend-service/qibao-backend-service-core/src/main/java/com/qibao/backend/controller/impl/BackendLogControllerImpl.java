package com.qibao.backend.controller.impl;

import com.qibao.backend.controller.IBackendLogController;
import com.qibao.backend.entity.BackendLogEO;
import com.qibao.backend.model.BackendLogVO;
import com.qibao.backend.model.QueryBackendLogDTO;
import com.qibao.backend.service.IBackendLogService;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.BeanMapper;
import com.qibao.common.utils.ParamUtils;
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

/**
 * Created by 340067 on 2018/1/25.
 */
@RestController
@RequestMapping(value = "backendLog")
public class BackendLogControllerImpl extends BaseController implements IBackendLogController {

    @Autowired
    IBackendLogService backendLogService;

    @Override
    @RequestMapping(value = "insertBackendLog", method = RequestMethod.POST)
    @ApiOperation(value = "插入操作日志", notes = "插入操作日志")
    public BaseResponse<String> insertBackendLog(@RequestBody BackendLogVO backendLogVO){
        BaseResponse<String> baseResponse = new BaseResponse<>();
        try {
            if (backendLogVO == null){
                throw new BaseException(01,"日志对象不能为空");
            }
            BackendLogEO backendLogEO = this.getEoByVo(backendLogVO);
            if (StringUtils.isBlank(backendLogEO.getDescripe())){
                throw new BaseException(01,"描述信息不能为空");
            }
            if (StringUtils.isBlank(backendLogEO.getIp())){
                throw new BaseException(01,"用户IP不能为空");
            }
            if (StringUtils.isBlank(backendLogEO.getUserName())){
                throw new BaseException(01,"用户名称不能为空");
            }
            if (backendLogEO.getModuleType() == null){
                throw new BaseException(01,"模块类型不能为空");
            }
            if (backendLogEO.getUserId() == null){
                throw new BaseException(01,"用户id不能为空");
            }
            backendLogEO.setCreateTime(new Date());
            backendLogService.insert(backendLogEO);
        } catch (BaseException e) {
            LOGGER.error("插入信息发生异常：" + e.getErrorMsg());
            baseResponse.setErrorMessage("插入信息发生异常：" + e.getErrorMsg());
        } catch (Exception e){
            LOGGER.error("插入信息发生未知异常：" + e.getMessage());
            baseResponse.setErrorMessage("插入信息发生未知异常");
        }
        return baseResponse;
    }


    @Override
    @RequestMapping(value = "listBackendLog", method = RequestMethod.POST)
    @ApiOperation(value = "获取系统消息", notes = "获取系统消息")
    public BaseResponse<BackendLogVO> listBackendLog(@RequestBody QueryBackendLogDTO backendLogDTO) {
        BaseResponse<BackendLogVO> baseResponse = new BaseResponse<>();
        try {
            if (backendLogDTO == null){
                throw  new BaseException(01,"查询系统消息参数不能为空");
            }

            Map<String, Object> params = this.getMapByDTO(backendLogDTO);
            int num = backendLogService.countByMap(params);
            List<BackendLogVO> backendLogVOs = new ArrayList<>();
            if (num != 0) {
                List<BackendLogEO> backendLogEOs = backendLogService.listMessageByMap(params);
                if (!CollectionUtils.isEmpty(backendLogEOs)) {
                    backendLogVOs = BeanMapper.mapList(backendLogEOs, BackendLogVO.class);
                }
            }
            baseResponse.setData(backendLogVOs);
            baseResponse.setTotalCount(num);
            baseResponse.setPageIndex(backendLogDTO.getPage() == null?0:backendLogDTO.getPage());
            baseResponse.setPageSize(backendLogDTO.getSize() == null?0:backendLogDTO.getSize());
        } catch (BaseException e) {
            LOGGER.error("查询操作日志发生异常:" + e.getErrorMsg());
            baseResponse.setErrorMessage("查询操作日志发生异常:" + e.getErrorMsg());
        } catch (Exception e){
            LOGGER.error("查询操作日志发生异常:" + e.getMessage());
            baseResponse.setErrorMessage("查询操作日志发生未知异常");
        }
        return baseResponse;
    }



    private BackendLogEO getEoByVo(BackendLogVO backendLogVO){
        BackendLogEO backendLogEO = new BackendLogEO();
        BeanMapper.copyProperties(backendLogVO, backendLogEO);
        return backendLogEO;
    }

    private Map<String, Object> getMapByDTO(QueryBackendLogDTO backendLogDTO){
        Map<String, Object> map = ParamUtils.convertMap(backendLogDTO);

        if (!CollectionUtils.isEmpty(backendLogDTO.getModuleTypeList())){
            List<Integer> moduleTypeList = backendLogDTO.getModuleTypeList();
            StringBuffer buffer = new StringBuffer();
            for (int i = 0; i < moduleTypeList.size(); i++) {
                if (i == (moduleTypeList.size() - 1)) {
                    buffer.append(moduleTypeList.get(i));
                } else {
                    buffer.append(moduleTypeList.get(i) + ",");
                }
                map.put("moduleTypeList", buffer.toString().split(","));
            }
        }
        return map;

    }



}
