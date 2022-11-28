package com.port.accident.portaccident.dto.training_scenario;

import com.port.accident.portaccident.domain.training_scenario.Scenario;
import com.port.accident.portaccident.domain.training_scenario.elements.AccidentPortFacility;
import com.port.accident.portaccident.domain.training_scenario.elements.AccidentResponseActivity;
import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.enums.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class ScenarioDto {

    private Integer id;
    private String name;
    private IncidentLevel incidentLevel;
    private IncidentImpact incidentImpact;
    private IncidentType incidentType;
    private IncidentDetailType incidentDetailType;
    private TrainingPlace portArea;
    private List<AccidentPortFacility> accidentPortFacilityList = new ArrayList<>();
    private List<AccidentResponseActivity> accidentResponseActivityList = new ArrayList<>();
    private List<ScenarioEvaluation> scenarioEvaluationList = new ArrayList<>();

    @Builder
    public ScenarioDto(Integer id, String name, IncidentLevel incidentLevel, IncidentImpact incidentImpact, IncidentType incidentType,
                       IncidentDetailType incidentDetailType, TrainingPlace portArea) {
//                       List<AccidentPortFacility> accidentPortFacilityList,
//                       List<AccidentResponseActivity> accidentResponseActivities,
//                       List<ScenarioEvaluation> scenarioEvaluationList
        this.id = id;
        this.name = name;
        this.incidentLevel = incidentLevel;
        this.incidentImpact = incidentImpact;
        this.incidentType = incidentType;
        this.incidentDetailType = incidentDetailType;
        this.portArea = portArea;
//        this.accidentPortFacilityList = accidentPortFacilityList;
//        this.accidentResponseActivities = accidentResponseActivities;
//        this.scenarioEvaluationList = scenarioEvaluationList;
    }

    public Scenario toEntity() {
        return Scenario.builder()
                .id(id)
                .name(name)
                .incidentLevel(incidentLevel)
                .incidentImpact(incidentImpact)
                .incidentType(incidentType)
                .incidentDetailType(incidentDetailType)
                .portArea(portArea)
                .accidentPortFacilityList(accidentPortFacilityList)
                .accidentResponseActivityList(accidentResponseActivityList)
                .scenarioEvaluationList(scenarioEvaluationList)
                .build();
    }
}
