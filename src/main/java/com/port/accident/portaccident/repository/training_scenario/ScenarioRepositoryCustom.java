package com.port.accident.portaccident.repository.training_scenario;

import com.port.accident.portaccident.dto.training_scenario.ScenarioFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ScenarioRepositoryCustom {
    Page<ScenarioFacilityDto> searchPageScenario(ScenarioSearchCondition condition, Pageable pageable);
}
