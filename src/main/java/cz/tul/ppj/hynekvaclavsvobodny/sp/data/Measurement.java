package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;

@Entity

public class Measurement implements IDataModel {

    @EmbeddedId
    private final MeasurementId id;

    private Float temp;

    private Float tempFeelsLike;
    private Float tempMin;
    private Float tempMax;
    private Integer pressure;
    private Integer humidity;

    private Float windSpeed;
    private Integer windDirection;
    private Float windGust;

    private Integer clouds;

    private Integer conditionId;

    public Measurement() {
        this.id = new MeasurementId();
    }

    public Measurement(City city, Instant datetime) {
        this();
        this.setCity(city);
        this.setDatetime(datetime);
    }

    @PrePersist
    @Override
    public void validate() {
        Objects.requireNonNull(getCity(), "'city' must not be null.");
        Objects.requireNonNull(getCityId(), "'city' must not be null.");
        Objects.requireNonNull(getDatetime(), "'datetime' must not be null.");
    }


    public City getCity() {
        return id.getCity();
    }

    public void setCity(City city) {
        id.setCity(city);
    }

    public Integer getCityId() {
        return id.getCityId();
    }

    public Instant getDatetime() {
        return id.getDatetime();
    }

    public void setDatetime(Instant datetime) {
        id.setDatetime(datetime);
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

    public Float getTempFeelsLike() {
        return tempFeelsLike;
    }

    public void setTemp_feels_like(Float temp_feels_Like) {
        this.tempFeelsLike = temp_feels_Like;
    }

    public void setTemp_feels_like(Double temp_feels_Like) {
        setTemp_feels_like(temp_feels_Like.floatValue());
    }

    public Float getTempMin() {
        return tempMin;
    }

    public void setTempMin(Float tempMin) {
        this.tempMin = tempMin;
    }

    public void setTemp_min(Double temp_min) {
        setTempMin(temp_min.floatValue());
    }

    public Float getTempMax() {
        return tempMax;
    }

    public void setTempMax(Float tempMax) {
        this.tempMax = tempMax;
    }

    public void setTemp_max(Double temp_max) {
        setTempMax(temp_max.floatValue());
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

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        testWindSpeed(windSpeed, "wind_speed");

        this.windSpeed = windSpeed;
    }

    public void setWind_speed(Double wind_speed) {
        setWindSpeed(wind_speed.floatValue());
    }

    public Float getWindGust() {
        return windGust;
    }

    public void setWindGust(Float windGust) {
        testWindSpeed(windGust, "wind_gust");

        this.windGust = windGust;
    }

    public void setWind_gust(Double temp_max) {
        setWindGust(temp_max.floatValue());
    }

    public Integer getWindDirection() {
        return windDirection;
    }

    public void setWindDirection(Integer windDirection) {
        if (windDirection != null && (windDirection < 0 || windDirection >= 360)) {
            throw new IllegalArgumentException("The argument 'wind_direction' must be between 0 and 360 (exclusive)");
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

    public Integer getConditionId() {
        return conditionId;
    }

    public void setConditionId(Integer conditionId) {
        this.conditionId = conditionId;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Measurement that)) return false;
        return Objects.equals(id, that.id) && Objects.equals(getTemp(), that.getTemp()) && Objects.equals(getTempFeelsLike(), that.getTempFeelsLike()) && Objects.equals(tempMin, that.tempMin) && Objects.equals(tempMax, that.tempMax) && Objects.equals(getPressure(), that.getPressure()) && Objects.equals(getHumidity(), that.getHumidity()) && Objects.equals(windSpeed, that.windSpeed) && Objects.equals(windDirection, that.windDirection) && Objects.equals(windGust, that.windGust) && Objects.equals(getClouds(), that.getClouds()) && Objects.equals(conditionId, that.conditionId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTemp(), tempFeelsLike, tempMin, tempMax, getPressure(), getHumidity(), windSpeed, windDirection, windGust, getClouds(), conditionId);
    }

    @Embeddable
    public static class MeasurementId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "city_id")
        private City city;

        private Instant datetime;

        public MeasurementId() {}

        public MeasurementId(City city, Instant datetime) {
            this.city = city;
            this.datetime = datetime;
        }

        public City getCity() {
            return city;
        }

        public Integer getCityId() {
            if (city == null) {
                return null;
            }

            return city.getId();
        }

        public void setCity(City city) {
            this.city = city;
        }

        public Instant getDatetime() {
            return datetime;
        }

        public void setDatetime(Instant datetime) {
            this.datetime = datetime;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof MeasurementId that)) return false;
            return Objects.equals(getCity(), that.getCity()) && Objects.equals(getDatetime(), that.getDatetime());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getCity(), getDatetime());
        }
    }
}
