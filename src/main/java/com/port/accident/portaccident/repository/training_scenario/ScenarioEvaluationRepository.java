package com.port.accident.portaccident.repository.training_scenario;

import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ScenarioEvaluationRepository extends JpaRepository<ScenarioEvaluation, Integer>, ScenarioEvaluationRepositoryCustom {

    @Override
    Optional<ScenarioEvaluation> findById(Integer integer);

    @Query("select s from ScenarioEvaluation s where s.name = :name")
    Optional<ScenarioEvaluation> findByName(@Param("name") String name);

    Integer countByScenarioId(Integer scenarioId);
}
