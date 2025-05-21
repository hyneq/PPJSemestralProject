package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.Measurement;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.MeasurementTestData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MeasurementRepositoryTestHelper extends DataModelRepositoryTestHelper<MeasurementRepository, Measurement, MeasurementTestData> {

    @Autowired
    public MeasurementRepositoryTestHelper(CityRepositoryTestHelper cityRepositoryTestHelper) {
        super();
        this.addDependency(cityRepositoryTestHelper);
    }

}
