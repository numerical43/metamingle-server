package com.mingles.metamingle.shortform.query.domain.repository;

import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormListResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.GetShortFormResponse;
import com.mingles.metamingle.shortform.query.application.dto.response.ShortFormInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShortFormQueryRepository extends JpaRepository<ShortForm, Long> {


    List<ShortForm> findAll();

    ShortForm findShortFormByShortFormNo(Long shortFormNo);
}
