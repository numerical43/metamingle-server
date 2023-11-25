package com.mingles.metamingle.interactivemovie.command.domain.aggregate.entity;

import com.mingles.metamingle.interactivemovie.command.domain.aggregate.vo.MemberNoVO;
import com.mingles.metamingle.interactivemovie.command.domain.aggregate.vo.ShortFormNoVO;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Table(name = "TBL_INTERACTIVE_MOVIE")
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class InteractiveMovie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long interactiveMovieNo;

    @Column
    private String title;

    @Column
    private String urlKr;

    @Column
    private String thumbnailUrlKr;

    @Column
    private String urlEng;

    @Column
    private String thumbnailUrlEng;

    @Column
    private String description;

    @Column
    private String choice;

    @Column
    private Date date;

    @Column
    private int sequence;

    @Embedded
    private ShortFormNoVO shortFormNoVO;

    @Embedded
    private MemberNoVO memberNoVO;

    @Builder
    public InteractiveMovie(String title, String urlKr, String thumbnailUrlKr, String urlEng, String thumbnailUrlEng, String description, String choice, Date date, int sequence,
                            ShortFormNoVO shortFormNoVO, MemberNoVO memberNoVO) {
        this.title = title;
        this.urlKr = urlKr;
        this.thumbnailUrlKr = thumbnailUrlKr;
        this.urlEng = urlEng;
        this.thumbnailUrlEng = thumbnailUrlEng;
        this.description = description;
        this.choice = choice;
        this.date = date;
        this.sequence = sequence;
        this.shortFormNoVO = shortFormNoVO;
        this.memberNoVO = memberNoVO;
    }

}
