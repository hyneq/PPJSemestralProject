package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Stream;

@Component
public class MeasurementTestData extends DataModelTestData<Measurement> {

    @Autowired
    CityRepository cityRepository;

    @Override
    public Measurement emptyInstance() {
        return new Measurement();
    }

    public Measurement getFullInstance() {
        Measurement m = new Measurement();

        m.setCity(cityRepository.getById(1));
        m.setDatetime(Instant.parse("2024-05-21T02:30:25Z"));

        m.setTemp(10.05);
        m.setTempFeelsLike(10.05);
        m.setTemp_min(10.05);
        m.setTemp_max(10.05);
        m.setPressure(1024);
        m.setHumidity(80);
        m.setWindSpeed(100.0);
        m.setWindGust(120.0);
        m.setWindDirection(95);

        m.setClouds(5);
        m.setConditionId(10);

        return m;
    }

    @Override
    public Stream<Measurement> objsValid() {

        return Stream.of(
                new Measurement(cityRepository.getById(1), Instant.EPOCH),
                new Measurement(cityRepository.getByName("Liberec"), Instant.parse("2025-01-21T21:40:00Z")),
                getFullInstance()
        );
    }

    @Override
    public Stream<Measurement> objsInvalid() {
        return Stream.concat(
                super.objsInvalid(),
                Stream.of(
                        new Measurement(),
                        new Measurement(null, null),
                        new Measurement(null, Instant.EPOCH),
                        new Measurement(new City(null), Instant.EPOCH),
                        new Measurement(cityRepository.getById(0), null)
                )
        );
    }
}
