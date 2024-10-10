package com.socialCloneMicroservices.AccountService.Enums;

public enum ResponseEnum {
    SUCESS(200),
    BAD(500),
    NOT_FOUND(99);

    private final int responseCode;

    ResponseEnum(int responseCode) {
        this.responseCode = responseCode;
    }

    public int getResponseCode() {
        return responseCode;
    }

}
