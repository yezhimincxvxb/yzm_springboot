package com.yzm.mapstruct.mapper.format;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeFormat {
    public static String toStr(LocalDateTime localDateTime) {
        return DateTimeFormatter.ofPattern("yyy-MM-dd HH:mm:ss").format(localDateTime);
    }
}
