package com.yzm.mybatis.mapper.base;

import com.yzm.mybatis.entity.base.BaseEntity;
import com.yzm.mybatis.entity.base.BaseVo;

import java.util.List;

public interface BaseMapper<E extends BaseEntity, Q extends BaseVo> {
    boolean save(E e);

    boolean update(E e);

    boolean delete(Integer id);

    E getOne(Integer id);

    List<E> list();

    List<E> page(Q q);
}
