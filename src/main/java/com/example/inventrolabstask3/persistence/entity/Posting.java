package com.example.inventrolabstask3.persistence.entity;

import com.example.inventrolabstask3.service.dto.PostingDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "postings")
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private long matDoc;
    private long item;
    private Date docDate;
    private Date pstngDate;
    private String materialDescription;
    private long quantity;
    private String bUn;
    private BigDecimal amountLC;
    private String crcy;
    private String userName;
    private boolean authorizedDelivery;

    public Posting(PostingDto postingDto) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        this.matDoc = Long.parseLong(postingDto.getMatDoc().trim());
        this.item = Long.parseLong(postingDto.getItem().trim());
        this.docDate = formatter.parse(postingDto.getDocDate().trim());
        this.pstngDate = formatter.parse(postingDto.getPstngDate().trim());
        this.materialDescription = postingDto.getMaterialDescription().trim();
        this.quantity = Long.parseLong(postingDto.getQuantity().trim());
        this.bUn = postingDto.getBUn().trim();
        this.amountLC = new BigDecimal(postingDto.getAmountLC().trim().replace(',', '.'));
        this.crcy = postingDto.getCrcy().trim();
        this.userName = postingDto.getUserName().trim();
        this.authorizedDelivery = postingDto.isAuthorizedDelivery();
    }
}
