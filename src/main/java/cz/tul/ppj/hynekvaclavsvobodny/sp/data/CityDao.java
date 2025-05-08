package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.stream.Stream;

@Component
public class CityDao extends NumberIdDataModelDao<City> {

    private static final String COUNTRY_PREFIX = "country__";

    @Autowired
    CountryDao countryDao;

    public CityDao() {
        super(
                City.class,
                "city",
                Arrays.asList("name", "country_id")
        );
    }

    protected String getNameCondition() {
        return String.format(
                "%s=%s",
                "name", getParam("name")
        );
    }

    @Override
    protected List<String> getSelectFields(String prefix) {
        return Stream.of(
                super.getSelectFields(prefix),
                countryDao.getSelectFields(prefix + COUNTRY_PREFIX)
        ).flatMap(Collection::stream).toList();
    }

    @Override
    protected List<String> getSelectTables() {
        return Stream.of(
                super.getSelectTables(),
                countryDao.getSelectTables()
        ).flatMap(Collection::stream).toList();
    }

    @Override
    protected List<String> getJoinConstraints() {
        return List.of("city.country_id = country.id");
    }

    @Override
    protected City getEmptyObject() {
        return new City();
    }

    @Override
    public City getFromResultSet(ResultSet rs, String prefix) throws SQLException {
        City obj = super.getFromResultSet(rs, prefix);
        obj.setName(rs.getString(prefix + "name"));
        obj.setCountry(countryDao.getFromResultSet(rs, prefix + COUNTRY_PREFIX));

        return obj;
    }

    public City getByName(String name) {
        return getOne(getNameCondition(), Map.of("name", name));
    }

}
