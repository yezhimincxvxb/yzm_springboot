package com.yzm.plus.config;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

/**
 * 日期字段类型处理器
 */
@MappedTypes(LocalDateTime.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyDateTypeHandler extends BaseTypeHandler<LocalDateTime> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, LocalDateTime localDateTime, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.valueOf(localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli()));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, String s) throws SQLException {
        return str2LocalDateTime(rs.getString(s));
    }

    @Override
    public LocalDateTime getNullableResult(ResultSet rs, int i) throws SQLException {
        return str2LocalDateTime(rs.getString(i));
    }

    @Override
    public LocalDateTime getNullableResult(CallableStatement cs, int i) throws SQLException {
        return str2LocalDateTime(cs.getString(i));
    }

    private LocalDateTime str2LocalDateTime(String s) {
        try {
            long l = Long.parseLong(s);
            return new Date(l).toInstant().atOffset(ZoneOffset.of("+8")).toLocalDateTime();
        } catch (Exception e) {
            //
        }
        return null;
    }

}
