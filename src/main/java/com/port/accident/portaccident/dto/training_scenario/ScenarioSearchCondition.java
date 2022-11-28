package com.port.accident.portaccident.dto.training_scenario;

import com.port.accident.portaccident.enums.IncidentDetailType;
import com.port.accident.portaccident.enums.IncidentLevel;
import com.port.accident.portaccident.enums.IncidentType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import static org.springframework.util.StringUtils.hasText;

@Data
@RequiredArgsConstructor
public class ScenarioSearchCondition {
    private String name;
    private IncidentLevel incidentLevel;
    private IncidentType incidentType;
    private IncidentDetailType incidentDetailType;

    public ScenarioSearchCondition(String name, String incidentLevel, String incidentType, String incidentDetailType) {
        if (hasText(name))
            this.name = name;

        if (hasText(incidentLevel))
            this.incidentLevel = IncidentLevel.valueOf(incidentLevel);

        if (hasText(incidentType))
            this.incidentType = IncidentType.valueOf(incidentType);

        if (hasText(incidentDetailType))
            this.incidentDetailType = IncidentDetailType.valueOf(incidentDetailType);
    }
}
