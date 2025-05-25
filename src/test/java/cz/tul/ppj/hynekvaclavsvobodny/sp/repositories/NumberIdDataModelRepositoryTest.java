package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModelTestData;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class NumberIdDataModelRepositoryTest<
            R extends NumberIdDataModelRepository<E>, E extends NumberIdDataModel, T extends NumberIdDataModelTestData<E>, S extends NumberIdDataModelRepositoryTestHelper<R,E,T>
        >  extends DataModelRepositoryTest<R, E, Integer, T, S> {}
