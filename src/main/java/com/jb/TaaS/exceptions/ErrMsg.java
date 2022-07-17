package com.jb.TaaS.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    ID_DOESNT_EXIST("ID doesn't exist"),
    ID_ALREADY_EXISTS("ID already exists"),
    INVALID_DATES("start date must be before end date");

    private String errMsg;

    ErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
