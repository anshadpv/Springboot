package com.login.loginDemo.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@Table(name = "UserTable")
@AllArgsConstructor
@NoArgsConstructor
public class UserInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String password;
    private String roles;
}
