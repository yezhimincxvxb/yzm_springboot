package com.yzm.serialize.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

/**
 * 注册自定义消息转换器
 */
@Configuration
public class FastJsonConfiguration {

    @Bean
    public HttpMessageConverters customConverters() {
        Collection<HttpMessageConverter<?>> messageConverters = new ArrayList<>();
        // 创建自定义消息转换器并加入Collection
        messageConverters.add(new CustomHttpMessageConverter());
        return new HttpMessageConverters(true, messageConverters);
    }

    /**
     * 自定义消息转换器
     */
    static class CustomHttpMessageConverter extends FastJsonHttpMessageConverter {

        @Override
        protected void writeInternal(Object obj, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
            byte[] bytes;
            if (obj instanceof JsonFilterObject) {
                JsonFilterObject jsonFilterObject = (JsonFilterObject) obj;
                JsonSerializerFilter jsonSerializerFilter = new JsonSerializerFilter(jsonFilterObject.getIncludeMap(), jsonFilterObject.getExcludeMap());
                String text = JSON.toJSONString(jsonFilterObject.getObject(), jsonSerializerFilter);
                bytes = text.getBytes();
            } else {
                bytes = JSON.toJSONString(obj).getBytes();
            }
            outputMessage.getBody().write(bytes);
        }
    }

    /**
     * 属性过滤器
     */
    static class JsonSerializerFilter extends SimplePropertyPreFilter {
        private final Map<Class<?>, Set<String>> includes;
        private final Map<Class<?>, Set<String>> excludes;

        public JsonSerializerFilter(Map<Class<?>, Set<String>> includes, Map<Class<?>, Set<String>> excludes) {
            this.includes = includes;
            this.excludes = excludes;
        }

        @Override
        public boolean apply(JSONSerializer serializer, Object source, String name) {
            if (!CollectionUtils.isEmpty(includes)) {
                for (Map.Entry<Class<?>, Set<String>> include : includes.entrySet()) {
                    Class<?> objClass = include.getKey();
                    Set<String> includeProp = include.getValue();
                    if (source.getClass().isAssignableFrom(objClass)) {
                        return includeProp.contains(name);
                    }
                }
            }
            if (!CollectionUtils.isEmpty(excludes)) {
                for (Map.Entry<Class<?>, Set<String>> exclude : excludes.entrySet()) {
                    Class<?> objClass = exclude.getKey();
                    Set<String> includeProp = exclude.getValue();
                    if (source.getClass().isAssignableFrom(objClass)) {
                        return !includeProp.contains(name);
                    }
                }
            }
            return true;
        }

    }

}
