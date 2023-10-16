package com.mingles.metamingle.shortform.command.domain.aggregate.entity;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "TBL_SHORT_FORM")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ShortForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shortFormId;

    @Column
    private String title;

    @Column
    private String url;

    @Column
    private int shortFormLength;

    @Column
    private Boolean isInteractive;

}
