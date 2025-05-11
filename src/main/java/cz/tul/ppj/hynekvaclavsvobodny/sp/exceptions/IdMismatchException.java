package cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;

public class IdMismatchException extends DataModelException {
    public IdMismatchException(IDataModel<?> obj) {
        super(
                String.format("Error identifying object %s: ID in argument does not match ID of the object which is not null", obj),
                obj
        );
    }
}
