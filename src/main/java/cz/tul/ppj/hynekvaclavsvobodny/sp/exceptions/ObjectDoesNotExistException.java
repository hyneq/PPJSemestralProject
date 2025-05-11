package cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;

public class ObjectDoesNotExistException extends DataModelException {
    public ObjectDoesNotExistException(IDataModel<?> obj) {
        super(
                String.format("The object %s does not exist in the database", obj),
                obj
        );
    }
}
