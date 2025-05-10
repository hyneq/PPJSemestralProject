package cz.tul.ppj.hynekvaclavsvobodny.sp.dto;

public class MeasurementAggretagion {
    private float temp;
    private float tempFeelsLike;
    private float tempMin;
    private float tempMax;
    private int pressure;
    private int humidity;
    private float windSpeed;
    private float windGust;

    public MeasurementAggretagion(float temp, float tempFeelsLike, float tempMin, float tempMax, int pressure, int humidity, float windSpeed, float windGust) {
        this.temp = temp;
        this.tempFeelsLike = tempFeelsLike;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.pressure = pressure;
        this.humidity = humidity;
        this.windSpeed = windSpeed;
        this.windGust = windGust;
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
}
