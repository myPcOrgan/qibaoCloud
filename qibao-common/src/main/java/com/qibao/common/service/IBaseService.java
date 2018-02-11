package com.qibao.common.service;


import com.github.pagehelper.Page;
import com.qibao.common.entity.BaseEntity;
import com.qibao.common.utils.PageQuery;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

public interface IBaseService<T extends BaseEntity> {
    Long insert(T entity);

    boolean update(T entity);

    boolean deleteByPrimary(Long id);

    boolean delete(T entity);

    T selectById(Long id);

    List<T> selectAll();

    T selectOne(T record);

    List<T> select(T record);

    List<T> selectByExample(Example example);

    Page<T> selectByPage(PageQuery pageQuery);

    int selectCount(T entity);

    boolean exists(Long id);
}
