package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends NumberIdDataModelRepository<Country> {

    Country getByCode(String code);

    Optional<Country> findByCode(String code);

    void deleteByCode(String code);

    Country getByName(String name);

    Optional<Country> findByName(String name);

    void deleteByName(String name);

}
