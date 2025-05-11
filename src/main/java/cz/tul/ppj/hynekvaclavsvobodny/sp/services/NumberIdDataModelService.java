package cz.tul.ppj.hynekvaclavsvobodny.sp.services;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.DataModelRepository;

public abstract class NumberIdDataModelService
        <T extends DataModelRepository<E, Integer>, E extends NumberIdDataModel>
        extends DataModelService<T, E, Integer> {

    protected NumberIdDataModelService(String modelName) {
        super(modelName);
    }
}
