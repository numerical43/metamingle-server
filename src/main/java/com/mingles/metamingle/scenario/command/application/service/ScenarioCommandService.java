package com.mingles.metamingle.scenario.command.application.service;

import com.mingles.metamingle.scenario.command.application.dto.request.SaveScenarioRequest;
import com.mingles.metamingle.scenario.command.domain.aggregate.document.Scenario;
import com.mingles.metamingle.scenario.command.domain.mongo.ScenarioCommandRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScenarioCommandService {

    private final ScenarioCommandRepository scenarioCommandRepository;

    public void saveCreatedScenario(SaveScenarioRequest request) {
        Scenario scenario = scenarioCommandRepository.save(Scenario
                                                    .builder()
                                                    .prompt(request.getPrompt())
                                                    .scenarioContent(request.getScenarioContent())
                                                    .build());
    }

}