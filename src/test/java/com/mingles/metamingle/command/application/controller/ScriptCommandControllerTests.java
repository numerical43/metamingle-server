package com.mingles.metamingle.command.application.controller;

import com.mingles.metamingle.script.command.application.controller.ScriptCommandController;
import com.mingles.metamingle.script.command.application.dto.request.CreateScriptRequest;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ScriptCommandControllerTests {

    @Autowired
    private ScriptCommandController scriptCommandController;

    private MockMvc mock;

    @BeforeEach
    void setup() {
        mock = MockMvcBuilders.standaloneSetup(scriptCommandController).build();
    }

    @Test
    @DisplayName("AI 대본 컨트롤러 : success")
    void createScriptControllerTest() throws Exception {

        CreateScriptRequest request = new CreateScriptRequest(1L, "컨트롤러 테스트입니다!!");

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("shortFormNo", request.getShortFormNo());
        jsonObject.put("content", request.getContent());

        mock.perform(post("/api/script")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(String.valueOf(jsonObject)))
                .andExpect(status().isCreated());

    }
}