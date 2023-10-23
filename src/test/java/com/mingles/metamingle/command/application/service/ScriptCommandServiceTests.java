package com.mingles.metamingle.command.application.service;

import com.mingles.metamingle.script.command.application.dto.request.CreateScriptRequest;
import com.mingles.metamingle.script.command.application.service.ScriptCommandService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
@SpringBootTest
public class ScriptCommandServiceTests {

    @Autowired
    private ScriptCommandService scriptCommandService;



    @Test
    @DisplayName("AI 대본 생성 테스트 : 한글이 아닌 언어 사용 시 예외처리")
    public void testCreateScriptKoreanException() {

        CreateScriptRequest request = new CreateScriptRequest(1L, "لَقَدْ تَغَيَّرَ الوَضْعُ تَماماً");

        assertThatThrownBy(() -> scriptCommandService.createScript(1L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("한국어가 아닌 언어는 사용이 불가능합니다.");
    }

    @Test
    @DisplayName("AI 대본 생성 테스트 : 1000자 이상 입력 시 예외처리")
    public void testCreateScriptLengthTooLongException() {

        StringBuilder str = new StringBuilder("가나다라마바사아자차");
        str.append("가나다라마바사아자차".repeat(100));

        CreateScriptRequest request = new CreateScriptRequest(1L, str.toString());

        assertThatThrownBy(() -> scriptCommandService.createScript(1L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("글자수는 1000자를 넘길 수 없습니다.");
    }

    @Test
    @DisplayName("AI 대본 생성 테스트 : 공백 시 예외처리")
    public void testCreateScriptLengthTooShortException() {

        String str = "  ";

        CreateScriptRequest request = new CreateScriptRequest(1L, str);

        assertThatThrownBy(() -> scriptCommandService.createScript(1L, request))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("글자수는 0자 이상이어야 합니다.");
    }

}