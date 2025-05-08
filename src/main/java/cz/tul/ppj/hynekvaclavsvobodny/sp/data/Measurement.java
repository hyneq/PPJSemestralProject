package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.time.Instant;
import java.util.Objects;

public class Measurement implements IDataModel {
    private City city;
    private Instant datetime;

    private Float temp;

    private Float temp_Feels_Like;
    private Float tempMin;
    private Float tempMax;
    private Integer pressure;
    private Integer humidity;

    private Float windSpeed;
    private Integer windDirection;
    private Float windGust;

    private Integer clouds;

    private Integer conditionId;

    public Measurement() {}

    public Measurement(City city, Instant datetime) {
        this.setCity(city);
        this.setDatetime(datetime);
    }

    @Override
    public void validate() {
        Objects.requireNonNull(getCity(), "'city' must not be null.");
        Objects.requireNonNull(getCity_id(), "'city' must not be null.");
        Objects.requireNonNull(getDatetime(), "'datetime' must not be null.");
    }


    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Integer getCity_id() {
        return city.getId();
    }

    public Instant getDatetime() {
        return datetime;
    }

    public void setDatetime(Instant datetime) {
        this.datetime = datetime;
    }

    public Float getTemp() {
        return temp;
    }

    public void setTemp(Float temp) {
        this.temp = temp;
    }

    public void setTemp(Double temp) {
        setTemp(temp.floatValue());
    }

    public Float gettemp_feels_like() {
        return temp_Feels_Like;
    }

    public void setTemp_feels_like(Float tempFeelsLike) {
        this.temp_Feels_Like = tempFeelsLike;
    }

    public void setTemp_feels_like(Double tempFeelsLike) {
        setTemp_feels_like(tempFeelsLike.floatValue());
    }

    public Float getTemp_min() {
        return tempMin;
    }

    public void setTemp_min(Float tempMin) {
        this.tempMin = tempMin;
    }

    public void setTemp_min(Double tempMin) {
        setTemp_min(tempMin.floatValue());
    }

    public Float getTemp_max() {
        return tempMax;
    }

    public void setTemp_max(Float tempMax) {
        this.tempMax = tempMax;
    }

    public void setTemp_max(Double tempMax) {
        setTemp_max(tempMax.floatValue());
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        if (pressure != null && pressure < 0) {
            throw new IllegalArgumentException("The argument 'pressure' must not be negative");
        }

        this.pressure = pressure;
    }

    public Integer getHumidity() {
        return humidity;
    }

    public void setHumidity(Integer humidity) {
        if (humidity != null && (humidity < 0 || humidity > 100)) {
            throw new IllegalArgumentException("The argument 'humidity' must be between 0 and 100");
        }

        this.humidity = humidity;
    }

    private void testWindSpeed(Float windSpeed, String paramName) {
        if (windSpeed != null && windSpeed < 0) {
            throw new IllegalArgumentException(String.format("The argument '%s' must not be negative", paramName));
        }
    }

    public Float getWind_speed() {
        return windSpeed;
    }

    public void setWind_speed(Float windSpeed) {
        testWindSpeed(windSpeed, "windSpeed");

        this.windSpeed = windSpeed;
    }

    public void setWind_speed(Double windSpeed) {
        setWind_speed(windSpeed.floatValue());
    }

    public Float getWind_gust() {
        return windGust;
    }

    public void setWind_gust(Float windGust) {
        testWindSpeed(windGust, "windGust");

        this.windGust = windGust;
    }

    public void setWind_gust(Double windGust) {
        setWind_gust(windGust.floatValue());
    }

    public Integer getWind_direction() {
        return windDirection;
    }

    public void setWind_direction(Integer windDirection) {
        if (windDirection != null && (windDirection < 0 || windDirection >= 360)) {
            throw new IllegalArgumentException("The argument 'windDirection' must be between 0 and 360 (exclusive)");
        }

        this.windDirection = windDirection;
    }

    public Integer getClouds() {
        return clouds;
    }

    public void setClouds(Integer clouds) {
        if (clouds != null && (clouds < 0 || clouds > 100)) {
            throw new IllegalArgumentException("The argument 'clouds' must be between 0 and 100");
        }

        this.clouds = clouds;
    }

    public Integer getCondition_id() {
        return conditionId;
    }

    public void setCondition_id(Integer conditionId) {
        this.conditionId = conditionId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Measurement that)) return false;
        return Objects.equals(getCity(), that.getCity()) && Objects.equals(getDatetime(), that.getDatetime()) && Objects.equals(getTemp(), that.getTemp()) && Objects.equals(temp_Feels_Like, that.temp_Feels_Like) && Objects.equals(tempMin, that.tempMin) && Objects.equals(tempMax, that.tempMax) && Objects.equals(getPressure(), that.getPressure()) && Objects.equals(getHumidity(), that.getHumidity()) && Objects.equals(windSpeed, that.windSpeed) && Objects.equals(windDirection, that.windDirection) && Objects.equals(windGust, that.windGust) && Objects.equals(getClouds(), that.getClouds()) && Objects.equals(conditionId, that.conditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCity(), getDatetime(), getTemp(), temp_Feels_Like, tempMin, tempMax, getPressure(), getHumidity(), windSpeed, windDirection, windGust, getClouds(), conditionId);
    }
}
