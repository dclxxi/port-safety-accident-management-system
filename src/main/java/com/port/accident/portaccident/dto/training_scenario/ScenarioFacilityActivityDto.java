package com.port.accident.portaccident.dto.training_scenario;


import com.port.accident.portaccident.dto.training_scenario.elements.AccidentResponseActivityDetailDto;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentResponseActivityDto;
import com.port.accident.portaccident.enums.*;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Data
public class ScenarioFacilityActivityDto {

    private Integer id;
    private String name;
    private IncidentLevel incidentLevel;
    private IncidentImpact incidentImpact;
    private IncidentType incidentType;
    private IncidentDetailType incidentDetailType;
    private TrainingPlace portArea;
    private List<PortFacility> accidentPortFacilityList = new ArrayList<>();
    private List<AccidentResponseActivityDto> accidentResponseActivityList = new ArrayList<>();

    @QueryProjection
    @Builder
    public ScenarioFacilityActivityDto(Integer id, String name, IncidentLevel incidentLevel, IncidentImpact impact,
                                       IncidentType incidentType, IncidentDetailType incidentDetailType, TrainingPlace portArea) {
        this.id = id;
        this.name = name;
        this.incidentLevel = incidentLevel;
        this.incidentImpact = impact;
        this.incidentType = incidentType;
        this.incidentDetailType = incidentDetailType;
        this.portArea = portArea;
    }
}
