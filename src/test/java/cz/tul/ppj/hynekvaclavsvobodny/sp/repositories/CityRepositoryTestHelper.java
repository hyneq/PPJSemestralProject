package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.City;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.CityTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CityRepositoryTestHelper extends NumberIdDataModelRepositoryTestHelper<CityRepository, City, CityTestData> {

    @Autowired
    public CityRepositoryTestHelper(CountryRepositoryTestHelper countryRepositoryTestHelper) {
        super();
        this.addDependency(countryRepositoryTestHelper);
    }

}
