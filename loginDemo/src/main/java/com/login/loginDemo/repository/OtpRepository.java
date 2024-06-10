package com.login.loginDemo.repository;

import com.login.loginDemo.model.Otp;
import com.login.loginDemo.model.OtpType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Otp findByMobileNumberAndOtpTypeAndOtp(String mobileNumber, OtpType otpType, String otp);
}
