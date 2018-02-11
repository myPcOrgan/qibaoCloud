package com.qibao.user.context.service;

import com.qibao.common.dto.BaseResponse;
import com.qibao.user.context.dto.UpdateGoldInfoDTO;
import com.qibao.user.context.dto.UserAccountDTO;
import com.qibao.user.context.vo.UserAccountVO;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

/**
 * Created by 340067 on 2018/1/11.
 */

@RequestMapping("user")
public interface IUserController{

    /**
     * 获取用户的基本信息
     * @param userId
     * @return
     */
    @RequestMapping(value = "getUserInfo", method = RequestMethod.GET)
    BaseResponse<UserAccountVO> getUserInfo(@RequestParam("userId") Long userId);

    /**
     * 修改用户基本信息，不包含QQ，微信
     * @param userAccountVO
     * @return
     */
    @RequestMapping(value = "updateUserInfo", method = RequestMethod.POST)
    BaseResponse<String> updateUserInfo(@RequestBody UserAccountVO userAccountVO);

    /**
     * 获取多个用户的基本信息
     * @param ids
     * @return
     */
    @RequestMapping(value = "getUserInfoByIds", method = RequestMethod.GET)
    BaseResponse<UserAccountVO> getUserInfoByIds(@RequestParam("ids") List<Long> ids);

    /**
     * 获取用户基本信息 包括分页与排序
     * @param userAccountDTO
     * @return
     */
    @RequestMapping(value = "listUserAccount", method = RequestMethod.POST)
    BaseResponse<UserAccountVO> listUserAccount(@RequestBody UserAccountDTO userAccountDTO);

    /**
     * 更新用户总金币信息
     * @param updateGoldInfoDTO
     *       type: 1 添加金币
     *             2 减少总金币
     *             3连续超本金次数加一，总金币加
     *             4 连续超本金次数置空，总金币加
     * @return
     */
    @RequestMapping(value = "updateUserGoldInfos", method = RequestMethod.POST)
    BaseResponse<String> updateUserGoldInfos(@RequestBody UpdateGoldInfoDTO updateGoldInfoDTO);

    /**
     * 用户启用，禁用，删除
     * @param type 1.启用  2.禁用  3.删除
     * @param userId
     * @return
     */
    @RequestMapping(value = "updateUserByType", method = RequestMethod.GET)
    BaseResponse<String> updateUserByType(@RequestParam("type") Integer type,
                                          @RequestParam("userId") Long userId);

    /**
     * 获取某个类别用户总金币的总和
     * @param userAccountDTO
     *          userGrade 用户类别 1.普通  2.官方  3.主播
     * @return
     */
    @RequestMapping(value = "getNumByUserGrade", method = RequestMethod.POST)
    BaseResponse<Double> getNumByUserGrade(@RequestBody UserAccountDTO userAccountDTO);

    /**
     * 插入用户，用于后台添加
     * @param userAccountVO
     * @return
     */
    @RequestMapping(value = "insertUser", method = RequestMethod.POST)
    BaseResponse<String> insertUser(@RequestBody UserAccountVO userAccountVO);
}
