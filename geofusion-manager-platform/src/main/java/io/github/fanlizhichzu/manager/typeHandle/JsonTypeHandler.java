package io.github.fanlizhichzu.manager.typeHandle;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgresql.util.PGobject;

import java.sql.*;

/**
 * Jsonb映射
 *
 * @author fanlz
 * @date 2022/06/20 10:52
 **/
@MappedTypes({Object.class})
@Slf4j
public class JsonTypeHandler extends BaseTypeHandler<Object> {

    private static final PGobject jsonObject = new PGobject();

    private static String globalJdbcType;

    public static void setJdbcType(String globalJdbcType) {
        JsonTypeHandler.globalJdbcType = globalJdbcType;
    }

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Object o, JdbcType jdbcType) throws SQLException {
        if (globalJdbcType.equals("dm")){
            Clob clob = preparedStatement.getConnection().createClob();
            clob.setString(1, JSON.toJSONString(o));
            preparedStatement.setClob(i, clob);
        }else {
            jsonObject.setType("jsonb");
            jsonObject.setValue(JSON.toJSONString(o));
            preparedStatement.setObject(i, jsonObject);
        }
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, String columnName) throws SQLException {
        Object obj = resultSet.getObject(columnName);
        if (obj instanceof Clob) {
            Clob clob = (Clob) obj;
            return JSON.parseObject(clob.getSubString(1, (int) clob.length()), Object.class);
        } else {
            return JSON.parseObject(resultSet.getString(columnName), Object.class);
        }
    }

    @Override
    public Object getNullableResult(ResultSet resultSet, int columnIndex) throws SQLException {
        Object obj = resultSet.getObject(columnIndex);
        if (obj instanceof Clob) {
            Clob clob = (Clob) obj;
            return JSON.parseObject(clob.getSubString(1, (int) clob.length()), Object.class);
        } else {
            return JSON.parseObject(resultSet.getString(columnIndex), Object.class);
        }
    }

    @Override
    public Object getNullableResult(CallableStatement callableStatement, int columnIndex) throws SQLException {
        Object obj = callableStatement.getObject(columnIndex);
        if (obj instanceof Clob) {
            Clob clob = (Clob) obj;
            return JSON.parseObject(clob.getSubString(1, (int) clob.length()), Object.class);
        } else {
            return JSON.parseObject(callableStatement.getString(columnIndex), Object.class);
        }
    }
}
