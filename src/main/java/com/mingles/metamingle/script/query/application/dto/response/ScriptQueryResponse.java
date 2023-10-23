package com.mingles.metamingle.script.query.application.dto.response;

import com.mingles.metamingle.script.command.domain.aggregate.entity.Script;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ScriptQueryResponse {

    private Long scriptMemberNo;
    private Long shortFormNo;
    private String scriptContent;
    private LocalDate uploadDate;

    public static ScriptQueryResponse from(Script script) {
        return new ScriptQueryResponse(
                script.getScriptMemberNoVO().getMemberNo(),
                script.getShortFormNoVO().getShortFormNo(),
                script.getAiContent(),
                script.getUploadDate()
        );
    }
}
