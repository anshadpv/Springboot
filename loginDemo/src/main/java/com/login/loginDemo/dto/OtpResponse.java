package com.login.loginDemo.dto;

import lombok.Getter;
import lombok.Setter;

public class OtpResponse {
    @Getter
    @Setter
    private int code;
    @Getter
    @Setter
    private String message;

    public OtpResponse(int code, String message){
        this.code=code;
        this.message=message;
    }

}
