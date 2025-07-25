package io.github.fanlizhichzu.manager.typeHandle;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.postgis.PGgeometry;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

/**
 * geometryHandle
 *
 * @author fanlz
 * @date 2023/03/21 13:59
 **/
@MappedTypes({String.class})
public class GeometryTypeHandler extends BaseTypeHandler<String> {
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, String parameter, JdbcType jdbcType) throws SQLException {
        PGgeometry pGgeometry = new PGgeometry(parameter);
        ps.setObject(i, pGgeometry);
    }

    @Override
    public String getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String geometry = rs.getString(columnName);
        PGgeometry pGgeometry = null;
        if (Objects.isNull(geometry)) {
            return null;
        } else {
            pGgeometry = new PGgeometry(rs.getString(columnName));
            return pGgeometry.toString();
        }
    }

    @Override
    public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        PGgeometry pGgeometry = new PGgeometry(rs.getString(columnIndex));
        if (pGgeometry == null) {
            return null;
        }
        return pGgeometry.toString();
    }

    @Override
    public String getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {

        PGgeometry pGgeometry = new PGgeometry(cs.getString(columnIndex));
        if (pGgeometry == null) {
            return null;
        }
        return pGgeometry.toString();
    }
}
