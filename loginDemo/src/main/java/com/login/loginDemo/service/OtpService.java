package com.login.loginDemo.service;

import com.login.loginDemo.model.Otp;
import com.login.loginDemo.model.OtpType;
import com.login.loginDemo.repository.OtpRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class OtpService {

    @Autowired
    private OtpRepository otpRepository;

    private String generateRandomOtp() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    public String generateOtp(String mobileNumber, OtpType otpType){
        String otp = generateRandomOtp();
        Otp otpEntity = new Otp();
        otpEntity.setMobileNumber(mobileNumber);
        otpEntity.setOtpType(otpType);
        otpEntity.setOtp(otp);
        otpEntity.setCreatedTime(LocalDateTime.now());
        otpRepository.save(otpEntity);
        return otp;
    }

    public boolean verifyOtp(String mobileNumber, OtpType otpType, String otp){
        Otp otpEntity = otpRepository.findByMobileNumberAndOtpTypeAndOtp(mobileNumber, otpType, otp);
        return otpEntity != null;
    }
}
