package com.mingles.metamingle.scenario.command.infrastructure.service;
import com.mingles.metamingle.scenario.command.application.dto.request.SaveScenarioRequest;
import com.mingles.metamingle.scenario.command.application.dto.response.BGMResponse;
import com.mingles.metamingle.scenario.command.application.dto.response.BgImageResponse;
import com.mingles.metamingle.scenario.command.application.service.ScenarioCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ScenarioCommandInfraService {

    private final ScenarioCommandService scenarioCommandService;
    private final WebClient webClient = WebClient.builder().baseUrl("http://192.168.0.66:8011/").build();

    //SSE 를 이용한 텍스트 실시간 스트리밍
    public Flux<ServerSentEvent<String>> getStreamingData(String message) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("text", message);

        StringBuilder stringBuilder = new StringBuilder();

        return webClient
                .post()
                .uri("chatbot/test_text")
                .bodyValue(bodyJson)
                .retrieve()
                .bodyToFlux(String.class)
                .doOnNext(stringBuilder::append)
                .doOnComplete(() -> {
                    String finalString = stringBuilder.toString();
                    SaveScenarioRequest request = new SaveScenarioRequest(message, finalString);
                    scenarioCommandService.saveCreatedScenario(request);
                })
                .map(data -> ServerSentEvent.<String>builder()
                        .data(data)
                        .build());

//        Mono<String> firstResponse = Mono.just("일 \n");
//        Mono<String> secondResponse = Mono.just("이 \n");
//        Mono<String> thirdResponse = Mono.just("삼 \n");
//        Mono<String> fourthResponse = Mono.just("사 \n");
//        Mono<String> fifthResponse = Mono.just("오 ");
//
//        Flux<ServerSentEvent<String>> responseStream = Flux.concat(
//                firstResponse.map(data -> ServerSentEvent.<String>builder().data(data).build()),
//                secondResponse.delayElement(Duration.ofSeconds(1)).map(data -> ServerSentEvent.<String>builder().data(data).build()),
//                thirdResponse.delayElement(Duration.ofSeconds(1)).map(data -> ServerSentEvent.<String>builder().data(data).build()),
//                fourthResponse.delayElement(Duration.ofSeconds(1)).map(data -> ServerSentEvent.<String>builder().data(data).build()),
//                fifthResponse.delayElement(Duration.ofSeconds(1)).map(data -> ServerSentEvent.<String>builder().data(data).build())
//        );
//
//        return responseStream;

    }


    // 배경
    public String getBackGroundImage(String message) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("text", message);

        BgImageResponse background = webClient.post()
                    .uri("chatbot/image_connect")
                    .bodyValue(bodyJson)
                    .retrieve()
                    .bodyToMono(BgImageResponse.class)
                    .block();

        System.out.println("background.getPlace() = " + background.getPlace());
        return background.getPlace();

//        return "campus";
    }

    // BGM
    public String getBGM(String message) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("text", message);

        BGMResponse bgm = webClient.post()
                .uri("chatbot/music_connect")
                .bodyValue(bodyJson)
                .retrieve()
                .bodyToMono(BGMResponse.class)
                .block();


        System.out.println("bgm.getMood() = " + bgm.getMood());
        return bgm.getMood();

//        return "Chill";
    }



}


