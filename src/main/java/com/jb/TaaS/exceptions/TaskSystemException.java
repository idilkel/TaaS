package com.jb.TaaS.exceptions;

public class TaskSystemException extends Exception {
    public TaskSystemException(ErrMsg errMsg) {
        super(errMsg.getErrMsg());
    }
}
