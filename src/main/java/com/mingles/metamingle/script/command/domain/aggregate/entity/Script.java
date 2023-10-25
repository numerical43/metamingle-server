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
    private String memberContent;

    @Column(length = 1000)
    private String aiContent;

    @Column
    private LocalDate uploadDate;

    @Builder
    public Script(ScriptMemberNoVO scriptMemberNoVO, ShortFormNoVO shortFormNoVO, String memberContent, String aiContent, LocalDate uploadDate) {
        this.scriptMemberNoVO = scriptMemberNoVO;
        this.shortFormNoVO = shortFormNoVO;
        this.memberContent = memberContent;
        this.aiContent = aiContent;
        this.uploadDate = uploadDate;
    }

    public void update(String aiContent) {
        this.aiContent = aiContent;
    }
}