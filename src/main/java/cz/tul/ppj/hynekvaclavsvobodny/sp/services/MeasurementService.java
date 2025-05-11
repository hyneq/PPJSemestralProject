package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MeasurementService extends DataModelService<MeasurementRepository, Measurement, Measurement.MeasurementId> {

    @Autowired
    CityService cityService;

    public MeasurementService() {
        super("measurement");
    }

    public Measurement getById(City city, Instant datetime) {
        return repository.getById(city, datetime);
    }

    public List<Measurement> getByCity(City city) {
        return repository.getByCity(city);
    }

    public List<Measurement> getByCity(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getByCity(city);
    }

    public void deleteById(City city, Instant datetime) {
        repository.deleteById(city, datetime);
    }

    public void deleteByCity(City city) {
        repository.deleteByCity(city);
    }

    public void deleteByCity(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return;
        }
        deleteByCity(city);
    }

    public Measurement getLatestMeasurement(City city) {
        return repository.findByCityOrderByDatetimeDesc(city);
    }

    public Measurement getLatestMeasurement(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getLatestMeasurement(city);
    }

    public List<Measurement> getLastDayMeasurements(City city) {
        Instant now = Instant.now();
        Instant dayAgo = now.minus(1, ChronoUnit.DAYS);
        return repository.findByCityAndDatetimeBetween(city, dayAgo, now);
    }

    public List<Measurement> getLastDayMeasurements(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getLastDayMeasurements(city);
    }

    public MeasurementAggregation getLastDayAverage(City city) {
        return repository.findDailyAverage(city);
    }

    public MeasurementAggregation getLastDayAverage(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getLastDayAverage(city);
    }

    public List<Measurement> getLastWeekMeasurements(City city) {
        Instant now = Instant.now();
        Instant weekAgo = now.minus(1, ChronoUnit.WEEKS);
        return repository.findByCityAndDatetimeBetween(city, weekAgo, now);
    }

    public List<Measurement> getLastWeekMeasurements(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getLastWeekMeasurements(city);
    }

    public MeasurementAggregation getLastWeekAverage(City city) {
        return repository.findWeeklyAverage(city);
    }

    public MeasurementAggregation getLastWeekAverage(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getLastWeekAverage(city);
    }

    public List<Measurement> getLastTwoWeeksMeasurements(City city) {
        Instant now = Instant.now();
        Instant weekAgo = now.minus(2, ChronoUnit.WEEKS);
        return repository.findByCityAndDatetimeBetween(city, weekAgo, now);
    }

    public List<Measurement> getLastTwoWeeksMeasurements(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getLastTwoWeeksMeasurements(city);
    }

    public MeasurementAggregation getLastTwoWeeksAverage(City city) {
        return repository.findTwoWeeksAverage(city);
    }

    public MeasurementAggregation getLastTwoWeeksAverage(String cityName) {
        City city = cityService.getByName(cityName);
        if (city == null) {
            return null;
        }
        return getLastTwoWeeksAverage(city);
    }

}
