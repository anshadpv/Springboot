package com.login.loginDemo.repository;

import com.login.loginDemo.model.Mpin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface MpinRepository extends JpaRepository<Mpin, String> {
    Optional<Mpin> findByUsername(String username);
}
