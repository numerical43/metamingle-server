package com.mingles.metamingle.global.infra;

import com.mingles.metamingle.quiz.command.application.dto.response.QuizCommandInfraResponse;
import com.mingles.metamingle.quiz.command.application.dto.response.QuizCommandResponse;
import com.mingles.metamingle.quiz.command.application.service.QuizCommandService;
import com.mingles.metamingle.scenario.command.application.dto.request.SaveScenarioRequest;
import com.mingles.metamingle.scenario.command.application.dto.response.BGMResponse;
import com.mingles.metamingle.scenario.command.application.dto.response.BgImageResponse;
import com.mingles.metamingle.scenario.command.application.service.ScenarioCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.MediaType;
import org.springframework.http.client.MultipartBodyBuilder;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AiInfraService {

    private WebClient webClient;

    private final ScenarioCommandService scenarioCommandService;
    private final QuizCommandService quizCommandService;

    //AI 세션 변경 확인
    public void setWebClient(String aiAddr) {
        this.webClient = WebClient.builder().baseUrl(aiAddr).build();
    }


    //SSE 를 이용한 텍스트 실시간 스트리밍
    public Flux<ServerSentEvent<String>> getStreamingData(String message) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("text", message);

        StringBuilder stringBuilder = new StringBuilder();

        return webClient
                .post()
                .uri("/chatbot/make_script")
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
    }


    // 배경
    public String getBackGroundImage(String message) {

//        Map<String, String> bodyJson = new HashMap<>();
//        bodyJson.put("text", message);
//
//        BgImageResponse background = webClient.post()
//                .uri("/hatbot/image_connect")
//                .bodyValue(bodyJson)
//                .retrieve()
//                .bodyToMono(BgImageResponse.class)
//                .block();
//
//        return background.getPlace();

        return "campus";
    }

    // BGM
    public String getBGM(String message) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("text", message);

        BGMResponse bgm = webClient.post()
                .uri("/chatbot/music_connect")
                .bodyValue(bodyJson)
                .retrieve()
                .bodyToMono(BGMResponse.class)
                .block();

        return bgm.getMood();

//        return "Chill";
    }


    public QuizCommandResponse getQuizFromScript(String text) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("text", text);

        QuizCommandInfraResponse quiz = webClient.post()
                .uri("/chatbot/quiz_gen")
                .bodyValue(bodyJson)
                .retrieve()
                .bodyToMono(QuizCommandInfraResponse.class)
                .block();

        UUID uuid = UUID.randomUUID();

        return quizCommandService.saveQuizWithUUID(uuid, quiz.getKorea(), quiz.getEnglish(), quiz.getIsquiz());
//        return quizCommandService.saveQuizWithUUID(uuid, "테스트", "test", "yes");
    }


    private MultipartFile dataBufferToMultipartFile(String fileKeyName, Flux<DataBuffer> responseBody) {
        byte[] byteArray = responseBody
                .collectList()
                .map(dataBuffers -> {
                    try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                        dataBuffers.forEach(buffer -> {
                            byte[] bytes = new byte[buffer.readableByteCount()];
                            buffer.read(bytes);
                            try {
                                outputStream.write(bytes);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            DataBufferUtils.release(buffer);
                        });
                        return outputStream.toByteArray();
                    } catch (Exception e) {
                        throw new IllegalArgumentException(e.getMessage());
                    }
                })
                .block();

        return new MockMultipartFile(fileKeyName, fileKeyName, MediaType.MULTIPART_FORM_DATA_VALUE, byteArray);
    }

    public MultipartFile sendToAIForEngSub(Resource file, String fileKeyName) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file", file);
        bodyBuilder.part("file_uuid", fileKeyName);

        System.out.println("영어 자막 영상 처리 중");

        Flux<DataBuffer> responseBody = webClient.post()
                .uri("/en_script_video/")
                .contentType(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        return dataBufferToMultipartFile(fileKeyName, responseBody);
    }

    public MultipartFile sendToAIForKrSub(String fileKeyName) {

        MultipartBodyBuilder bodyBuilder = new MultipartBodyBuilder();
        bodyBuilder.part("file_uuid", fileKeyName);

        System.out.println("한글 자막 영상 처리 중 : 인터랙티브 무비");

        Flux<DataBuffer> responseBody = webClient.post()
                .uri("/kr_script_video/")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromMultipartData(bodyBuilder.build()))
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .retrieve()
                .bodyToFlux(DataBuffer.class);

        return dataBufferToMultipartFile(fileKeyName, responseBody);
    }


}
