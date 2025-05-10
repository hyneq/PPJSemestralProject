package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.NumberIdDataModel;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface NumberIdDataModelRepository<T extends NumberIdDataModel> extends DataModelRepository<T, Integer> {}
