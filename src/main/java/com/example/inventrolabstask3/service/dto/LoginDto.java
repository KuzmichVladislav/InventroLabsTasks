package com.example.inventrolabstask3.service.dto;

import com.example.inventrolabstask3.persistence.entity.Login;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginDto {

    @CsvBindByName(column = "Application")
    private String application;
    @CsvBindByName(column = "AppAccountName")
    private String appAccountName;
    @CsvBindByName(column = "IsActive")
    private String isActive;
    @CsvBindByName(column = "JobTitle")
    private String jobTitle;
    @CsvBindByName(column = "Department")
    private String department;

    public Login toLogin(LoginDto loginDto) {
        return new Login(loginDto);
    }
}
