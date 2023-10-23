package com.mingles.metamingle.script.command.infrastructure.service;
import com.mingles.metamingle.script.command.application.dto.response.ScriptInfraResponse;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ScriptCommandInfraService {

    private final WebClient webClient = WebClient.builder().baseUrl("http://172.17.156.234:8000").build();

    public ScriptInfraResponse getAiScript(String content) {

        Map<String, String> bodyJson = new HashMap<>();
        bodyJson.put("user_input", content);

        ScriptInfraResponse aiScript = webClient.post()
                .uri("/chatbot/chat")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(bodyJson)
                .retrieve()
                .bodyToMono(ScriptInfraResponse.class)
                .block();
        System.out.println("aiScript = " + aiScript.toString());

        return aiScript;

//        return new ScriptInfraResponse("영상 제목: \"이런 신기한 속담도 있었네?\"\n" +
//                "오프닝:\n" +
//                "(활기찬 모습으로) 안녕! 오늘 말하고 싶은 이야기가 생겨서 왔어. 바로 어제 나한테 생긴 신기한 경험에 대해서 말해볼게!\n" +
//                "내용:\n" +
//                "(생각하는 표정으로) 어제, 누군가 나에게 아주 이상한 말을 던졌어: \"너 발이 넓다\". 발이 작은 나는 이 말을 이해하지 못했지!  그치만, 알고보니 그게 단순히 친구가 많다는 뜻이라니!\n" +
//                "(놀라는 표정으로) 이런 아주 신기한 속담이 있었다니! 나, 평생 동안 상상도 못했었어.\n" +
//                "결론:\n" +
//                "(웃으며) 이제부터 나는 \"발이 넓다\"고 했을 때, 그게 내가 많은 사람들과 괜찮은 관계를 갖고 있다는 속담의 하나라는 걸 알게 된 거야. " +
//                "일상 속에서 흔히 사용되는 말들 중에 어떤 깊은 뜻이 숨어있는지 모르니까, 새로운 것을 알게 되는건 정말 재밌어. 공부 하는건 정말 재밌있는 일이야!");

    }
}


/*
from fastapi import FastAPI

app = FastAPI()

@app.post("/")
async def process_json(data: dict):
    text = data.get("text")
    # Process the text here
    response_text = process_content(text)
    return {"response": response_text}
 */