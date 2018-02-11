package com.qibao.activity;

import com.qibao.activity.controller.impl.RoomActivityControlImpl;
import com.qibao.activity.controller.impl.RoomControlImpl;
import com.qibao.activity.controller.impl.UserActivityControlImpl;
import com.qibao.activity.entity.dto.RoomDTO;
import com.qibao.activity.entity.vo.*;
import com.qibao.activity.service.IRoomActivityService;
import com.qibao.common.dto.BaseResponse;
import com.qibao.common.utils.DateUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class RoomTest {

    @Autowired
    private RoomControlImpl roomControl;

    @Autowired
    private RoomActivityControlImpl activityControl;

    @Autowired
    private UserActivityControlImpl userActivityControl;

    @Autowired
    private IRoomActivityService activityService;

    @Test
    public void testCreateRoom(){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setUserId(11l);
        roomDTO.setRoomName("哈哈");
        roomDTO.setTotalGold(500d);
        roomDTO.setLotteryTime(DateUtil.afterDays(new Date(),2));
        roomDTO.setLotteryNum(5);
        roomDTO.setLotteryType((byte)0);
        roomDTO.setIsLiveRoom((byte)0);
        roomDTO.setActivityType((byte)0);
        roomDTO.setDescription("我的房间");
        BaseResponse<Long> room = roomControl.createRoom(roomDTO);
        System.out.println(JSONObject.fromObject(room).toString());
    }

    @Test
    public void testEditRoom(){
        RoomDTO roomDTO = new RoomDTO();
        roomDTO.setRoomId(27l);
        roomDTO.setUserId(11l);
        roomDTO.setRoomName("哈哈11");
        roomDTO.setTotalGold(600d);
        roomDTO.setLotteryTime(DateUtil.afterDays(new Date(),2));
        roomDTO.setLotteryNum(5);
        roomDTO.setLotteryType((byte)0);
        roomDTO.setIsLiveRoom((byte)0);
        roomDTO.setActivityType((byte)0);
        roomDTO.setDescription("我的房间");
        BaseResponse<Boolean> baseResponse = roomControl.editRoom(roomDTO);
        System.out.println(JSONObject.fromObject(baseResponse).toString());
    }

    @Test
    public void testQueryRoomInfo(){
        long roomId = 27l;
        long userId = 11l;
        BaseResponse<ActivityDetailVO> baseResponse = roomControl.selectRoom(roomId, userId);
        System.out.println(JSONObject.fromObject(baseResponse).toString());

    }

    @Test
    public void testQueryRoomList(){
        RoomDTO dto = new RoomDTO();
        dto.setRequestType(0);
        dto.setUserId(11l);
        BaseResponse<RoomListVO> response = roomControl.selectRoomList(dto);
        System.out.println(JSONObject.fromObject(response).toString());

    }

    @Test
    public void testQueryRoomActivityList(){
        BaseResponse<ActivityVO> response = activityControl.selectActivityList(27l);
        System.out.println(JSONObject.fromObject(response).toString());

    }

    @Test
    public void testQueryActivityUserList(){
        BaseResponse<UserActivityVO> response = userActivityControl.selectActivityUsers(11l);
        System.out.println(JSONObject.fromObject(response).toString());

    }

    @Test
    public void test(){
        BaseResponse<ActivityUserPrizeVO> response = new BaseResponse<>();
        List<ActivityUserPrizeVO> list = new ArrayList<>();
        ActivityUserPrizeVO userPrizeVO = new ActivityUserPrizeVO();
        userPrizeVO.setUserId(11L);
        userPrizeVO.setTotalRmb(12d);
        userPrizeVO.setUserImg("fsf");
        userPrizeVO.setNickName("aa");
        userPrizeVO.setPrizeUnit("万金");
        userPrizeVO.setWinGold(100d);
        list.add(userPrizeVO);
        response.setData(list);
        System.out.println(JSONObject.fromObject(response).toString());
    }

    @Test
    public void testOrderBy(){
        List<ActivityVO> activityVOS = activityService.selectAllActivitys(54l);
        System.out.println(JSONArray.fromObject(activityVOS).toString());

    }
}
