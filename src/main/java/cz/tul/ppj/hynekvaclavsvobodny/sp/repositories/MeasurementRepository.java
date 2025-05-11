package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
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

    List<Measurement> getByCity(City cit);

    void deleteByCity(City city);

    Optional<Measurement> findByCityOrderByDatetimeDesc(City city);

    List<Measurement> findByCityAndDatetimeBetween(City city, Instant from, Instant to);

    @Query("SELECT new cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)) " +
            "FROM Measurement m " +
            "WHERE m.id.city = :city " +
            "GROUP BY CAST(m.datetime AS date) " +
            "ORDER BY CAST(m.datetime AS date) DESC")
    Optional<MeasurementAggregation> findDailyAverage(@Param("city") City city);

    @Query("SELECT new cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)) " +
            "FROM Measurement m " +
            "WHERE m.id.city = :city " +
            "GROUP BY FUNCTION('YEAR', m.datetime), FUNCTION('WEEK', m.datetime) " +
            "ORDER BY FUNCTION('YEAR', m.datetime) DESC, FUNCTION('WEEK', m.datetime) DESC ")
    Optional<MeasurementAggregation> findWeeklyAverage(@Param("city") City city);

    @Query("SELECT new cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)) " +
            "FROM Measurement m " +
            "WHERE m.id.city = :city " +
            "GROUP BY FUNCTION('YEAR', m.datetime), FUNCTION('WEEK', m.datetime) / 2 " +
            "ORDER BY FUNCTION('YEAR', m.datetime) DESC, FUNCTION('WEEK', m.datetime) / 2 DESC")
    Optional<MeasurementAggregation> findTwoWeeksAverage(@Param("city") City city);
}
