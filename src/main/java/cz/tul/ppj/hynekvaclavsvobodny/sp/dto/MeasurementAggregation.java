package cz.tul.ppj.hynekvaclavsvobodny.sp.dto;

import java.util.Objects;

public record MeasurementAggregation(double temp, double tempFeelsLike, double tempMin, double tempMax, double pressure,
                                     double humidity, double windSpeed, double windGust) {


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MeasurementAggregation that)) return false;
        return Double.compare(temp(), that.temp()) == 0 && Double.compare(tempMin(), that.tempMin()) == 0 && Double.compare(tempMax(), that.tempMax()) == 0 && Double.compare(pressure(), that.pressure()) == 0 && Double.compare(humidity(), that.humidity()) == 0 && Double.compare(windGust(), that.windGust()) == 0 && Double.compare(windSpeed(), that.windSpeed()) == 0 && Double.compare(tempFeelsLike(), that.tempFeelsLike()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp(), tempFeelsLike(), tempMin(), tempMax(), pressure(), humidity(), windSpeed(), windGust());
    }
}
