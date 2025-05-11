package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.NumberIdDataModelService;
import org.springframework.stereotype.Component;

@Component
public class NumberIdCrudDelegate
        <T extends NumberIdDataModelService<?, E>, E extends NumberIdDataModel>
        extends CrudDelegate<T, E, Integer> {}
