package com.login.loginDemo.dto;

import com.login.loginDemo.model.OtpType;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OtpRequest {
    private String mobileNumber;
    private OtpType otpType;

}
