package com.yzm.mybatis.typeHandler;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedJdbcTypes;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@MappedTypes(String.class)
@MappedJdbcTypes(JdbcType.VARCHAR)
public class MyGenderTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String s, JdbcType jdbcType) throws SQLException {
        if ("0".equals(s))
            ps.setString(i, s);
        else
            ps.setString(i, "1");
    }

    @Override
    public String getNullableResult(ResultSet rs, String s) throws SQLException {
        return getGender(rs.getString(s));
    }

    @Override
    public String getNullableResult(ResultSet rs, int i) throws SQLException {
        return getGender(rs.getString(i));
    }

    @Override
    public String getNullableResult(CallableStatement cs, int i) throws SQLException {
        return getGender(cs.getString(i));
    }

    private String getGender(String s) {
        if ("0".equals(s))
            return "女";
        else
            return "男";
    }

}


