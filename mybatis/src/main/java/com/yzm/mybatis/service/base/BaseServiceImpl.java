package com.yzm.mybatis.service.base;

import com.yzm.mybatis.entity.base.BaseEntity;
import com.yzm.mybatis.entity.base.BaseVo;
import com.yzm.mybatis.entity.base.Page;
import com.yzm.mybatis.mapper.base.BaseMapper;

import java.util.List;

public class BaseServiceImpl<E extends BaseEntity, Q extends BaseVo> implements BaseService<E, Q> {

    private BaseMapper<E, Q> baseMapper = null;

    public void setMapper(BaseMapper<E, Q> mapper) {
        this.baseMapper = mapper;
    }

    @Override
    public boolean save(E e) {
        return baseMapper.save(e);
    }

    @Override
    public boolean update(E e) {
        return baseMapper.update(e);
    }

    @Override
    public boolean delete(Integer id) {
        return baseMapper.delete(id);
    }

    @Override
    public E getOne(Integer id) {
        return (E) baseMapper.getOne(id);
    }

    @Override
    public List<E> list() {
        return (List<E>) baseMapper.list();
    }

    @Override
    public Page<E> page(Q q) {
        List<E> list = baseMapper.page(q);
        Page<E> page = q.getPage();
        page.setResult(list);
        return page;
    }
}
