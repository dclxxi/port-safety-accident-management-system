package com.port.accident.portaccident.domain.training_scenario.scenario_evaluation;

import com.port.accident.portaccident.domain.training_scenario.Scenario;
import com.port.accident.portaccident.dto.training_scenario.scenario_evaluation.ScenarioEvaluationDto;
import com.port.accident.portaccident.enums.SuitableCheck;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;

@Table(name = "scenario_evaluation")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScenarioEvaluation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenario_evaluation_id") // 시나리오 평가 id
    private Integer id;

    @Column(name = "scenario_evaluation_name") // 시나리오명
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_develop_standard")
    private SuitableCheck developmentStandard1;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_develop_standard2")
    private SuitableCheck developmentStandard2;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_develop_standard3")
    private SuitableCheck developmentStandard3;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_possible_standard1")
    private SuitableCheck possibleStandard1;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_possible_standard2")
    private SuitableCheck possibleStandard2;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_possible_standard3")
    private SuitableCheck possibleStandard3;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_complete_standard1")
    private SuitableCheck completeStandard1;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_complete_standard2")
    private SuitableCheck completeStandard2;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_evaluation_complete_standard3")
    private SuitableCheck completeStandard3;

//    @OneToMany(mappedBy = "scenarioEvaluation")  // 시나리오 평가 기준
//    private List<ScenarioEvaluationStandard> scenarioEvaluationStandardList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "scenario_id") // 시나리오 id
    private Scenario scenario;

    @Builder
    public ScenarioEvaluation(Integer id, String name, Scenario scenario,
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
        this.scenario = scenario;
    }

    @Transactional(readOnly = true)
    public void update(ScenarioEvaluationDto scenarioEvaluationDto) {
        this.name = scenarioEvaluationDto.getName();
        this.developmentStandard1 = scenarioEvaluationDto.getDevelopmentStandard1();
        this.developmentStandard2 = scenarioEvaluationDto.getDevelopmentStandard2();
        this.developmentStandard3 = scenarioEvaluationDto.getDevelopmentStandard3();
        this.possibleStandard1 = scenarioEvaluationDto.getPossibleStandard1();
        this.possibleStandard2 = scenarioEvaluationDto.getPossibleStandard2();
        this.possibleStandard3 = scenarioEvaluationDto.getPossibleStandard3();
        this.completeStandard1 = scenarioEvaluationDto.getCompleteStandard1();
        this.completeStandard2 = scenarioEvaluationDto.getCompleteStandard2();
        this.completeStandard3 = scenarioEvaluationDto.getCompleteStandard3();
    }
}