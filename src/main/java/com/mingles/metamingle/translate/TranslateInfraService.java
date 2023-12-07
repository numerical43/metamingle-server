package com.mingles.metamingle.translate;

import com.mingles.metamingle.translate.dto.Translation;
import com.mingles.metamingle.translate.dto.TranslationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TranslateInfraService {

    @Value("${deepl-key}")
    private String deeplKey;
    private final WebClient webClient = WebClient.builder().baseUrl("https://api-free.deepl.com/v2/translate").build();

    public String translateTextToEnglish(String text) {
        String url = UriComponentsBuilder.fromUriString("")
                .queryParam("auth_key", deeplKey)
                .queryParam("text", text)
                .queryParam("target_lang", "EN")
                .build()
                .toUriString();

        TranslationResponse response = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(TranslationResponse.class)
                .block();

        return response.getTranslations().get(0).getText();
    }

    public String translateTextToKorean(String text) {
        String url = UriComponentsBuilder.fromUriString("")
                .queryParam("auth_key", deeplKey)
                .queryParam("text", text)
                .queryParam("target_lang", "KO")
                .build()
                .toUriString();

        TranslationResponse response = webClient.post()
                .uri(url)
                .retrieve()
                .bodyToMono(TranslationResponse.class)
                .block();

        return response.getTranslations().get(0).getText();
    }

}
