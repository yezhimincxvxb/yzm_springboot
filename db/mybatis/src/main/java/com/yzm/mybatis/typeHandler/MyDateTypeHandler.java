package com.yzm.mybatis.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

@MappedTypes(Date.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyDateTypeHandler extends BaseTypeHandler<Date> {

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Date date, JdbcType jdbcType) throws SQLException {
        ps.setString(i, String.valueOf(date.getTime()));
    }

    @Override
    public Date getNullableResult(ResultSet rs, String s) throws SQLException {
        return str2Date(rs.getString(s));
    }

    @Override
    public Date getNullableResult(ResultSet rs, int i) throws SQLException {
        return str2Date(rs.getString(i));
    }

    @Override
    public Date getNullableResult(CallableStatement cs, int i) throws SQLException {
        return str2Date(cs.getString(i));
    }

    private Date str2Date(String s) {
        try {
            long l = Long.parseLong(s);
            return new Date(l);
        } catch (Exception e) {
            //
        }
        return null;
    }

}
