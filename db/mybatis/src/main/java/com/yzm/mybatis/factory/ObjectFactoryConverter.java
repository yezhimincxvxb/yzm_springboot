package com.yzm.mybatis.factory;

import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * 使用对象工厂时，可能报错，添加下面配置
 */
@Component
@ConfigurationPropertiesBinding
public class ObjectFactoryConverter implements Converter<String, ObjectFactory> {

    @Override
    public ObjectFactory convert(String source) {
        try {
            return (ObjectFactory) Class.forName(source).newInstance();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
