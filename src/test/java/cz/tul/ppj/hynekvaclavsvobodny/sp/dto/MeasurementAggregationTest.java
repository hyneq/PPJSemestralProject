package cz.tul.ppj.hynekvaclavsvobodny.sp.dto;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class MeasurementAggregationTest {

    private static Stream<Arguments> params() {
        return Stream.of(
                Arguments.of(0.0001, 0.0, 0.0, 0.0, 0, 0, 0.0, 0.0)
        );
    }

    private void assertFieldsEqual(
            MeasurementAggregation obj,
            double temp, double tempFeelsLike, double tempMin, double tempMax, int pressure, int humidity, double windSpeed, double windGust
        ) {
        assertEquals(temp, obj.temp());
        assertEquals(tempFeelsLike, obj.tempFeelsLike());
        assertEquals(tempMin, obj.tempMin());
        assertEquals(tempMax, obj.tempMax());
        assertEquals(pressure, obj.pressure());
        assertEquals(humidity, obj.humidity());
        assertEquals(windSpeed, obj.windSpeed());
        assertEquals(windGust, obj.windGust());
    }

    @ParameterizedTest
    @MethodSource("params")
    public void testObjCreate(double temp, double tempFeelsLike, double tempMin, double tempMax, int pressure, int humidity, double windSpeed, double windGust) {
        MeasurementAggregation obj = new MeasurementAggregation(temp, tempFeelsLike, tempMin, tempMax, pressure, humidity, windSpeed, windGust);

        assertFieldsEqual(obj, temp, tempFeelsLike, tempMin, tempMax, pressure, humidity, windSpeed, windGust);
    }

}