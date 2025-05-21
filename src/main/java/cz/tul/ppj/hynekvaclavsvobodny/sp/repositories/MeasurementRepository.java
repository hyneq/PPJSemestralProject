package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface MeasurementRepository extends DataModelRepository<Measurement, Measurement.MeasurementId> {

    default Optional<Measurement> findById(City city, Instant datetime) {
        return findById(new Measurement.MeasurementId(city, datetime));
    }

    default Measurement getById(City city, Instant datetime) {
        return getById(new Measurement.MeasurementId(city, datetime));
    }

    default void deleteById(City city, Instant datetime) {
        deleteById(new Measurement.MeasurementId(city, datetime));
    }

    Iterable<Measurement> findAllByIdCity(City city);

    void deleteAllByIdCity(City city);

    Optional<Measurement> findByIdCityOrderByIdDatetimeDesc(City city);

    Iterable<Measurement> findAllByIdCityAndIdDatetimeBetween(City city, Instant from, Instant to);

    @Query("SELECT new cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)) " +
            "FROM Measurement m " +
            "WHERE m.id.city = :city " +
            "GROUP BY CAST(m.id.datetime AS date) " +
            "ORDER BY CAST(m.id.datetime AS date) DESC")
    Optional<MeasurementAggregation> findDailyAverage(@Param("city") City city);

    @Query("SELECT new cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)), " +
            "FUNCTION('WEEK', m.id.datetime) as week " +
            "FROM Measurement m " +
            "WHERE m.id.city = :city " +
            "GROUP BY FUNCTION('YEAR', m.id.datetime), week " +
            "ORDER BY FUNCTION('YEAR', m.id.datetime) DESC, week DESC ")
    Optional<MeasurementAggregation> findWeeklyAverage(@Param("city") City city);

    @Query("SELECT new cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)), " +
            "FUNCTION('FLOOR', EXTRACT(WEEK FROM m.id.datetime) / 2) as week_group " +
            "FROM Measurement m " +
            "WHERE m.id.city = :city " +
            "GROUP BY FUNCTION('YEAR', m.id.datetime), week_group " +
            "ORDER BY FUNCTION('YEAR', m.id.datetime) DESC, week_group DESC")
    Optional<MeasurementAggregation> findTwoWeeksAverage(@Param("city") City city);
}
