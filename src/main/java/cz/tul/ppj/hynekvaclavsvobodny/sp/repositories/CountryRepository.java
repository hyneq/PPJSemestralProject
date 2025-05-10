package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends NumberIdDataModelRepository<Country> {

    Country getByCode(String code);

    Country getByName(String name);

}
