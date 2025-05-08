package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.*;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public abstract class Dao<T extends IDataModel> {

    protected final String COND_TRUE = "true";

    protected final Class<T> cls;
    protected final String tableName;
    protected final List<String> fields;

    protected final RowMapper<T> rowMapper;

    public Dao(Class<T> cls, String tableName, List<String> fields) {
        this.cls = cls;
        this.tableName = tableName;
        this.fields = fields;

        this.rowMapper = (ResultSet rs, int rowNum) -> getFromResultSet(rs);
    }

    @Autowired
    protected NamedParameterJdbcOperations jdbc;

    protected SqlParameterSource getParams(T obj) {
        return new BeanPropertySqlParameterSource(obj);
    }

    protected SqlParameterSource[] getParamsBatch(List<T> objs) {
        return SqlParameterSourceUtils.createBatch(objs.toArray());
    }

    public String getTableName() {
        return tableName;
    }

    protected String getParam(String field) {
        return ":" + field;
    }

    protected List<String> getParamFieldList() {
        List<String> paramFields = new ArrayList<>();
        for (String field : fields) {
            paramFields.add(getParam(field));
        }

        return paramFields;
    }

    protected List<String> getSelectFields(String prefix) {
        List<String> selectFields = new ArrayList<>();
        for (String field : fields) {
            selectFields.add(String.format("%s.%s as %s%s", tableName, field, prefix, field));
        }

        return selectFields;
    }

    protected List<String> getSelectFields() {
        return getSelectFields("");
    }

    protected List<String> getSelectTables() {
        return List.of(getTableName());
    }

    protected List<String> getJoinConstraints() {
        return List.of(COND_TRUE);
    }

    protected List<String> getUpdateSetParams() {
        List<String> updateSetParams = new ArrayList<>();
        for (String field : fields) {
            updateSetParams.add(String.format("%s=%s", field, getParam(field)));
        }

        return updateSetParams;
    }

    protected abstract String getIdCondition();

    protected String getInsertSQL() {
        return String.format(
                "insert into %s (%s) values (%s)",
                getTableName(),
                String.join(", ", fields),
                String.join(", ", getParamFieldList())
        );
    }

    protected String getExistsSQL(String cond) {
        return String.format(
                "select count(*) from %s where %s",
                getTableName(),
                cond
        );
    }

    protected String getSelectSQL(String cond) {
        return String.format(
                "select %s from %s where %s and %s",
                String.join(", ", getSelectFields()),
                String.join(", ", getSelectTables()),
                String.join(" and ", getJoinConstraints()),
                cond
        );
    }

    protected String getUpdateSQL() {
        return String.format(
                "update %s set %s where %s;",
                getTableName(),
                String.join(", ", getUpdateSetParams()),
                getIdCondition()
        );
    }

    protected String getDeleteSQL() {
        return String.format(
                "delete from %s where %s",
                getTableName(), getIdCondition()
        );
    }

    protected void validateObj(T obj) {
        Objects.requireNonNull(obj, "'obj' cannot be null");
        obj.validate();
    }

    protected abstract T getEmptyObject();

    public T getFromResultSet(ResultSet rs, String prefix) throws SQLException {
        return getEmptyObject();
    }

    public T getFromResultSet(ResultSet rs) throws SQLException {
        return getFromResultSet(rs, "");
    }

    public boolean create(T obj) {
        validateObj(obj);

        return jdbc.update(getInsertSQL(),
                getParams(obj)) == 1;
    }

    @Transactional
    public int[] create(List<T> objs) {
        return jdbc.batchUpdate(getInsertSQL(), getParamsBatch(objs));
    }

    protected boolean exists(String cond, Map<String,?> params) {
        return jdbc.queryForObject(getExistsSQL(cond), params, Integer.class) > 0;
    }

    public abstract boolean exists(T obj);

    protected T getOne(String cond, Map<String,?> params) {
        return jdbc.queryForObject(getSelectSQL(cond), params, rowMapper);
    }

    protected List<T> get(String cond, Map<String,?> params) {
        return jdbc.query(getSelectSQL(cond), params, rowMapper);
    }

    public List<T> getAll() {
        return get(COND_TRUE, Collections.emptyMap());
    }

    public boolean update(T obj) {
        validateObj(obj);

        return jdbc.update(getUpdateSQL(),
                getParams(obj)) == 1;
    }

    public boolean delete(T obj) {
        return jdbc.update(getDeleteSQL(),
                getParams(obj)) == 1;
    }

}
