package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NumberIdDataModelRepository<E extends NumberIdDataModel> extends DataModelRepository<E, Integer> {}
