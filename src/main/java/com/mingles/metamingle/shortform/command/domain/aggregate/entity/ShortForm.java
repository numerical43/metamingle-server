package com.mingles.metamingle.shortform.command.domain.aggregate.entity;

import com.mingles.metamingle.shortform.command.domain.aggregate.vo.MemberNoVO;
import com.mingles.metamingle.shortform.command.domain.aggregate.vo.QuizUUIDVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "TBL_SHORT_FORM")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ShortForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long shortFormNo;

    @Column
    private String title;

    @Column
    private String thumbnailUrlKr;

    @Column
    private String urlKr;
    @Column
    private String thumbnailUrlEng;

    @Column
    private String urlEng;

    @Column
    private String description;

    @Column
    private Date date;

    @Column
    private Boolean isInteractive;

    @Embedded
    private MemberNoVO memberNoVO;

    @Embedded
    private QuizUUIDVO quizUUIDVO;

    @Builder
    public ShortForm(String title, String thumbnailUrlKr, String urlKr, String thumbnailUrlEng, String urlEng, String description, Date date,
                     Boolean isInteractive, MemberNoVO memberNoVO, QuizUUIDVO quizUUIDVO) {
        this.title = title;
        this.thumbnailUrlKr = thumbnailUrlKr;
        this.urlKr = urlKr;
        this.thumbnailUrlEng = thumbnailUrlEng;
        this.urlEng = urlEng;
        this.description = description;
        this.date = date;
        this.isInteractive = isInteractive;
        this.memberNoVO = memberNoVO;
        this.quizUUIDVO = quizUUIDVO;
    }

}
