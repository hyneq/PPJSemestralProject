package cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.CONFLICT, reason = "The object already exists in the database")
public class ObjectAlreadyExistsException extends DataModelException {
    public ObjectAlreadyExistsException(IDataModel<?> obj) {
        super(
                String.format("The object %s already exists in the database", obj),
                obj
        );
    }
}
