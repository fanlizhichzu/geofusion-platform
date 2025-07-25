package io.github.fanlizhichzu.manager.typeHandle;

import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Bool映射
 *
 * @author fanlz
 * @date 2024/04/01
 **/
@MappedTypes({Boolean.class})
@Slf4j
public class BoolTypeHandler extends BaseTypeHandler<Boolean> {

    private static String globalJdbcType;

    public static void setJdbcType(String globalJdbcType) {
        BoolTypeHandler.globalJdbcType = globalJdbcType;
    }


    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Boolean parameter, JdbcType jdbcType) throws SQLException {
        if (globalJdbcType.equals("dm")){
            ps.setInt(i, parameter ? 1 : 0);
        }else {
            ps.setBoolean(i, parameter);
        }
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, String columnName) throws SQLException {
        if (globalJdbcType.equals("dm")){
            int value = rs.getInt(columnName);
            return value == 1;
        }else {
            return rs.getBoolean(columnName);
        }
    }

    @Override
    public Boolean getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        if (globalJdbcType.equals("dm")){
            int value = rs.getInt(columnIndex);
            return value == 1;
        }else {
            return rs.getBoolean(columnIndex);
        }
    }

    @Override
    public Boolean getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        if (globalJdbcType.equals("dm")){
            int value = cs.getInt(columnIndex);
            return value == 1;
        }else {
            return cs.getBoolean(columnIndex);
        }
    }
}
