package com.login.loginDemo.repository;

import com.login.loginDemo.model.Bpin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BpinRepository extends JpaRepository<Bpin, String> {
    Optional<Bpin> findByUsername(String username);
}
