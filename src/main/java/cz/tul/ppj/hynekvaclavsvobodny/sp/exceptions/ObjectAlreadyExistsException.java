package cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;

public class ObjectAlreadyExistsException extends DataModelException {
    public ObjectAlreadyExistsException(IDataModel<?> obj) {
        super(
                String.format("The object %s already exists in the database", obj),
                obj
        );
    }
}
