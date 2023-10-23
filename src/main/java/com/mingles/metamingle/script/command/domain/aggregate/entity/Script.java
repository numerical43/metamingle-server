package com.mingles.metamingle.script.command.domain.aggregate.entity;

import com.mingles.metamingle.script.command.domain.aggregate.vo.ScriptMemberNoVO;
import com.mingles.metamingle.script.command.domain.aggregate.vo.ShortFormNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Table(name = "TBL_SCRIPT")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class Script implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scriptNo;

    @Embedded
    private ScriptMemberNoVO scriptMemberNoVO;

    @Embedded
    private ShortFormNoVO shortFormNoVO;

    @Column(length = 1000)
    private String scriptContent;

    @Column
    private LocalDate uploadDate;

    @Builder
    public Script(ScriptMemberNoVO scriptMemberNoVO, ShortFormNoVO shortFormNoVO, String scriptContent, LocalDate uploadDate) {
        this.scriptMemberNoVO = scriptMemberNoVO;
        this.shortFormNoVO = shortFormNoVO;
        this.scriptContent = scriptContent;
        this.uploadDate = uploadDate;
    }
}