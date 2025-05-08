package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;

@Component
public class CountryDao extends NumberIdDataModelDao<Country> {

    public CountryDao() {
        super(
                Country.class,
                "country",
                Arrays.asList("code", "name")
        );
    }

    private String getCodeCondition() {
        return String.format(
                "%s=%s",
                "code", getParam("code")
        );
    }

    @Override
    protected Country getEmptyObject() {
        return new Country();
    }

    @Override
    public Country getFromResultSet(ResultSet rs, String prefix) throws SQLException {
        Country obj = super.getFromResultSet(rs, prefix);
        obj.setCode(rs.getString(prefix + "code"));
        obj.setName(rs.getString(prefix + "name"));

        return obj;
    }

    public Country getByCode(String code) {
        return getOne(getCodeCondition(), Map.of("code", code));
    }

}
