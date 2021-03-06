package com.example.inventrolabstask3.controller;

import com.example.inventrolabstask3.persistence.entity.Posting;
import com.example.inventrolabstask3.service.Period;
import com.example.inventrolabstask3.service.PostingService;
import com.example.inventrolabstask3.service.dto.PostingDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/postings")
@RequiredArgsConstructor
public class PostingController {

    private final PostingService postingService;

    @GetMapping("/csv")
    public List<PostingDto> getPostingsFromCsv() {
        return postingService.getPostingsDtoFromCsv();
    }

    @GetMapping("/csv/mat-doc")
    public List<Long> getMatDoc() {
        return postingService.getMatDoc();
    }

    /**
     * Step 6 (Отдавать по GET (REST API) за период (день, месяц, квартал, год)
     * данные из базы, загруженные из postings.csv (с возможностью запроса с
     * фильтром по полю "авторизованная поставка"))
     */
    @GetMapping("/db")
    public List<Posting> getPostingsFromDb(
            @RequestParam(value = "period", required = false, defaultValue = "ALL") Period period,
            @RequestParam(value = "authorize", required = false, defaultValue = "false") boolean authorizedDelivery
    ) {
        return postingService.getPostingsFromDb(period, authorizedDelivery);
    }

    @PostMapping("/db")
    public void addPostingsToDb() {
        postingService.addPostingsToDb();
    }
}
