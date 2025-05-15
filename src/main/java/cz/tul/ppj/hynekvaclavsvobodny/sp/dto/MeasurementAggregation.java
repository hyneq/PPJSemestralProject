package cz.tul.ppj.hynekvaclavsvobodny.sp.dto;

import java.util.Objects;

public class MeasurementAggregation {
    private final double temp;
    private final double tempFeelsLike;
    private final double tempMin;
    private final double tempMax;
    private final int pressure;
    private final int humidity;
    private final double windSpeed;
    private final double windGust;

    public MeasurementAggregation(double temp, double tempFeelsLike, double tempMin, double tempMax, int pressure, int humidity, double windSpeed, double windGust) {
        this.temp = temp;
        this.tempFeelsLike = tempFeelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
    }

    public double getTemp() {
        return temp;
    }

    public double getTempFeelsLike() {
        return tempFeelsLike;
    }

    public double getTempMin() {
        return tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public double getWindGust() {
        return windGust;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MeasurementAggregation that)) return false;
        return Double.compare(getTemp(), that.getTemp()) == 0 && Double.compare(getTempFeelsLike(), that.getTempFeelsLike()) == 0 && Double.compare(getTempMin(), that.getTempMin()) == 0 && Double.compare(getTempMax(), that.getTempMax()) == 0 && getPressure() == that.getPressure() && getHumidity() == that.getHumidity() && Double.compare(getWindSpeed(), that.getWindSpeed()) == 0 && Double.compare(getWindGust(), that.getWindGust()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemp(), getTempFeelsLike(), getTempMin(), getTempMax(), getPressure(), getHumidity(), getWindSpeed(), getWindGust());
    }
}
