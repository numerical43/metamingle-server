package com.mingles.metamingle.script.command.domain.service;

import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

@Service
public class ScriptCommandDomainService {

    public void validateScriptLanguage(String scriptContent) {

        boolean isKorean = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", scriptContent);
//        boolean isKoreanOrEnglish = Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", scriptContent);

        System.out.println("isKorean = " + isKorean);

        if(!isKorean) {
            throw new IllegalArgumentException("한국어가 아닌 언어는 사용이 불가능합니다.");
        }

    }

    public void validateScriptLength(String scriptContent) {

        int length = scriptContent.getBytes(Charset.defaultCharset()).length;

        System.out.println("length = " + length);

        if(length > 1000) {
            throw new IllegalArgumentException("글자수는 1000자를 넘길 수 없습니다.");
        }

    }

}