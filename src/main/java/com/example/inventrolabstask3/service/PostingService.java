package com.example.inventrolabstask3.service;

import com.example.inventrolabstask3.persistence.entity.Login;
import com.example.inventrolabstask3.persistence.entity.Posting;
import com.example.inventrolabstask3.persistence.repository.PostingRepository;
import com.example.inventrolabstask3.service.dto.LoginDto;
import com.example.inventrolabstask3.service.dto.PostingDto;
import com.example.inventrolabstask3.service.utils.ListConverter;
import com.example.inventrolabstask3.service.utils.ListDtosCreator;
import com.opencsv.bean.StatefulBeanToCsv;
import com.opencsv.bean.StatefulBeanToCsvBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.Writer;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostingService {

    public static final String POSTINGS_CSV = "src/main/resources/postings.csv";

    private final PostingRepository postingRepository;
    private final ListDtosCreator listDtosCreator;
    private final ListConverter listConverter;

    /**
     * Step 2 (Прочитать файл postings.csv с локальной файловой системы (строки со значениями в поле Mat. Doc.)
     */
    public List<Long> getMatDoc() {
        List<PostingDto> postingsFromCsv = getPostingsDtoFromCsv();
        List<Posting> postings = listConverter.convertList(postingsFromCsv, x -> x.toPosting(x));
        return postings.stream().map(Posting::getMatDoc).collect(Collectors.toList());
    }

    /**
     * Step 3 (Добавить булевое поле "авторизованная поставка" в данные из postings.csv, которое будет указывать,
     * что User Name (postings.csv) находится в списке AppAccountName (logins.csv) и IsActive)
     */
    public void addAuthorizedDeliveryColumn(List<PostingDto> postingDtos, List<LoginDto> loginDtos) throws Exception {
        List<Login> logins = listConverter.convertList(loginDtos, x -> x.toLogin(x));
        postingDtos.forEach(postingDto -> postingDto.setAuthorizedDelivery(logins.stream()
                .anyMatch(x -> x.getAppAccountName().equals(postingDto.getUserName()) && x.isActive())));
        Writer writer = new FileWriter(POSTINGS_CSV);
        StatefulBeanToCsv sbc = new StatefulBeanToCsvBuilder(writer).withSeparator(';').build();
        sbc.write(postingDtos);
        writer.close();
    }

    /**
     * Step 5 (Сохранить в SQL СУБД данные файла postings.csv (с дополнительным полем))
     */
    public void addPostingsToDb() {
        List<PostingDto> postingsFromCsv = getPostingsDtoFromCsv();
        postingRepository.saveAll(listConverter.convertList(postingsFromCsv, x -> x.toPosting(x)));
    }

    /**
     * Step 6 (Отдавать по GET (REST API) за период (день, месяц, квартал, год)
     * данные из базы, загруженные из postings.csv (с возможностью запроса с
     * фильтром по полю "авторизованная поставка"))
     */
    public List<Posting> getPostingsFromDb(Period period, boolean authorizedDelivery) {
        LocalDate date = LocalDate.now().minusDays(period.getDays());
        Date pstngDate = java.sql.Date.valueOf(date);
        if (period.days == 0) {
            return postingRepository.findByAuthorizedDelivery(authorizedDelivery);
        } else {
            return postingRepository.findAllWithPstngDateAfterAndAuthorizedDelivery(pstngDate, authorizedDelivery);
        }
    }

    public List<PostingDto> getPostingsDtoFromCsv() {
        return listDtosCreator.createPostingDtos(POSTINGS_CSV);
    }
}
