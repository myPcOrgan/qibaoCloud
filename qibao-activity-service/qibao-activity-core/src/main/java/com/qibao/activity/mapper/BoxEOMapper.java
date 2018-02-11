package com.qibao.activity.mapper;

import com.qibao.activity.entity.BoxEO;
import com.qibao.activity.entity.vo.BoxVO;
import com.qibao.common.mapper.IBaseMapper;

import java.util.List;
import java.util.Map;

public interface BoxEOMapper extends IBaseMapper<BoxEO> {

    BoxEO selectByIdForUpdate(Long id);

    /**
     * 查询宝箱明细列表(后台管理使用)
     */
    List<BoxVO> selectBoxDetailList(Map<String, Object> queryMap);

}