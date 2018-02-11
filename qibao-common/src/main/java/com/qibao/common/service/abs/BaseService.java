package com.qibao.common.service.abs;


import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.qibao.common.entity.BaseEntity;
import com.qibao.common.mapper.IBaseMapper;
import com.qibao.common.service.IBaseService;
import com.qibao.common.utils.PageQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import sun.reflect.generics.reflectiveObjects.ParameterizedTypeImpl;
import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.Type;
import java.util.List;

public abstract class BaseService<T extends BaseEntity> implements IBaseService<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(BaseService.class);

    @Autowired
    private IBaseMapper<T> iBaseMapper;

    private Class<T> entityClass;

    private Type[] actualTypeArguments;

    private ParameterizedTypeImpl parameterizedType;

    private Type genericSuperclass;

    protected IBaseMapper<T> getMapper() {
        return iBaseMapper;
    }

    public BaseService() {
        this.genericSuperclass = this.getClass().getGenericSuperclass();

        if (this.genericSuperclass == null) {
            return;
        }

        this.parameterizedType = (ParameterizedTypeImpl) genericSuperclass;
        this.actualTypeArguments = parameterizedType.getActualTypeArguments();
        this.entityClass = (Class<T>) actualTypeArguments[0];
    }

    @Override
    public Long insert(T entity) {
        if (entity == null) {
            return null;
        }
        getMapper().insertSelective(entity);
        return entity.getId();
    }

    @Override
    public boolean update(T entity) {
        if (entity == null || entity.getId() == null) {
            return false;
        }
        return getMapper().updateByPrimaryKeySelective(entity) == 1;
    }

    @Override
    public boolean deleteByPrimary(Long id) {
        if (id == null) {
            return false;
        }
        return getMapper().deleteByPrimaryKey(id) == 1;
    }

    @Override
    public boolean delete(T entity) {
        if (entity == null || entity.getId() == null) {
            return false;
        }
        return getMapper().delete(entity) == 1;
    }

    @Override
    public T selectById(Long id) {
        if (id == null) {
            return null;
        }
        return getMapper().selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectAll() {
        return getMapper().selectAll();
    }

    @Override
    public T selectOne(T record) {
        List<T> select = getMapper().select(record);
        if (select == null || select.size() == 0) {
            return null;
        }
        return select.get(0);
    }

    @Override
    public List<T> select(T record) {
        if (record == null) {
            return null;
        }
        return getMapper().select(record);
    }

    @Override
    public List<T> selectByExample(Example example) {
        if (example == null) {
            return null;
        }
        return getMapper().selectByExample(example);
    }

    @Override
    public Page<T> selectByPage(PageQuery pageQuery) {
        int page = pageQuery.getPage();
        int size = pageQuery.getSize();
        Page<T> pageInfo = PageHelper.startPage(page, size);
        getMapper().selectAll();
        return pageInfo;
    }

    @Override
    public int selectCount(T entity) {
        return getMapper().selectCount(entity);
    }

    @Override
    public boolean exists(Long id) {
        if (entityClass == null) {
            throw new UnsupportedOperationException();
        }
        T domain = null;
        try {
            domain = entityClass.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        domain.setId(id);
        return getMapper().selectCount(domain) > 0;
    }
}
