package com.qibao.backend.api.activity.controller;

import com.qibao.activity.entity.dto.BoxDTO;
import com.qibao.activity.entity.vo.BoxListVO;
import com.qibao.activity.entity.vo.BoxVO;
import com.qibao.backend.api.backend.service.IBackendFeign;
import com.qibao.backend.common.UserContext;
import com.qibao.backend.feign.IBackendLogFeign;
import com.qibao.backend.feign.IBoxFeign;
import com.qibao.backend.model.BackendLogVO;
import com.qibao.backend.model.BackendRequest;
import com.qibao.backend.model.UserVO;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.exception.BaseException;
import com.qibao.common.utils.IpUtil;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("api/box")
public class BoxControllerImpl {

    @Autowired
    IBoxFeign boxFeign;

    @Autowired
    IBackendLogFeign backendLogFeign;

    @Autowired
    private IBackendFeign backendFeign;

    /**
     * 查询宝箱
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectBoxList", method = RequestMethod.POST)
    public BaseResponse<BoxListVO> selectBoxList(@RequestBody BoxDTO dto) {
        return boxFeign.selectBoxList(dto);
    }

    /**
     * 增加宝箱
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "insertBox", method = RequestMethod.POST)
    public BaseResponse insertBox(@RequestBody BoxDTO dto, HttpServletRequest request) {
        BaseResponse baseResponse = new BaseResponse();
        boxFeign.insertBox(dto);
        String user = UserContext.getCurrentUserLoginAccount();
        if (user == null){
            throw new BaseException(01,"用户未登录");
        }
        BackendRequest backendRequest=new BackendRequest();
        backendRequest.setLoginAccount(user);
        UserVO userVO = (UserVO)backendFeign.findBackUsers(backendRequest).getData().get(0);
        String ip = IpUtil.getIpAddr(request);
        if (StringUtils.isBlank(ip)) {
            ip = request.getRemoteAddr();
        }
        BackendLogVO backendLogVO=new BackendLogVO();
        backendLogVO.setCreateTime(new Date());
        backendLogVO.setUserId(userVO.getId());
        backendLogVO.setIp(ip);
        backendLogVO.setModuleType(2);
        backendLogVO.setDescripe(userVO.getRoleName()+"-"+userVO.getRealName()+",增加了"+dto.getBoxName()+"档位");
        backendLogFeign.insertBackendLog(backendLogVO);
        return baseResponse;
    }

    /**
     * 删除宝箱
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "deleteBox", method = RequestMethod.GET)
    public BaseResponse deleteBox(@RequestParam("id") Long id) {
        BaseResponse baseResponse = new BaseResponse();
        boxFeign.deleteBox(id);
        return baseResponse;
    }

    /**
     * 修改宝箱
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "updateBox", method = RequestMethod.POST)
    public BaseResponse updateBox(@RequestBody BoxDTO dto) {
        BaseResponse baseResponse = new BaseResponse();
        boxFeign.updateBox(dto);
        return baseResponse;
    }

    /**
     * 查询宝箱,根据id
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectById", method = RequestMethod.POST)
    public BaseResponse<BoxVO> selectById(@RequestBody BoxDTO dto) {
        BaseResponse baseResponse = new BaseResponse();
        boxFeign.selectById(dto);
        return baseResponse;
    }

    /**
     * 查询宝箱明细
     *
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectBoxDetailList", method = RequestMethod.POST)
    public BaseResponse<BoxVO> selectBoxDetailList(@RequestBody BoxDTO dto) {
        BaseResponse baseResponse = new BaseResponse();
        boxFeign.selectBoxDetailList(dto);
        return baseResponse;
    }
}
