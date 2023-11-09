package com.mingles.metamingle.scenario.command.domain.aggregate.document;

import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.*;

@Document(collection = "scenario")
public class Scenario{

    @Id
    private String id;

    @Field(name = "prompt")
    private String prompt;

    @Field(name = "scenario_content")
    private String scenarioContent;

    @Builder
    public Scenario(String prompt, String scenarioContent) {
        this.prompt = prompt;
        this.scenarioContent = scenarioContent;
    }
}