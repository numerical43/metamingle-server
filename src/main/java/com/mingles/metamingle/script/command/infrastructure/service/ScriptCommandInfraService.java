package com.mingles.metamingle.script.command.infrastructure.service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

@Service
public class ScriptCommandInfraService {

    private WebClient webClient = WebClient.builder().baseUrl("").build();

    public String getAiScript(String content) {

//        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
//        bodyBuilder.part("text", content);

//        String aiScript = webClient.post()
//                .uri("/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(BodyInserters.fromValue(bodyBuilder.build()))
//                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
//                .accept(MediaType.MULTIPART_FORM_DATA)
//                .retrieve()
//                .bodyToMono(String.class)
//                .block();
//
//        return aiScript;

        return "testing";

    }
}