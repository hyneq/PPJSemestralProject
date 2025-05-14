package cz.tul.ppj.hynekvaclavsvobodny.sp.dto;

import java.util.Objects;

public class MeasurementAggregation {
    private final float temp;
    private final float tempFeelsLike;
    private final float tempMin;
    private final float tempMax;
    private final int pressure;
    private final int humidity;
    private final float windSpeed;
    private final float windGust;

    public MeasurementAggregation(float temp, float tempFeelsLike, float tempMin, float tempMax, int pressure, int humidity, float windSpeed, float windGust) {
        this.temp = temp;
        this.tempFeelsLike = tempFeelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
    }

    public MeasurementAggregation(double temp, double tempFeelsLike, double tempMin, double tempMax, double pressure, double humidity, double windSpeed, double windGust) {
        this(
                (float)temp,
                (float)tempFeelsLike,
                (float)tempMin,
                (float)tempMax,
                (int)pressure,
                (int)humidity,
                (float)windSpeed,
                (float)windGust
        );
    }

    public float getTemp() {
        return temp;
    }

    public float getTempFeelsLike() {
        return tempFeelsLike;
    }

    public float getTempMin() {
        return tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getWindSpeed() {
        return windSpeed;
    }

    public float getWindGust() {
        return windGust;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof MeasurementAggregation that)) return false;
        return Float.compare(getTemp(), that.getTemp()) == 0 && Float.compare(getTempFeelsLike(), that.getTempFeelsLike()) == 0 && Float.compare(getTempMin(), that.getTempMin()) == 0 && Float.compare(getTempMax(), that.getTempMax()) == 0 && getPressure() == that.getPressure() && getHumidity() == that.getHumidity() && Float.compare(getWindSpeed(), that.getWindSpeed()) == 0 && Float.compare(getWindGust(), that.getWindGust()) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTemp(), getTempFeelsLike(), getTempMin(), getTempMax(), getPressure(), getHumidity(), getWindSpeed(), getWindGust());
    }
}
