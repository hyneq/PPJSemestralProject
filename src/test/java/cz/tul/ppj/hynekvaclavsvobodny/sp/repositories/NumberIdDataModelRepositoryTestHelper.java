package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModelTestData;

public class NumberIdDataModelRepositoryTestHelper<R extends NumberIdDataModelRepository<E>, E extends NumberIdDataModel, T extends NumberIdDataModelTestData<E>>
        extends DataModelRepositoryTestHelper<R,E,T> {}
