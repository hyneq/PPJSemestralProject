package cz.tul.ppj.hynekvaclavsvobodny.sp.controllers;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import cz.tul.ppj.hynekvaclavsvobodny.sp.repositories.NumberIdDataModelRepository;
import cz.tul.ppj.hynekvaclavsvobodny.sp.services.NumberIdDataModelService;
import org.springframework.stereotype.Component;

public class NumberIdCrudDelegate
        <S extends NumberIdDataModelService<R, E>, R extends NumberIdDataModelRepository<E>, E extends NumberIdDataModel>
        extends CrudDelegate<S, R, E, Integer> {}
