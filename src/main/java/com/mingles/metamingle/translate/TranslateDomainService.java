package com.mingles.metamingle.translate;

import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class TranslateDomainService {

    public boolean detectSourceLanguageIsEnglish(String text) {
        String regex = "^[a-zA-Z\\s\\p{P}]*$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        System.out.println("matcher.matches() = " + matcher.matches());
        return matcher.matches();
    }
}
