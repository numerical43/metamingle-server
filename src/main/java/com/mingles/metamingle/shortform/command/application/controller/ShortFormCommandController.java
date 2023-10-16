package com.mingles.metamingle.shortform.command.application.controller;

import com.mingles.metamingle.shortform.command.application.service.ShortFormCommandService;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
public class ShortFormCommandController {

    private final ShortFormCommandService shortFormCommandService;

    // 숏폼 생성

    // 숏폼 삭제

    // 숏폼 수정

}
