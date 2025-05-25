package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class MeasurementTestData extends DataModelTestData<Measurement, Measurement.MeasurementId> {

    private final CityTestData cityTestData;

    @Autowired
    public MeasurementTestData(CityTestData cityTestData) {
        super();
        this.cityTestData = cityTestData;
        addDependency(cityTestData);
    }

    @Override
    public Measurement emptyInstance() {
        return new Measurement();
    }

    public Measurement getFullInstance() {
        Map<String, City> cities = cityTestData.getObjsValidByName();

        Measurement m = new Measurement();

        m.setCity(cities.get("Jablonec nad Nisou"));
        m.setDatetime(Instant.parse("2024-05-21T02:30:25Z"));

        m.setTemp(10.05);
        m.setTempFeelsLike(10.05);
        m.setTempMin(10.05);
        m.setTempMax(10.05);
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
        Map<String, City> cities = cityTestData.getObjsValidByName();

        return Stream.of(
                new Measurement(cities.get("Jablonec nad Nisou"), Instant.EPOCH),
                new Measurement(cities.get("Los Angeles"), Instant.parse("2025-01-21T21:40:00Z")),
                getFullInstance()
        );
    }

    @Override
    public Stream<Measurement> objsInvalid() {
        Map<String, City> cities = cityTestData.getObjsValidByName();

        return Stream.concat(
                super.objsInvalid(),
                Stream.of(
                        new Measurement(),
                        new Measurement(null, null),
                        new Measurement(null, Instant.EPOCH),
                        new Measurement(new City(null), Instant.EPOCH),
                        new Measurement(cities.get("Los Angeles"), null)
                )
        );
    }

    @Override
    public Stream<Measurement.MeasurementId> idsValid() {
        Map<String, City> cities = cityTestData.getObjsValidByName();

        return Stream.of(
                new Measurement.MeasurementId(cities.get("Jablonec nad Nisou"), Instant.EPOCH),
                new Measurement.MeasurementId(new City(null), Instant.EPOCH),
                new Measurement.MeasurementId(null, Instant.EPOCH),
                new Measurement.MeasurementId(null, null)
        );

    }

    @Override
    public Stream<Measurement.MeasurementId> idsInvalid() {
        return Stream.of((Measurement.MeasurementId) null);
    }

}
