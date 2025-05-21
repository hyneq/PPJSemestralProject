package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.dto.MeasurementAggregation;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.MeasurementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
public class MeasurementService extends DataModelService<MeasurementRepository, Measurement, Measurement.MeasurementId> {

    @Autowired
    CityService cityService;

    public MeasurementService() {
        super("measurement");
    }

    public Optional<Measurement> getById(City city, Instant datetime) {
        return repository.findById(city, datetime);
    }

    public Optional<Measurement> getById(Integer cityId, Instant datetime) {
        return cityService.getById(cityId).flatMap(city -> getById(city, datetime));
    }

    public Iterable<Measurement> getByCity(City city) {
        return repository.findAllByIdCity(city);
    }

    public Optional<Iterable<Measurement>> getByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getByCity);
    }

    public Optional<Iterable<Measurement>> getByCityName(String cityName) {
        return cityService.getByName(cityName).map(this::getByCity);
    }

    public void update(Measurement obj, City city, Instant datetime) {
        update(obj, new Measurement.MeasurementId(city, datetime));
    }

    public void update(Measurement obj, Integer cityId, Instant datetime) {
        cityService.getById(cityId).ifPresent(city -> update(obj, city, datetime));
    }

    public void deleteById(City city, Instant datetime) {
        repository.deleteById(city, datetime);
    }

    public void deleteById(Integer cityId, Instant datetime) {
        cityService.getById(cityId).ifPresent(city -> deleteById(city, datetime));
    }

    public void deleteByCity(City city) {
        repository.deleteAllByIdCity(city);
    }

    public void deleteByCityName(String cityName) {
        cityService.getByName(cityName).ifPresent(this::deleteByCity);
    }

    public Optional<Measurement> getLatestMeasurement(City city) {
        return repository.findByIdCityOrderByIdDatetimeDesc(city);
    }

    public Optional<Measurement> getLatestMeasurementByCityId(Integer cityId) {
        return cityService.getById(cityId).flatMap(this::getLatestMeasurement);
    }

    public Optional<Measurement> getLatestMeasurementByCityName(String cityName) {
        return cityService.getByName(cityName).flatMap(this::getLatestMeasurement);
    }

    public Iterable<Measurement> getLastDayMeasurements(City city) {
        Instant now = Instant.now();
        Instant dayAgo = now.minus(1, ChronoUnit.DAYS);
        return repository.findAllByIdCityAndIdDatetimeBetween(city, dayAgo, now);
    }

    public Optional<Iterable<Measurement>> getLastDayMeasurementsByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getLastDayMeasurements);
    }

    public Optional<Iterable<Measurement>> getLastDayMeasurementsByCityName(String cityName) {
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

    public Iterable<Measurement> getLastWeekMeasurements(City city) {
        Instant now = Instant.now();
        Instant weekAgo = now.minus(1, ChronoUnit.WEEKS);
        return repository.findAllByIdCityAndIdDatetimeBetween(city, weekAgo, now);
    }

    public Optional<Iterable<Measurement>> getLastWeekMeasurementsByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getLastWeekMeasurements);
    }

    public Optional<Iterable<Measurement>> getLastWeekMeasurementsByCityName(String cityName) {
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

    public Iterable<Measurement> getLastTwoWeeksMeasurements(City city) {
        Instant now = Instant.now();
        Instant weekAgo = now.minus(2, ChronoUnit.WEEKS);
        return repository.findAllByIdCityAndIdDatetimeBetween(city, weekAgo, now);
    }

    public Optional<Iterable<Measurement>> getLastTwoWeeksMeasurementsByCityId(Integer cityId) {
        return cityService.getById(cityId).map(this::getLastTwoWeeksMeasurements);
    }

    public Optional<Iterable<Measurement>> getLastTwoWeeksMeasurementsByCityName(String cityName) {
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
