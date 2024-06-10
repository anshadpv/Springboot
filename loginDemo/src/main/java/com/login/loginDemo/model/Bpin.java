package com.login.loginDemo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Bpin {
    @Id
    private String username;
    private String udid;
    private Integer bpin;

}
