package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CityRepository extends NumberIdDataModelRepository<City> {

    List<City> findAllByCountry(Country country);

    Optional<City> findByName(String name);

    City getByName(String name);

    void deleteByCountry(Country country);

    void deleteByName(String name);

}
