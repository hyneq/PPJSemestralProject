package cz.tul.ppj.hynekvaclavsvobodny.sp.repositories;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

@NoRepositoryBean
public interface DataModelRepository<E extends IDataModel<ID>, ID extends Serializable> extends CrudRepository<E, ID> {
    E getById(ID id);
}
