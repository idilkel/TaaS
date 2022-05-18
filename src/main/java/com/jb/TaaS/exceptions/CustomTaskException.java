package com.jb.TaaS.exceptions;

public class CustomTaskException extends Exception {
    public CustomTaskException(ErrMsg errMsg) {
        super(errMsg.getErrMsg());
    }
}
