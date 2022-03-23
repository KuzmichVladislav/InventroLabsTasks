package com.example.inventrolabstask3.service.utils;

import com.example.inventrolabstask3.service.dto.LoginDto;
import com.example.inventrolabstask3.service.dto.PostingDto;
import com.opencsv.bean.CsvToBeanBuilder;
import org.springframework.stereotype.Component;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.List;

@Component
public class ListDtosCreator {

    public List<PostingDto> createPostingDtos(String part) {
        try (Reader reader = new FileReader(part)) {
            return new CsvToBeanBuilder(reader)
                    .withSeparator(';')
                    .withIgnoreQuotations(true)
                    .withIgnoreEmptyLine(true)
                    .withType(PostingDto.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public List<LoginDto> createLoginDtos(String part) {
        try (Reader reader = new FileReader(part)) {
            return new CsvToBeanBuilder(reader)
                    .withType(LoginDto.class)
                    .build()
                    .parse();
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }
}
