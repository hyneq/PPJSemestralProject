package cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "The object does not exist in the database")
public class ObjectDoesNotExistException extends DataModelException {
    public ObjectDoesNotExistException(IDataModel<?> obj) {
        super(
                String.format("The object %s does not exist in the database", obj),
                obj
        );
    }
}
