package com.example.inventrolabstask3.persistence.repository;

import com.example.inventrolabstask3.persistence.entity.Posting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;
import java.util.List;

public interface PostingRepository extends JpaRepository<Posting, Long> {

    @Query("select p from Posting p where p.pstngDate >= ?1 and p.authorizedDelivery = ?2")
    List<Posting> findAllWithPstngDateAfterAndAuthorizedDelivery(@Param("pstngDate") Date pstngDate, boolean authorizedDelivery);

    List<Posting> findByAuthorizedDelivery(boolean authorizedDelivery);


}
