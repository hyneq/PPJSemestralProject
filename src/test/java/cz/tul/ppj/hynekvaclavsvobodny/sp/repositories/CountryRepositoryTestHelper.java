package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Country;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CountryTestData;
import org.springframework.stereotype.Component;

@Component
public class CountryRepositoryTestHelper extends DataModelRepositoryTestHelper<CountryRepository, Country, CountryTestData> {}
