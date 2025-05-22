package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CountryRepository extends DataModelRepository<Country, String> {

    Optional<Country> findByCode(String code);

    Country getByCode(String code);

    Optional<Country> findByName(String name);

    Country getByName(String name);

    void deleteByName(String name);

    void deleteByCode(String code);

}
