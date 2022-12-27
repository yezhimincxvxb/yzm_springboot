package com.yzm.fastjson.using;

import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * 自定义序列化
 * BigDecimal类型的参数保留两位小数
 */
public class BigDecimalSerialize implements ObjectSerializer {

    @Override
    public void write(JSONSerializer jsonSerializer, Object value, Object filedName, Type type, int i) {
        String typeName = type.getTypeName();
        if (typeName.equals(BigDecimal.class.getTypeName())) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String price = decimalFormat.format(value);
            jsonSerializer.write(price);
        } else {
            jsonSerializer.write(value);
        }
    }

}
