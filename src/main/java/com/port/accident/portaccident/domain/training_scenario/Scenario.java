package com.port.accident.portaccident.domain.training_scenario;


import com.port.accident.portaccident.domain.training_scenario.elements.AccidentPortFacility;
import com.port.accident.portaccident.domain.training_scenario.elements.AccidentResponseActivity;
import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.domain.training_scenario_result.TrainingResult;
import com.port.accident.portaccident.dto.training_scenario.ScenarioDto;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentPortFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentResponseActivityDto;
import com.port.accident.portaccident.dto.training_scenario.scenario_evaluation.ScenarioEvaluationDto;
import com.port.accident.portaccident.enums.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Table(name = "scenario")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Scenario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scenario_id") // 훈련 시나리오 id
    private Integer id;

    @Column(name = "scenario_name") // 시나리오명
    private String name;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_level") // 사고 수준
    private IncidentLevel incidentLevel;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_impact") // 사고 영향
    private IncidentImpact incidentImpact;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_incident_type") // 사고/재난
    private IncidentType incidentType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_incident_detail_type") // 사고 유형
    private IncidentDetailType incidentDetailType;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "scenario_port_area") // 사고 항만 구역
    private TrainingPlace portArea;



    @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL, orphanRemoval = true) // 안전 사고 항만 설비
    private List<AccidentPortFacility> accidentPortFacilityList = new ArrayList<>();

    @OneToMany(mappedBy = "scenario") // 안전 사고 대응 활동
    private List<AccidentResponseActivity> accidentResponseActivityList = new ArrayList<>();

    @OneToMany(mappedBy = "scenario") // 시나리오 평가
    private List<ScenarioEvaluation> scenarioEvaluationList = new ArrayList<>();

    @OneToMany(mappedBy = "scenario", cascade = CascadeType.ALL, orphanRemoval = true) // 훈련 결과
    private List<TrainingResult> trainingResultArrayList = new ArrayList<>();

    @Builder
    public Scenario(Integer id, String name, IncidentLevel incidentLevel, IncidentImpact incidentImpact,
                    IncidentType incidentType, IncidentDetailType incidentDetailType, TrainingPlace portArea,
                    List<AccidentPortFacility> accidentPortFacilityList,
                    List<AccidentResponseActivity> accidentResponseActivityList,
                    List<ScenarioEvaluation> scenarioEvaluationList) {
        this.id = id;
        this.name = name;
        this.incidentLevel = incidentLevel;
        this.incidentImpact = incidentImpact;
        this.incidentType = incidentType;
        this.incidentDetailType = incidentDetailType;
        this.portArea = portArea;
        this.accidentPortFacilityList = accidentPortFacilityList;
        this.accidentResponseActivityList = accidentResponseActivityList;
        this.scenarioEvaluationList = scenarioEvaluationList;
    }

    @Transactional
    public void addAccidentPortFacility(AccidentPortFacilityDto accidentPortFacilityDto) {
        accidentPortFacilityDto.setScenario(this);
        this.accidentPortFacilityList.add(accidentPortFacilityDto.toEntity());
    }

    @Transactional
    public void addAccidentResponseActivity(AccidentResponseActivityDto accidentResponseActivityDto) {
        accidentResponseActivityDto.setScenario(this);
        this.accidentResponseActivityList.add(accidentResponseActivityDto.toEntity());
    }

    @Transactional
    public void addScenarioEvaluation(ScenarioEvaluationDto scenarioEvaluationDto) {
        scenarioEvaluationDto.setScenario(this);
        this.scenarioEvaluationList.add(scenarioEvaluationDto.toEntity());
    }

    @Transactional
    public void removeAccidentPortFacility(List<AccidentPortFacility> accidentPortFacilityList) {
        for (AccidentPortFacility accidentPortFacility : accidentPortFacilityList) {
            this.accidentPortFacilityList.remove(accidentPortFacility);
        }
    }

    @Transactional
    public void removeAccidentResponseActivity(List<AccidentResponseActivity> accidentResponseActivityList) {
        for (AccidentResponseActivity accidentResponseActivity : accidentResponseActivityList) {
            this.accidentResponseActivityList.remove(accidentResponseActivity);
        }
    }

    @Transactional
    public void removeScenarioEvaluation(ScenarioEvaluation scenarioEvaluation) {
        this.scenarioEvaluationList.remove(scenarioEvaluation);
    }

    @Transactional(readOnly = true)
    public void update(ScenarioDto scenarioDto) {
        this.name = scenarioDto.getName();
        this.incidentLevel = scenarioDto.getIncidentLevel();
        this.incidentImpact = scenarioDto.getIncidentImpact();
        this.incidentType = scenarioDto.getIncidentType();
        this.incidentDetailType = scenarioDto.getIncidentDetailType();
        this.portArea = scenarioDto.getPortArea();
    }
}
