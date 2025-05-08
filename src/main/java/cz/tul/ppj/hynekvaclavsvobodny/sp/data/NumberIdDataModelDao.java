package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public abstract class NumberIdDataModelDao<T extends NumberIdDataModel> extends Dao<T> {

    protected static final String ID_COLUMN = "id";

    public NumberIdDataModelDao(Class<T> cls, String tableName, List<String> fields) {
        super(
                cls,
                tableName,
                Stream.concat(Stream.of(ID_COLUMN), fields.stream())
                        .collect(Collectors.toList())
        );
    }

    @Override
    protected String getIdCondition() {
        return String.format(
                "%s=%s",
                ID_COLUMN, getParam(ID_COLUMN)
        );
    }

    @Override
    public T getFromResultSet(ResultSet rs, String prefix) throws SQLException {
        T obj = super.getFromResultSet(rs, prefix);
        obj.setId(rs.getInt(prefix + ID_COLUMN));

        return obj;
    }

    @Override
    public boolean create(T obj) {
        validateObj(obj);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        boolean created = jdbc.update(getInsertSQL(),
                getParams(obj), keyHolder, new String[]{ID_COLUMN}) == 1;

        obj.setId(keyHolder.getKeyAs(Integer.class));

        return created;
    }

    public boolean exists(int id) {
        return exists(getIdCondition(), Map.of(ID_COLUMN, id));
    }

    @Override
    public boolean exists(T obj) {
        return exists(obj.getId());
    }

    public T get(int id) {
        return getOne(getIdCondition(), Map.of(ID_COLUMN, id));
    }

}
