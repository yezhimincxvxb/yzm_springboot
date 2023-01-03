package com.yzm.plus.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * 填充处理器
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        handleField(metaObject, "createDate");
        handleField(metaObject, "updateDate");
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        handleField(metaObject, "updateDate");
    }

    private void handleField(MetaObject metaObject, String field) {
        if (getFieldValByName(field, metaObject) == null) {
            setFieldValByName(field, LocalDateTime.now(), metaObject);
        }
    }
}

