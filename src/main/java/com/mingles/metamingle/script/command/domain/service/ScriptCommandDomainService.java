package com.mingles.metamingle.script.command.domain.service;

import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.util.regex.Pattern;

@Service
public class ScriptCommandDomainService {

    public boolean validateScriptLanguage(String scriptContent) {

        boolean isKorean = Pattern.matches("^[ㄱ-ㅎ가-힣]*$", scriptContent);
//        boolean isKoreanOrEnglish = Pattern.matches("^[ㄱ-ㅎ가-힣a-zA-Z]*$", scriptContent);

        System.out.println("isKorean = " + isKorean);

        return isKorean;

    }

    public void validateScriptLength(String scriptContent) {

        int length = scriptContent.getBytes(Charset.defaultCharset()).length;

        System.out.println("length = " + length);

    }

}