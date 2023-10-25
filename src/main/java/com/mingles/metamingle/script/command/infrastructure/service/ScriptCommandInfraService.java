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

//    private final WebClient webClient = WebClient.builder().baseUrl("http://172.17.156.234:8000").build();

    public ScriptInfraResponse getAiScript(String content) {

//        Map<String, String> bodyJson = new HashMap<>();
//        bodyJson.put("user_input", content);
//
//        ScriptInfraResponse aiScript = webClient.post()
//                .uri("/chatbot/chat")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(bodyJson)
//                .retrieve()
//                .bodyToMono(ScriptInfraResponse.class)
//                .block();
//        System.out.println("aiScript = " + aiScript.toString());
//
//        return aiScript;

        System.out.println("영상 제목: \"놀라운 신조어! 발이 넓다는 뜻은?\"\n\n오프닝: (자연스럽게 웃으며) 안녕! 오늘은 어제 내가 들은 신조어에 대해서 이야기하려고 왔어. 궁금하다면 계속해서 함께해봐!\n\n내용: 어제 어떤 사람이 나에게 \"너 발이 넓다\"라고 했어. 나는 발이 다른 사람들보다 작아서 무슨 뜻인지 이해하지 못했지. 근데 나중에 알고보니 그게 친구가 많다는 뜻이라고 해! (깜짝 놀란 표정으로) 정말 신기하지 않아?\n\n결론: 언어는 정말 신기하고 다양하게 변화한다는 걸 알게됐어. 하나의 문장에 담긴 뜻을 알아내는 것도 재미있고, 새로운 표현과 속어를 배우는 것도 흥미로워. 이렇게 저마다의 고유한 말이 있어서 언어가 더욱 풍부해지는거야. 이런 새로운 경험을 통해 세상을 더 깊게 알아갈 수 있다는 건 정말 멋지지 않아? 함께 언어의 매력을 발견해보자!");

        return new ScriptInfraResponse("영상 제목: \"놀라운 신조어! 발이 넓다는 뜻은?\"\n\n오프닝: (자연스럽게 웃으며) 안녕! 오늘은 어제 내가 들은 신조어에 대해서 이야기하려고 왔어. 궁금하다면 계속해서 함께해봐!\n\n내용: 어제 어떤 사람이 나에게 \"너 발이 넓다\"라고 했어. 나는 발이 다른 사람들보다 작아서 무슨 뜻인지 이해하지 못했지. 근데 나중에 알고보니 그게 친구가 많다는 뜻이라고 해! (깜짝 놀란 표정으로) 정말 신기하지 않아?\n\n결론: 언어는 정말 신기하고 다양하게 변화한다는 걸 알게됐어. 하나의 문장에 담긴 뜻을 알아내는 것도 재미있고, 새로운 표현과 속어를 배우는 것도 흥미로워. 이렇게 저마다의 고유한 말이 있어서 언어가 더욱 풍부해지는거야. 이런 새로운 경험을 통해 세상을 더 깊게 알아갈 수 있다는 건 정말 멋지지 않아? 함께 언어의 매력을 발견해보자!");

    }
}
