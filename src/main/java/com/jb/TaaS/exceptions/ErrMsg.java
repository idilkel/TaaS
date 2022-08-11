package com.jb.TaaS.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    ID_DOESNT_EXIST("ID doesn't exist"),
    ID_ALREADY_EXISTS("ID already exists"),
    EMAIL_OR_PASSWORD_ARE_WRONG("Wrong email or password"),
    INVALID_DATES("start date must be before end date");

    private String errMsg;

    ErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
