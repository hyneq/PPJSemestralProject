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

    Iterable<Measurement> findAllByIdCity(City city);

    default Optional<Measurement> findById(City city, Instant datetime) {
        return findById(new Measurement.MeasurementId(city, datetime));
    }

    default Measurement getById(City city, Instant datetime) {
        return getById(new Measurement.MeasurementId(city, datetime));
    }

    void deleteAllByIdCity(City city);

    default void deleteById(City city, Instant datetime) {
        deleteById(new Measurement.MeasurementId(city, datetime));
    }

    Optional<Measurement> findFirstByIdCityOrderByIdDatetimeDesc(City city);

    Iterable<Measurement> findAllByIdCityAndIdDatetimeBetween(City city, Instant from, Instant to);

    @Query("SELECT new cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation(" +
            "AVG(m.temp), AVG(m.tempFeelsLike), AVG(m.tempMin), AVG(m.tempMax), " +
            "AVG(m.pressure), AVG(m.humidity), AVG(m.windSpeed), " +
            "AVG(CASE WHEN m.windGust = m.windGust THEN m.windGust ELSE NULL END)) " +
            "FROM Measurement m " +
            "WHERE m.id.city = :city " +
            "AND m.id.datetime BETWEEN :from AND :to")
    Optional<MeasurementAggregation> findAverage(
            @Param("city") City city,
            @Param("from") Instant from,
            @Param("to") Instant to
    );

}
