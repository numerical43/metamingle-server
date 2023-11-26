package com.mingles.metamingle.global.infra;

import com.mingles.metamingle.interactivemovie.command.application.service.InteractiveMovieCommandService;
import com.mingles.metamingle.shortform.command.application.service.ShortFormFirebaseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddrController {

    private final AiInfraService aiInfraService;
    private final ShortFormFirebaseService shortFormFirebaseService;
    private final InteractiveMovieCommandService interactiveMovieCommandService;

    @PostMapping ("/infra/update-addr")
    public String updateInfraAddr(@RequestBody String aiAddr) {
        aiInfraService.setWebClient(aiAddr);
        shortFormFirebaseService.setWebClient(aiAddr);
        interactiveMovieCommandService.setWebClient(aiAddr);

        return aiAddr;
    }
}
