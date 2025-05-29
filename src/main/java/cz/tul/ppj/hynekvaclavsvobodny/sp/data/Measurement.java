package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.Objects;
import java.util.StringJoiner;

@Entity

public class Measurement implements IDataModel<Measurement.MeasurementId> {

    @EmbeddedId
    @JsonIgnore
    private MeasurementId id;

    private Double temp;

    private Double tempFeelsLike;
    private Double tempMin;
    private Double tempMax;
    private Integer pressure;
    private Integer humidity;

    private Double windSpeed;
    private Integer windDirection;
    private Double windGust;

    private Integer clouds;

    private Integer conditionId;

    public Measurement(MeasurementId id) {
        this.setId(id);
    }

    public Measurement() {
        this(new MeasurementId());
    }

    public Measurement(City city, Instant datetime) {
        this(new MeasurementId(city, datetime));
    }

    @PrePersist
    @Override
    public void validate() {
        Objects.requireNonNull(getCity(), "'city' must not be null.");
        getCity().validate();
        Objects.requireNonNull(getDatetime(), "'datetime' must not be null.");
    }

    @Override
    public MeasurementId getId() {
        return id;
    }

    @Override
    public void setId(MeasurementId id) {
        if (id == null) {
            throw new IllegalArgumentException("'id' must not be null.");
        }
        this.id = id;
    }

    public City getCity() {
        return id.getCity();
    }

    public void setCity(City city) {
        id.setCity(city);
    }

    @JsonIgnore
    public Integer getCityId() {
        return id.getCityId();
    }

    public Instant getDatetime() {
        return id.getDatetime();
    }

    public void setDatetime(Instant datetime) {
        id.setDatetime(datetime);
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempFeelsLike() {
        return tempFeelsLike;
    }

    public void setTempFeelsLike(Double tempFeelsLike) {
        this.tempFeelsLike = tempFeelsLike;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
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

    private void testWindSpeed(Double windSpeed, String paramName) {
        if (windSpeed != null && windSpeed < 0) {
            throw new IllegalArgumentException(String.format("The argument '%s' must not be negative", paramName));
        }
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        testWindSpeed(windSpeed, "wind_speed");

        this.windSpeed = windSpeed;
    }

    public Double getWindGust() {
        return windGust;
    }

    public void setWindGust(Double windGust) {
        testWindSpeed(windGust, "wind_gust");

        this.windGust = windGust;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Measurement.class.getSimpleName() + "[", "]")
                .add("city=" + getCity())
                .add("datetime=" + getDatetime())
                .add("temp=" + temp)
                .add("tempFeelsLike=" + tempFeelsLike)
                .add("tempMin=" + tempMin)
                .add("tempMax=" + tempMax)
                .add("pressure=" + pressure)
                .add("humidity=" + humidity)
                .add("windSpeed=" + windSpeed)
                .add("windDirection=" + windDirection)
                .add("windGust=" + windGust)
                .add("clouds=" + clouds)
                .add("conditionId=" + conditionId)
                .toString();
    }

    @Embeddable
    public static class MeasurementId implements Serializable {

        @ManyToOne
        @JoinColumn(name = "city_id", nullable = false)
        private City city;

        @Column(nullable = false)
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
