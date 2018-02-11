package com.qibao.common.mapper;

import com.qibao.common.entity.BaseEntity;
import tk.mybatis.mapper.common.Mapper;

public interface IBaseMapper<T extends BaseEntity> extends Mapper<T> {

}
