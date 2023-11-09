package com.mingles.metamingle.scenario.command.infrastructure.service;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScenarioCommandInfraService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.66:8011/").build();

    //SSE 를 이용한 텍스트 실시간 스트리밍
    public Flux<ServerSentEvent<String>> getStreamingData(String message) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("text", message);

        return webClient
                .post()
                .uri("chatbot/test_text")
                .bodyValue(bodyJson)
                .retrieve()
                .bodyToFlux(String.class)
                .map(data -> ServerSentEvent.<String>builder()
                        .data(data)
                        .build());

    }






}


