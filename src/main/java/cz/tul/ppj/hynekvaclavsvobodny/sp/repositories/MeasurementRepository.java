package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
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

}
