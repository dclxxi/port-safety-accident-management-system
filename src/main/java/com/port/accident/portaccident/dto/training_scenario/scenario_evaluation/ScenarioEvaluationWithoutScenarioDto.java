package com.port.accident.portaccident.dto.training_scenario.scenario_evaluation;

import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.enums.SuitableCheck;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ScenarioEvaluationWithoutScenarioDto {
    private Integer id;
    private String name;
    private SuitableCheck developmentStandard1;
    private SuitableCheck developmentStandard2;
    private SuitableCheck developmentStandard3;
    private SuitableCheck possibleStandard1;
    private SuitableCheck possibleStandard2;
    private SuitableCheck possibleStandard3;
    private SuitableCheck completeStandard1;
    private SuitableCheck completeStandard2;
    private SuitableCheck completeStandard3;

    @Builder
    public ScenarioEvaluationWithoutScenarioDto(Integer id, String name,
                                                SuitableCheck developmentStandard1, SuitableCheck developmentStandard2, SuitableCheck developmentStandard3,
                                                SuitableCheck possibleStandard1, SuitableCheck possibleStandard2, SuitableCheck possibleStandard3,
                                                SuitableCheck completeStandard1, SuitableCheck completeStandard2, SuitableCheck completeStandard3) {
        this.id = id;
        this.name = name;
        this.developmentStandard1 = developmentStandard1;
        this.developmentStandard2 = developmentStandard2;
        this.developmentStandard3 = developmentStandard3;
        this.possibleStandard1 = possibleStandard1;
        this.possibleStandard2 = possibleStandard2;
        this.possibleStandard3 = possibleStandard3;
        this.completeStandard1 = completeStandard1;
        this.completeStandard2 = completeStandard2;
        this.completeStandard3 = completeStandard3;
    }

    public ScenarioEvaluation toEntity() {
        return ScenarioEvaluation.builder()
                .id(id)
                .name(name)
                .developmentStandard1(developmentStandard1)
                .developmentStandard2(developmentStandard2)
                .developmentStandard3(developmentStandard3)
                .possibleStandard1(possibleStandard1)
                .possibleStandard2(possibleStandard2)
                .possibleStandard3(possibleStandard3)
                .completeStandard1(completeStandard1)
                .completeStandard2(completeStandard2)
                .completeStandard3(completeStandard3)
                .build();
    }
}
