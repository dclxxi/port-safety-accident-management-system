package com.port.accident.portaccident.repository.training_scenario;

import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.dto.training_scenario_result.EvaluationSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScenarioEvaluationRepositoryCustom {

    Page<ScenarioEvaluation> searchPageScenarioEvaluation(EvaluationSearchCondition condition, Pageable pageable);
    String setScenarioEvaluationName(Integer scenarioId, String scenarioName);
}
