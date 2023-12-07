package com.mingles.metamingle.translate;


import com.mingles.metamingle.translate.dto.TranslationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TranslateController {

    private final TranslateDomainService translateDomainService;
    private final TranslateInfraService translateInfraService;

    @PostMapping(value = "/translate")
    public String translateChat(@RequestBody TranslationRequest request) {

        if(translateDomainService.detectSourceLanguageIsEnglish(request.getText())) {
            return translateInfraService.translateTextToKorean(request.getText());
        } else return translateInfraService.translateTextToEnglish(request.getText());

    }
}
