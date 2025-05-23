package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CityRepository extends NumberIdDataModelRepository<City> {

    Iterable<City> findAllByCountry(Country country);

    Optional<City> findByName(String name);

    City getByName(String name);

    void deleteAllByCountry(Country country);

    void deleteByName(String name);

}
