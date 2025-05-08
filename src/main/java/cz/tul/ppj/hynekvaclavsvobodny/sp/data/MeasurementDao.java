package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

@Component
public class MeasurementDao extends Dao<Measurement> {

    private static final String CITY_PREFIX = "city__";

    @Autowired
    CityDao cityDao;

    public MeasurementDao() {
        super(
                Measurement.class,
                "measurement",
                List.of(
                        "city_id",
                        "datetime",
                        "temp",
                        "temp_feels_like",
                        "temp_min",
                        "temp_max",
                        "pressure",
                        "humidity",
                        "wind_speed",
                        "wind_direction",
                        "wind_gust",
                        "clouds",
                        "condition_id"
                )
        );
    }

    @Override
    protected List<String> getSelectFields(String prefix) {
        return Stream.of(
                super.getSelectFields(prefix),
                cityDao.getSelectFields(prefix + CITY_PREFIX)
        ).flatMap(Collection::stream).toList();
    }

    @Override
    protected List<String> getSelectTables() {
        return Stream.of(
                super.getSelectTables(),
                cityDao.getSelectTables()
        ).flatMap(Collection::stream).toList();
    }

    @Override
    public List<String> getJoinConstraints() {
        return Stream.of(
                cityDao.getJoinConstraints(),
                List.of("measurement.city_id = city.id")
        ).flatMap(Collection::stream).toList();
    }

    @Override
    protected String getIdCondition() {
        return String.format(
                "%s=%s and %s=%s",
                "city_id", getParam("city_id"),
                "datetime", getParam("datetime")
        );
    }

    @Override
    protected Measurement getEmptyObject() {
        return new Measurement();
    }

    @Override
    public Measurement getFromResultSet(ResultSet rs, String prefix) throws SQLException {
        Measurement obj = super.getFromResultSet(rs, prefix);

        obj.setCity(cityDao.getFromResultSet(rs,prefix + CITY_PREFIX));
        obj.setDatetime(rs.getTimestamp(prefix + "datetime").toInstant());

        obj.setTemp(ResultSetUtils.getNullableFloat(rs, prefix + "temp"));
        obj.setTemp_feels_like(ResultSetUtils.getNullableFloat(rs, prefix + "temp_feels_like"));
        obj.setTemp_min(ResultSetUtils.getNullableFloat(rs, prefix + "temp_min"));
        obj.setTemp_max(ResultSetUtils.getNullableFloat(rs, prefix + "temp_max"));
        obj.setPressure(ResultSetUtils.getNullableInt(rs, prefix + "pressure"));
        obj.setHumidity(ResultSetUtils.getNullableInt(rs, prefix + "humidity"));

        obj.setWind_speed(ResultSetUtils.getNullableFloat(rs, prefix + "wind_speed"));
        obj.setWind_direction(ResultSetUtils.getNullableInt(rs, prefix + "wind_direction"));
        obj.setWind_gust(ResultSetUtils.getNullableFloat(rs, prefix + "wind_gust"));

        obj.setClouds(ResultSetUtils.getNullableInt(rs, prefix + "clouds"));

        obj.setCondition_id(ResultSetUtils.getNullableInt(rs, prefix + "condition_id"));

        return obj;
    }

    public boolean exists(Integer cityId, Instant datetime) {
        return exists(getIdCondition(), Map.of(
                "city_id", cityId,
                "datetime", datetime
        ));
    }

    public boolean exists(City city, Instant datetime) {
        Objects.requireNonNull(city);

        return exists(city.getId(), datetime);
    }

    @Override
    public boolean exists(Measurement measurement) {
        return exists(measurement.getCity(), measurement.getDatetime());
    }

    public Measurement get(Integer cityId, Instant datetime) {
        return getOne(getIdCondition(), Map.of(
                "city_id", cityId,
                "datetime", datetime
        ));
    }

    public Measurement get(City city, Instant datetime) {
        Objects.requireNonNull(city);

        return get(city.getId(), datetime);
    }

}
