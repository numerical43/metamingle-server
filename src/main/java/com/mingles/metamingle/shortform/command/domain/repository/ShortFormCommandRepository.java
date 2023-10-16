package com.mingles.metamingle.shortform.command.domain.repository;

import com.mingles.metamingle.shortform.command.domain.aggregate.entity.ShortForm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortFormCommandRepository extends JpaRepository<ShortForm, Long> {



}
