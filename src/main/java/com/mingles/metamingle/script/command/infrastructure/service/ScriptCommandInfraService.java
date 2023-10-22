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

    private final WebClient webClient = WebClient.builder().baseUrl("").build();

    public ScriptInfraResponse getAiScript(String content) {

//        Map<String, String> bodyJson = new HashMap<>();
//        bodyJson.put("content", content);

//        ScriptInfraResponse aiScript = webClient.post()
//                .uri("/")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(bodyJson)
//                .retrieve()
//                .bodyToMono(ScriptInfraResponse.class)
//                .block();
//
//        return aiScript;

        return new ScriptInfraResponse("ai script result");

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