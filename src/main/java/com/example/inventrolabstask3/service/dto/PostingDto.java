package com.example.inventrolabstask3.service.dto;

import com.example.inventrolabstask3.persistence.entity.Posting;
import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.text.ParseException;

@Getter
@Setter
public class PostingDto {

    @CsvBindByName(column = "Mat. Doc.")
    private String matDoc;
    @CsvBindByName(column = "Item")
    private String item;
    @CsvBindByName(column = "Doc. Date")
    private String docDate;
    @CsvBindByName(column = "Pstng Date")
    private String pstngDate;
    @CsvBindByName(column = "Material Description")
    private String materialDescription;
    @CsvBindByName(column = "Quantity")
    private String quantity;
    @CsvBindByName(column = "BUn")
    private String bUn;
    @CsvBindByName(column = "Amount LC")
    private String amountLC;
    @CsvBindByName(column = "Crcy")
    private String crcy;
    @CsvBindByName(column = "User Name")
    private String userName;
    @CsvBindByName(column = "Authorized Delivery")
    private boolean authorizedDelivery;

    public Posting toPosting(PostingDto postingDto) {
        try {
            return new Posting(postingDto);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
