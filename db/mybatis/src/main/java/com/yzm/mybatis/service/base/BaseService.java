package com.yzm.mybatis.service.base;

import com.yzm.mybatis.entity.base.BaseEntity;
import com.yzm.mybatis.entity.base.BaseVo;
import com.yzm.mybatis.entity.base.Page;

import java.util.List;

public interface BaseService<E extends BaseEntity, Q extends BaseVo> {
    boolean save(E e);

    boolean update(E e);

    boolean delete(Integer id);

    E getOne(Integer id);

    List<E> list();

    Page<E> page(Q q);
}
