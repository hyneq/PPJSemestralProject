package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModelTestData;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.NumberIdDataModelRepository;

public abstract class NumberIdDataModelServiceTest<S extends NumberIdDataModelService<R,E>, R extends NumberIdDataModelRepository<E>, E extends NumberIdDataModel, T extends NumberIdDataModelTestData<E>> extends DataModelServiceTest<S,R,E, Integer, T> {}
