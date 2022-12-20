package com.yzm.datamasking.config;


import org.springframework.util.StringUtils;

/**
 * 脱敏策略
 */
public enum DataMaskingFunc {


    NONE((str, maskChar) -> str),

    // 密码：123456 -> ******
    ALL_MASK((str, maskChar) -> {
        if (!StringUtils.hasText(str)) return "";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            sb.append(maskChar);
        }
        return sb.toString();
    }),

    // 姓名：李总 --> 李**
    NAME_MASK((str, maskChar) -> {
        if (!StringUtils.hasText(str)) return "";

        return str.charAt(0) + maskChar + maskChar;
    }),

    // 手机号：136123456789 -> 136***6789
    PHONE_MASK((str, maskChar) -> {
        if (!StringUtils.hasText(str)) return "";
        if (str.length() <= 7) return str;

        return str.substring(0, 3) + maskChar + maskChar + maskChar + str.substring(str.length() - 4);
    }),

    // 身份号码：
    IDENTITY_MASK((str, maskChar) -> {
        if (!StringUtils.hasText(str)) return "";

        return str.substring(0, 6) + maskChar + maskChar + maskChar + str.substring(str.length() - 4);
    });

    private final DataMaskingOperation operation;

    DataMaskingFunc(DataMaskingOperation operation) {
        this.operation = operation;
    }

    public DataMaskingOperation operation() {
        return this.operation;
    }

}
