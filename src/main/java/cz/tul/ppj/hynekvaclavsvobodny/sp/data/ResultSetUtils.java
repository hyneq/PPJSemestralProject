package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetUtils {

    private ResultSetUtils() {}

    public static Float getNullableFloat(ResultSet rs, String columnLabel) throws SQLException {
        float value = rs.getFloat(columnLabel);
        return rs.wasNull() ? null : value;
    }

    public static Integer getNullableInt(ResultSet rs, String columnLabel) throws SQLException {
        int value = rs.getInt(columnLabel);
        return rs.wasNull() ? null : value;
    }

    public static Float getNullableFloat(ResultSet rs, int columnIndex) throws SQLException {
        float value = rs.getFloat(columnIndex);
        return rs.wasNull() ? null : value;
    }

    public static Integer getNullableInt(ResultSet rs, int columnIndex) throws SQLException {
        int value = rs.getInt(columnIndex);
        return rs.wasNull() ? null : value;
    }
}
