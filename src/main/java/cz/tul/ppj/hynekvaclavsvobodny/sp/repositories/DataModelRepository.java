package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface DataModelRepository<T extends IDataModel, ID extends Serializable> extends CrudRepository<T, ID> {
    T getById(ID id);
}
