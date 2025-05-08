package cz.tul.ppj.hynekvaclavsvobodny.sp.data;

import java.util.Arrays;
import java.util.List;


public abstract class NumberIdDataModelTestData<T extends NumberIdDataModel> extends DataModelTestData<T> {

    List<Integer> idsValid = Arrays.asList(null, 0, 1, 3, 12, 12345);

    List<Integer> idsInvalid = Arrays.asList(-1, -2, -3, -120, -12345);
}
