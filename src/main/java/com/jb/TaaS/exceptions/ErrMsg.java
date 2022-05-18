package com.jb.TaaS.exceptions;

import lombok.Getter;

@Getter
public enum ErrMsg {
    ID_DOESNT_EXIST("ID doesn't exist"),
    ID_ALREADY_EXISTS("ID already exists"),
    START_DATE_CANT_BE_AFTER_END_DATE("Start date can't be after end date");

    private String errMsg;

    ErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
