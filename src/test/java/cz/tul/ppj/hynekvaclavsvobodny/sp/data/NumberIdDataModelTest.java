package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public abstract class NumberIdDataModelTest<E extends NumberIdDataModel, T extends NumberIdDataModelTestData<E>> extends DataModelTest<E, T, Integer> {}
