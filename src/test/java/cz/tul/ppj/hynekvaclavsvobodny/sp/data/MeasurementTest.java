package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.FieldSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MeasurementTest extends DataModelTest<Measurement, MeasurementTestData> {

    @Override
    public void assertEmpty(Measurement measurement) {
        super.assertEmpty(measurement);

        assertNull(measurement.getCity());
        assertNull(measurement.getDatetime());

        assertNull(measurement.getTemp());
        assertNull(measurement.gettemp_feels_like());
        assertNull(measurement.getTemp_min());
        assertNull(measurement.getTemp_max());
        assertNull(measurement.getPressure());
        assertNull(measurement.getHumidity());

        assertNull(measurement.getWind_speed());
        assertNull(measurement.getWind_direction());
        assertNull(measurement.getWind_gust());

        assertNull(measurement.getClouds());

        assertNull(measurement.getCondition_id());
    }

    @Test
    public void testSetCity() {
        obj.setCity(new City());
    }

    @Test
    public void testSetCityNull() {
        obj.setCity(null);
    }

    private static final List<Instant> datetimesValid = Arrays.asList(
            null,
            Instant.EPOCH,
            Instant.parse("1900-01-01T00:00:00Z"),
            Instant.parse("2025-04-05T18:54:54Z")
    );

    @ParameterizedTest
    @FieldSource("datetimesValid")
    public void testDatetimeValid(Instant datetime) {
        obj.setDatetime(datetime);

        assertEquals(datetime, obj.getDatetime());
    }

    private static final List<Float> tempsValid = Arrays.asList((float)0, (float)2, (float)10, (float)0.5, (float)-0.5, (float)-2, (float)-10  );

    @ParameterizedTest
    @FieldSource("tempsValid")
    public void testTempValid(Float temp) {
        obj.setTemp(temp);

        assertEquals(temp, obj.getTemp());
    }

    @ParameterizedTest
    @FieldSource("tempsValid")
    public void testTempFeelsLikeValid(Float temp) {
        obj.setTemp_feels_like(temp);

        assertEquals(temp, obj.gettemp_feels_like());
    }

    @ParameterizedTest
    @FieldSource("tempsValid")
    public void testTempMinValid(Float temp) {
        obj.setTemp_min(temp);

        assertEquals(temp, obj.getTemp_min());
    }

    @ParameterizedTest
    @FieldSource("tempsValid")
    public void testTempMaxValid(Float temp) {
        obj.setTemp_max(temp);

        assertEquals(temp, obj.getTemp_max());
    }

    private static final List<Integer> pressuresValid = Arrays.asList(0, 100, 987, 1034, 890, 1500, 2000);

    @ParameterizedTest
    @FieldSource("pressuresValid")
    public void testPressureValid(Integer pressure) {
        obj.setPressure(pressure);

        assertEquals(pressure, obj.getPressure());
    }

    private static final List<Integer> pressuresInvalid = Arrays.asList(-1, -4, -10);

    @ParameterizedTest
    @FieldSource("pressuresInvalid")
    public void testPressureInvalid(Integer pressure) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setPressure(pressure));
    }

    private static final List<Integer> humiditiesValid = Arrays.asList(0, 100, 50, 80, 74, 65, 36, 73);

    @ParameterizedTest
    @FieldSource("humiditiesValid")
    public void testHumidityValid(Integer humidity) {
        obj.setHumidity(humidity);

        assertEquals(humidity, obj.getHumidity());
    }

    private static final List<Integer> humiditiesInvalid = Arrays.asList(-1, -100, -50, -101, -200, 101, 200);

    @ParameterizedTest
    @FieldSource("humiditiesInvalid")
    public void testHumidityInvalid(Integer humidity) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setHumidity(humidity));
    }

    private static final List<Float> windSpeedsValid = Arrays.asList((float)0, (float)0.1, (float)5, (float)12, (float)50.586);

    @ParameterizedTest
    @FieldSource("windSpeedsValid")
    public void testWindSpeedValid(Float windSpeed) {
        obj.setWind_speed(windSpeed);

        assertEquals(windSpeed, obj.getWind_speed());
    }

    @ParameterizedTest
    @FieldSource("windSpeedsValid")
    public void testWindGustValid(Float windGust) {
        obj.setWind_gust(windGust);

        assertEquals(windGust, obj.getWind_gust());
    }

    private static final List<Float> windSpeedsInvalid = Arrays.asList((float)-0.1, (float)-5, (float)-12, (float)-50.586);

    @ParameterizedTest
    @FieldSource("windSpeedsInvalid")
    public void testWindSpeedInvalid(Float windSpeed) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setWind_speed(windSpeed));
    }

    @ParameterizedTest
    @FieldSource("windSpeedsInvalid")
    public void testWindGustInvalid(Float windGust) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setWind_gust(windGust));
    }

    private static final List<Integer> windDirectionsValid = Arrays.asList(0, 45, 90, 135, 180, 225, 270, 315);

    @ParameterizedTest
    @FieldSource("windDirectionsValid")
    public void testWindDirectionValid(Integer windDirection) {
        obj.setWind_direction(windDirection);

        assertEquals(windDirection, obj.getWind_direction());
    }

    private static final List<Integer> windDirectionsInvalid = Arrays.asList(360, -1, -90, -270, -360);

    @ParameterizedTest
    @FieldSource("windDirectionsInvalid")
    public void testWindDirectionInvalid(Integer windDirection) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setWind_direction(windDirection));
    }

    private static final List<Integer> cloudsValid = Arrays.asList(0, 30, 50, 70, 100);

    @ParameterizedTest
    @FieldSource("cloudsValid")
    public void testCloudsValid(Integer clouds) {
        obj.setClouds(clouds);

        assertEquals(clouds, obj.getClouds());
    }

    private static final List<Integer> cloudsInvalid = Arrays.asList(-1, 101);

    @ParameterizedTest
    @FieldSource("cloudsInvalid")
    public void testCloudsInvalid(Integer clouds) {
        assertThrows(IllegalArgumentException.class,
                () -> obj.setClouds(clouds));
    }
}
