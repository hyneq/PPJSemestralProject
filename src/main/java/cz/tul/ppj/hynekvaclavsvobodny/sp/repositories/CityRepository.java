package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CityRepository extends NumberIdDataModelRepository<City> {

    List<City> getByCountry(Country country);

    List<City> getByName(String name);

}
