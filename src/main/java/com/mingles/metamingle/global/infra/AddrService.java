package com.mingles.metamingle.global.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AddrService {

    private final AiInfraService aiInfraService;

    public void updateAddr(String aiAddr) {
        aiInfraService.setAiAddr(aiAddr);
    }

    public String getAddr() {
        return aiInfraService.getAiAddr();
    }
}
