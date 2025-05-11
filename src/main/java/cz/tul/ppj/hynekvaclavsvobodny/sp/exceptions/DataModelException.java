package cz.tul.ppj.hynekvaclavsvobodny.sp.exceptions;

import cz.tul.ppj.hynekvaclavsvobodny.sp.data.IDataModel;

public class DataModelException extends RuntimeException {
    
    private final IDataModel<?> obj;
  
    protected DataModelException(String message, IDataModel<?> obj) {
        super(message);
        this.obj = obj;
    }

    public IDataModel<?> getObj() {
        return obj;
    }
}
