package com.mingles.metamingle.scenario.command.domain.mongo;

import com.mingles.metamingle.scenario.command.domain.aggregate.entity.Scenario;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScenarioCommandRepository extends MongoRepository<Scenario, String> {
}