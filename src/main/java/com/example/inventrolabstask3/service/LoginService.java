package com.example.inventrolabstask3.service;

import com.example.inventrolabstask3.persistence.entity.Login;
import com.example.inventrolabstask3.persistence.repository.LoginRepository;
import com.example.inventrolabstask3.service.dto.LoginDto;
import com.example.inventrolabstask3.service.utils.ListConverter;
import com.example.inventrolabstask3.service.utils.ListDtosCreator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LoginService {

    public static final String LOGINS_CSV = "src/main/resources/logins.csv";

    private final LoginRepository loginRepository;
    private final ListDtosCreator listDtosCreator;
    private final ListConverter listConverter;

    /**
     * Step 1 (Прочитать файл logins.csv с локальной файловой системы)
     */
    public List<LoginDto> getLoginsDtoFromCsv() {
        return listDtosCreator.createLoginDtos(LOGINS_CSV);
    }

    /**
     * Step 4 (Cохранить в SQL СУБД данные файла logins.csv)
     */
    public void addLoginsToDb() {
        List<LoginDto> loginsFromCsv = getLoginsDtoFromCsv();
        loginRepository.saveAll(listConverter.convertList(loginsFromCsv, x -> x.toLogin(x)));
    }

    public List<Login> getLoginsFromDb() {
        return loginRepository.findAll();
    }
}
