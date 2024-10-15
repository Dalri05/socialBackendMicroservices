package com.socialCloneMicroservices.AccountService.Enums;

public enum ResponseEnum {
    SUCESS(200),
    BAD(500),
    NOT_FOUND(400),
    MONTAR_BODY(777);

    private final int responseCode;

    ResponseEnum(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

}
