package cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "ID in path does not match ID of the body which is not null")
public class IdMismatchException extends DataModelException {
    public IdMismatchException(IDataModel<?> obj) {
        super(
                String.format("Error identifying object %s: ID in argument does not match ID of the object which is not null", obj),
                obj
        );
    }
}
