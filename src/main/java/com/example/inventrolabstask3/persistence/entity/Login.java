package com.example.inventrolabstask3.persistence.entity;

import com.example.inventrolabstask3.service.dto.LoginDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "logins")
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String application;
    private String appAccountName;
    private boolean isActive;
    private String jobTitle;
    private String department;

    public Login(LoginDto loginDto) {
        this.application = loginDto.getApplication().trim();
        this.appAccountName = loginDto.getAppAccountName().trim();
        this.isActive = Boolean.parseBoolean(loginDto.getIsActive().trim());
        this.jobTitle = loginDto.getJobTitle().trim();
        this.department = loginDto.getDepartment().trim();
    }
}


