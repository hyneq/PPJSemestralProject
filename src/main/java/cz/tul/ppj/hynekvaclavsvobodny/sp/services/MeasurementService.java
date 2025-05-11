package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class MeasurementService extends DataModelService<MeasurementRepository, Measurement, Measurement.MeasurementId> {

    @Autowired
    CityService cityService;

    public MeasurementService() {
        super("measurement");
    }

    public Optional<Measurement> getById(City city, Instant datetime) {
        return repository.findById(city, datetime);
    }

    public List<Measurement> getByCity(City city) {
        return repository.getByCity(city);
    }

    public Optional<List<Measurement>> getByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getByCity);
    }

    public Optional<List<Measurement>> getByCityName(String cityName) {
        return cityService.getByName(cityName).map(this::getByCity);
    }

    public void deleteById(City city, Instant datetime) {
        repository.deleteById(city, datetime);
    }

    public void deleteByCity(City city) {
        repository.deleteByCity(city);
    }

    public void deleteByCityName(String cityName) {
        cityService.getByName(cityName).ifPresent(this::deleteByCity);
    }

    public Optional<Measurement> getLatestMeasurement(City city) {
        return repository.findByCityOrderByDatetimeDesc(city);
    }

    public Optional<Measurement> getLatestMeasurementByCityName(String cityName) {
        return cityService.getByName(cityName).flatMap(this::getLatestMeasurement);
    }

    public List<Measurement> getLastDayMeasurements(City city) {
        Instant now = Instant.now();
        Instant dayAgo = now.minus(1, ChronoUnit.DAYS);
        return repository.findByCityAndDatetimeBetween(city, dayAgo, now);
    }

    public Optional<List<Measurement>> getLastDayMeasurementsByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getLastDayMeasurements);
    }

    public Optional<List<Measurement>> getLastDayMeasurementsByCityName(String cityName) {
        return cityService.getByName(cityName).map(this::getLastDayMeasurements);
    }

    public Optional<MeasurementAggregation> getLastDayAverage(City city) {
        return repository.findDailyAverage(city);
    }

    public Optional<MeasurementAggregation> getLastDayAverageByCityId(Integer cityId) {
        return cityService.getById(cityId).flatMap(this::getLastDayAverage);
    }

    public Optional<MeasurementAggregation> getLastDayAverageByCityName(String cityName) {
        return cityService.getByName(cityName).flatMap(this::getLastDayAverage);
    }

    public List<Measurement> getLastWeekMeasurements(City city) {
        Instant now = Instant.now();
        Instant weekAgo = now.minus(1, ChronoUnit.WEEKS);
        return repository.findByCityAndDatetimeBetween(city, weekAgo, now);
    }

    public Optional<List<Measurement>> getLastWeekMeasurementsByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getLastWeekMeasurements);
    }

    public Optional<List<Measurement>> getLastWeekMeasurementsByCityName(String cityName) {
        return cityService.getByName(cityName).map(this::getLastWeekMeasurements);
    }

    public Optional<MeasurementAggregation> getLastWeekAverage(City city) {
        return repository.findWeeklyAverage(city);
    }

    public Optional<MeasurementAggregation> getLastWeekAverageByCityId(Integer cityId) {
        return cityService.getById(cityId).flatMap(this::getLastWeekAverage);
    }

    public Optional<MeasurementAggregation> getLastWeekAverageByCityName(String cityName) {
        return cityService.getByName(cityName).flatMap(this::getLastWeekAverage);
    }

    public List<Measurement> getLastTwoWeeksMeasurements(City city) {
        Instant now = Instant.now();
        Instant weekAgo = now.minus(2, ChronoUnit.WEEKS);
        return repository.findByCityAndDatetimeBetween(city, weekAgo, now);
    }

    public Optional<List<Measurement>> getLastTwoWeeksMeasurementsByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getLastTwoWeeksMeasurements);
    }

    public Optional<List<Measurement>> getLastTwoWeeksMeasurementsByCityName(String cityName) {
        return cityService.getByName(cityName).map(this::getLastTwoWeeksMeasurements);
    }

    public Optional<MeasurementAggregation> getLastTwoWeeksAverage(City city) {
        return repository.findTwoWeeksAverage(city);
    }

    public Optional<MeasurementAggregation> getLastTwoWeeksAverageByCityId(Integer cityId) {
        return cityService.getById(cityId).flatMap(this::getLastTwoWeeksAverage);
    }

    public Optional<MeasurementAggregation> getLastTwoWeeksAverageByCityName(String cityName) {
        return cityService.getByName(cityName).flatMap(this::getLastTwoWeeksAverage);
    }

}
