package com.mingles.metamingle.global.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddrController {

    private final AiInfraService aiInfraService;

    @PostMapping ("/infra/update-addr")
    public String updateInfraAddr(@RequestBody String aiAddr) {
        aiInfraService.setWebClient(aiAddr);
        return aiAddr;
    }
}
