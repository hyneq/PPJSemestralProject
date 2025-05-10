package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggretagion;
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

    void deleteByCity(City city);

    List<Measurement> getByCity(City city);

    Measurement findFirstByCityOrderByDatetimeDesc(City city);

    default Measurement getLatestByCity(City city) {
        return findFirstByCityOrderByDatetimeDesc(city);
    }

    @Query("SELECT NEW cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax)," +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)" +
            "FROM Measurement m" +
            "WHERE m.city = :city" +
            "GROUP BY CAST(m.datetime AS date)" +
            "ORDER BY CAST(m.datetime AS date) DESC")
    List<MeasurementAggretagion> findDailyAverage(@Param("city") City city);

    @Query("SELECT NEW cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax)," +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)" +
            "FROM Measurement m" +
            "WHERE m.city = :city" +
            "GROUP BY FUNCTION('YEAR', m.datetime), FUNCTION('WEEK', m.datetime)" +
            "ORDER BY FUNCTION('YEAR', m.datetime) DESC, FUNCTION('WEEK', m.datetime) DESC")
    List<MeasurementAggretagion> findWeeklyAverage(@Param("city") City city);

    @Query("SELECT NEW cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), AVG(m.windGust)) " +
            "FROM Measurement m " +
            "WHERE m.city = :city " +
            "GROUP BY FUNCTION('YEAR', m.datetime), FUNCTION('WEEK', m.datetime) / 2 " +
            "ORDER BY FUNCTION('YEAR', m.datetime) DESC, FUNCTION('WEEK', m.datetime) / 2 DESC")
    List<MeasurementAggretagion> findTwoWeeksAverage(@Param("city") City city);
}
