package com.example.inventrolabstask3.service;

import com.example.inventrolabstask3.persistence.repository.LoginRepository;
import com.example.inventrolabstask3.persistence.repository.PostingRepository;
import com.example.inventrolabstask3.service.dto.LoginDto;
import com.example.inventrolabstask3.service.dto.PostingDto;
import com.example.inventrolabstask3.service.utils.ListConverter;
import com.example.inventrolabstask3.service.utils.ListDtosCreator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

class PostingServiceTest {
    @Mock
    private PostingRepository postingRepository;
    @Mock
    private LoginRepository loginRepository;
    @InjectMocks
    private PostingService postingService;
    @InjectMocks
    private LoginService loginService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ListDtosCreator listDtosCreator = new ListDtosCreator();
        ListConverter listConverter = new ListConverter();
        postingService = new PostingService(postingRepository, listDtosCreator, listConverter);
        loginService = new LoginService(loginRepository, listDtosCreator, listConverter);

    }

    @Test
    void testGetMatDoc() {
        List<Long> result = postingService.getMatDoc();
        Assertions.assertEquals(25, result.size());
    }

    @Test
    void testAddAuthorizedDeliveryColumn() throws Exception {
        List<PostingDto> postingsFromCsv = postingService.getPostingsFromCsv();
        List<LoginDto> loginsFromCsv = loginService.getLoginsFromCsv();
        postingService.addAuthorizedDeliveryColumn(postingsFromCsv, loginsFromCsv);
    }
}