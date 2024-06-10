package com.login.loginDemo.controller;

import com.login.loginDemo.dto.OtpRequest;
import com.login.loginDemo.dto.OtpResponse;
import com.login.loginDemo.dto.OtpVerificationRequest;
import com.login.loginDemo.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/otp")
public class otpController {

    @Autowired
    private OtpService otpService;

    @PostMapping("/sendOtp")
    public ResponseEntity<OtpResponse> sendOtp(@RequestBody OtpRequest otpRequest){
        String otp = otpService.generateOtp(otpRequest.getMobileNumber(), otpRequest.getOtpType());
        OtpResponse response = new OtpResponse(200, "OTP sent successfully.");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/verifyOtp")
    public ResponseEntity<String> verifyOtp(@RequestBody OtpVerificationRequest otpVerificationRequest){
        boolean isValid = otpService.verifyOtp(
                otpVerificationRequest.getMobileNumber(),
                otpVerificationRequest.getOtpType(),
                otpVerificationRequest.getOtp()
        );
        if (isValid){
            return ResponseEntity.ok("OTP verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid OTP.");
        }
    }
}
