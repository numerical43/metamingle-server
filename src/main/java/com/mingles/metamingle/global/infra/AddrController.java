package com.mingles.metamingle.global.infra;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class AddrController {

    private final AddrService addrService;

    @PostMapping ("/infra/update-addr")
    public String updateInfraAddr(@RequestBody String aiAddr) {
        addrService.updateAddr(aiAddr);
        return addrService.getAddr();
    }

    @GetMapping("/infra/get-addr")
    public String getInfraAddr() {
        return addrService.getAddr();
    }
}
