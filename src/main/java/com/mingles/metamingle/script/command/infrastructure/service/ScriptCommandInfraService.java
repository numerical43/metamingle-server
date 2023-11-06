package com.mingles.metamingle.script.command.infrastructure.service;
import com.mingles.metamingle.script.command.application.dto.response.ScriptInfraResponse;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

@Service
public class ScriptCommandInfraService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://172.17.156.234:8000").build();

    public ScriptInfraResponse getAiScript(String content) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("user_input", content);

//        ScriptInfraResponse aiScript = webClient.post()
//                .uri("/chatbot/chat")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(bodyJson)
//                .retrieve()
//                .bodyToMono(ScriptInfraResponse.class)
//                .block();

        return new ScriptInfraResponse("testing-output");

    }

    public Flux<ServerSentEvent<String>> getStreamingData(String message) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("message", message);

//        return webClient
//                .post()
//                .uri("/chatbot/test_text")
//                .bodyValue(bodyJson)
//                .retrieve()
//                .bodyToFlux(String.class)
//                .map(data -> ServerSentEvent.<String>builder()
//                        .data(data)
//                        .build());

        Flux<String> firstResponse = Flux.just("First Response!");
        Flux<String> secondResponse = Flux.just("Second Response!!");
        Flux<String> thirdResponse = Flux.just("Third Response!!!");

        Flux<ServerSentEvent<String>> responseStream = Flux.concat(
                firstResponse.map(data -> ServerSentEvent.<String>builder().data(data).build()),
                secondResponse.delayElements(Duration.ofSeconds(1)).map(data -> ServerSentEvent.<String>builder().data(data).build()),
                thirdResponse.delayElements(Duration.ofSeconds(1)).map(data -> ServerSentEvent.<String>builder().data(data).build())
        );

        return responseStream;
    }
}
