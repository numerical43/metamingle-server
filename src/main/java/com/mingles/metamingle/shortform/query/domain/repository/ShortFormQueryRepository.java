package com.mingles.metamingle.shortform.query.domain.repository;

import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormListResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortFormQueryRepository extends JpaRepository<ShortForm, Long> {

    @Query(value = "SELECT S.SHORT_FORM_NO, S.TITLE, S.THUMBNAIL_URL, S.URL, S.DESCRIPTION, " +
            "M.MEMBER_NAME, S.DATE, S.IS_INTERACTIVE " +
            "FROM TBL_SHORT_FORM S " +
            "INNER JOIN TBL_MEMBER M ON M.MEMBER_NO = S.MEMBER_NO", nativeQuery = true)
    List<ShortForm> findAllShortFormList();


    @Query(value = "SELECT S.SHORT_FORM_NO, S.TITLE, S.THUMBNAIL_URL, S.URL, S.DESCRIPTION, " +
            "M.MEMBER_NAME, S.DATE, S.IS_INTERACTIVE " +
            "FROM TBL_SHORT_FORM S " +
            "INNER JOIN TBL_MEMBER M ON S.MEMBER_NO " +
            "WHERE S.SHORT_FORM_NO = :shortFormNo", nativeQuery = true)
    ShortForm findShortFormByShortFormNo(@Param("shortFormNo") Long shortFormNo);
}
