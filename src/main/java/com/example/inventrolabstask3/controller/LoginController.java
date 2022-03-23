package com.example.inventrolabstask3.controller;

import com.example.inventrolabstask3.persistence.entity.Login;
import com.example.inventrolabstask3.service.LoginService;
import com.example.inventrolabstask3.service.utils.ListConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/logins")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;
    private final ListConverter listConverter;

    @GetMapping("/csv")
    public List<Login> getLoginsFromCsv() {
        return listConverter.convertList(loginService.getLoginsFromCsv(), x -> x.toLogin(x));
    }

    @GetMapping("/db")
    public List<Login> getLoginsFromDb() {
        return loginService.getLoginsFromDb();
    }

    @PostMapping("/db")
    public void addLoginsToDb() {
        loginService.addLoginsToDb();
    }
}
