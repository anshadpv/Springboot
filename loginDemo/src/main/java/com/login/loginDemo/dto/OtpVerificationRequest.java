package com.login.loginDemo.dto;

import com.login.loginDemo.model.OtpType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OtpVerificationRequest {
    private String mobileNumber;
    private OtpType otpType;
    private String otp;


}
