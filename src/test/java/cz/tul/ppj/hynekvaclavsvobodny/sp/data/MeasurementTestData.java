package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.stream.Stream;

@Component
public class MeasurementTestData extends DataModelTestData<Measurement> {

    @Autowired
    CityDao cityDao;

    @Override
    public Measurement emptyInstance() {
        return new Measurement();
    }

    public Measurement getFullInstance() {
        Measurement m = new Measurement();

        m.setCity(cityDao.get(0));
        m.setDatetime(Instant.parse("2024-05-21T02:30:25Z"));

        m.setTemp(10.05);
        m.setTemp_feels_like(10.05);
        m.setTemp_min(10.05);
        m.setTemp_max(10.05);
        m.setPressure(1024);
        m.setHumidity(80);
        m.setWind_speed(100.0);
        m.setWind_gust(120.0);
        m.setWind_direction(95);

        m.setClouds(5);
        m.setCondition_id(10);

        return m;
    }

    @Override
    public Stream<Measurement> objsValid() {

        return Stream.of(
                new Measurement(cityDao.get(1), Instant.EPOCH),
                new Measurement(cityDao.getByName("Liberec"), Instant.parse("2025-01-21T21:40:00Z")),
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
                        new Measurement(cityDao.get(0), null)
                )
        );
    }
}
