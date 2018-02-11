package com.qibao.activity.backend.control;

import com.github.pagehelper.PageInfo;
import com.qibao.activity.backend.service.IBackendRoomService;
import com.qibao.activity.entity.dto.BackendRoomActivityDTO;
import com.qibao.activity.entity.service.IBackendRoomControl;
import com.qibao.activity.entity.vo.BackendRoomVO;
import com.qibao.common.controller.abs.BaseController;
import com.qibao.common.dto.BaseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("backend/room")
public class BackendRoomControl extends BaseController implements IBackendRoomControl {

    @Autowired
    private IBackendRoomService roomService;

    /**
     * 启用房间
     * @param roomId
     * @return
     */
    @RequestMapping(value = "openRoom",method = RequestMethod.GET)
    public BaseResponse<Boolean> openRoom(@RequestParam("roomId") Long roomId){
        BaseResponse<Boolean> response = new BaseResponse<Boolean>();
        boolean result = roomService.openRoom(roomId);
        response.setResult(result);
        return response;
    }

    /**
     * 禁用房间
     * @param roomId
     * @return
     */
    @RequestMapping(value = "forbidRoom",method = RequestMethod.GET)
    public BaseResponse<Boolean> forbidRoom(@RequestParam("roomId") Long roomId){
        BaseResponse<Boolean> response = new BaseResponse<Boolean>();
        boolean result = roomService.forbidRoom(roomId);
        response.setResult(result);
        return response;
    }

    /**
     * 没收房间
     * @param roomId
     * @return
     */
    @RequestMapping(value = "refuseRoom",method = RequestMethod.GET)
    public BaseResponse<Boolean> refuseRoom(@RequestParam("roomId") Long roomId){
        BaseResponse<Boolean> response = new BaseResponse<Boolean>();
        boolean result = roomService.refuseRoom(roomId);
        response.setResult(result);
        return response;
    }

    /**
     * 根据条件查询房间列表
     * @param dto
     * @return
     */
    @RequestMapping(value = "selectRoomList",method = RequestMethod.POST)
    public BaseResponse<BackendRoomVO> selectRoomList(@RequestBody BackendRoomActivityDTO dto){
        BaseResponse<BackendRoomVO> response = new BaseResponse<BackendRoomVO>();
        PageInfo<BackendRoomVO> pageInfo = roomService.selectRoomList(dto);
        response.setData(pageInfo.getList());
        response.setTotalCount(pageInfo.getTotal());
        response.setPageIndex(dto.getPageIndex());
        response.setPageSize(dto.getPageSize());
        return response;
    }
}
