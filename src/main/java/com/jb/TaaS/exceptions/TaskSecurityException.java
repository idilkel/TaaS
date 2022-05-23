package com.jb.TaaS.exceptions;

import lombok.Data;

@Data // TODO: 20/05/2022 check if only getter is enough
public class TaskSecurityException extends Exception {

    private SecMsg secMsg;

    public TaskSecurityException(SecMsg secMsg) {
        super(secMsg.getMsg());
        this.secMsg = secMsg;
    }
}
