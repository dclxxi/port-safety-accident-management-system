package com.port.accident.portaccident.service;

import com.port.accident.portaccident.domain.training_scenario.Scenario;
import com.port.accident.portaccident.domain.training_scenario.elements.AccidentPortFacility;
import com.port.accident.portaccident.domain.training_scenario.elements.AccidentResponseActivity;
import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.dto.training_scenario.ScenarioFacilityActivityDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioSearchCondition;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentPortFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentResponseActivityDto;
import com.port.accident.portaccident.dto.training_scenario.scenario_evaluation.ScenarioEvaluationDto;
import com.port.accident.portaccident.dto.training_scenario_result.EvaluationSearchCondition;
import com.port.accident.portaccident.enums.PortFacility;
import com.port.accident.portaccident.repository.training_scenario.AccidentPortFacilityRepository;
import com.port.accident.portaccident.repository.training_scenario.AccidentResponseActivityRepository;
import com.port.accident.portaccident.repository.training_scenario.ScenarioEvaluationRepository;
import com.port.accident.portaccident.repository.training_scenario.ScenarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = false)
@RequiredArgsConstructor
public class ScenarioService {
    private final ScenarioRepository scenarioRepository;
    private final AccidentPortFacilityRepository accidentPortFacilityRepository;
    private final AccidentResponseActivityRepository accidentResponseActivityRepository;
    private final ScenarioEvaluationRepository scenarioEvaluationRepository;

    public ScenarioDto toServiceScenarioDto(ScenarioDto scenarioDto) {
        return ScenarioDto.builder()
                .id(scenarioDto.getId())
                .name(scenarioDto.getName())
                .incidentLevel(scenarioDto.getIncidentLevel())
                .incidentImpact(scenarioDto.getIncidentImpact())
                .incidentType(scenarioDto.getIncidentType())
                .incidentDetailType(scenarioDto.getIncidentDetailType())
                .portArea(scenarioDto.getPortArea())
                .build();
    }

    public ScenarioDto toServiceScenarioDto(ScenarioFacilityActivityDto scenarioFacilityActivityDto) {
        return ScenarioDto.builder()
                .id(scenarioFacilityActivityDto.getId())
                .name(scenarioFacilityActivityDto.getName())
                .incidentLevel(scenarioFacilityActivityDto.getIncidentLevel())
                .incidentImpact(scenarioFacilityActivityDto.getIncidentImpact())
                .incidentType(scenarioFacilityActivityDto.getIncidentType())
                .incidentDetailType(scenarioFacilityActivityDto.getIncidentDetailType())
                .portArea(scenarioFacilityActivityDto.getPortArea())
                .build();
    }

/*
    public List<AccidentPortFacilityDto> toServiceAccidentPortFacilityDtoList(List<AccidentPortFacilityDto> accidentPortFacilityDtoList) {
        List<AccidentPortFacilityDto> toServiceAccidentPortFacilityDtoList = new ArrayList<>();

        for (AccidentPortFacilityDto accidentPortFacilityDto : accidentPortFacilityDtoList) {
            AccidentPortFacilityDto facilityDto = AccidentPortFacilityDto.builder()
                    .id(accidentPortFacilityDto.getId())
                    .name(accidentPortFacilityDto.getName())
                    .build();

            toServiceAccidentPortFacilityDtoList.add(facilityDto);
        }
        return toServiceAccidentPortFacilityDtoList;
    }
*/

    public List<AccidentPortFacilityDto> toServiceAccidentPortFacilityDtoList(List<AccidentPortFacilityDto> facilityDtoList) {
        List<AccidentPortFacilityDto> toServiceAccidentPortFacilityDtoList = new ArrayList<>();

        for (AccidentPortFacilityDto accidentPortFacilityDto : facilityDtoList) {
            AccidentPortFacilityDto facilityDto = AccidentPortFacilityDto.builder()
                    .name(accidentPortFacilityDto.getName())
                    .build();

            toServiceAccidentPortFacilityDtoList.add(facilityDto);
        }
        return toServiceAccidentPortFacilityDtoList;
    }

    public AccidentResponseActivityDto toServiceAccidentResponseActivity(AccidentResponseActivityDto accidentResponseActivityDto) {
        return AccidentResponseActivityDto.builder()
                .id(accidentResponseActivityDto.getId())
                .comment(accidentResponseActivityDto.getComment())
                .manager(accidentResponseActivityDto.getManager())
                .completePlaningTime(accidentResponseActivityDto.getCompletePlaningDate().atStartOfDay())
                .scenario(accidentResponseActivityDto.getScenario())
                .build();
    }

    public ScenarioEvaluationDto toServiceScenarioEvaluation(ScenarioEvaluationDto scenarioEvaluationDto) {
        return ScenarioEvaluationDto.builder()
                .id(scenarioEvaluationDto.getId())
                .name(scenarioEvaluationDto.getName())
                .developmentStandard1(scenarioEvaluationDto.getDevelopmentStandard1())
                .developmentStandard2(scenarioEvaluationDto.getDevelopmentStandard2())
                .developmentStandard3(scenarioEvaluationDto.getDevelopmentStandard3())
                .possibleStandard1(scenarioEvaluationDto.getPossibleStandard1())
                .possibleStandard2(scenarioEvaluationDto.getPossibleStandard2())
                .possibleStandard3(scenarioEvaluationDto.getPossibleStandard3())
                .completeStandard1(scenarioEvaluationDto.getCompleteStandard1())
                .completeStandard2(scenarioEvaluationDto.getCompleteStandard2())
                .completeStandard3(scenarioEvaluationDto.getCompleteStandard3())
                .scenario(scenarioEvaluationDto.getScenario())
                .build();

    }

    public List<AccidentPortFacilityDto> makeAccidentPortFacilityDtoBuilder(List<PortFacility> facilityList) {
        List<AccidentPortFacilityDto> facilityDtoList = new ArrayList<>();

        for (PortFacility facility : facilityList) {
            AccidentPortFacilityDto facilityDto = AccidentPortFacilityDto.builder()
                    .name(facility)
                    .build();

            facilityDtoList.add(facilityDto);
        }

        return facilityDtoList;
    }

    public List<AccidentResponseActivityDto> makeAccidentResponseActivityDtoBuilder(List<AccidentResponseActivityDto> accidentResponseActivityDtoList) {
        List<AccidentResponseActivityDto> activityDtoList = new ArrayList<>();

        for (AccidentResponseActivityDto accidentResponseActivityDto : accidentResponseActivityDtoList) {
            Scenario scenario = findById(accidentResponseActivityDto.getScenarioId());

            AccidentResponseActivityDto activityDto = AccidentResponseActivityDto.builder()
                    .id(accidentResponseActivityDto.getId())
                    .comment(accidentResponseActivityDto.getComment())
                    .manager(accidentResponseActivityDto.getManager())
                    .completePlaningTime(accidentResponseActivityDto.getCompletePlaningDate().atStartOfDay())
                    .scenario(scenario)
                    .build();

            activityDtoList.add(activityDto);
        }

        return activityDtoList;
    }

    public Scenario findById(Integer scenarioId) {
        return scenarioRepository.findById(scenarioId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디값입니다."));
    }

    public String findNameById(Integer scenarioId) {
        return scenarioRepository.findNameById(scenarioId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디값입니다."));
    }

    @Transactional
    public Integer registerScenario(ScenarioDto scenarioDto,
                                    List<AccidentPortFacilityDto> accidentPortFacilityDtoList) {
        Integer scenarioId = saveScenario(scenarioDto);
        Scenario scenario = scenarioRepository.findById(scenarioId).get();

        saveAccidentPortFacility(scenario, accidentPortFacilityDtoList);

        return scenarioId;
    }

    @Transactional
    public Integer saveScenario(ScenarioDto scenarioDto) {
        validateDuplicateScenario(scenarioDto.getName()); // 중복 시나리오 검증

        return scenarioRepository.save(scenarioDto.toEntity()).getId();
    }

    private void validateDuplicateScenario(String name) {
        Optional<Scenario> findScenario = scenarioRepository.findByName(name);

        if (findScenario.isPresent()) {
            throw new IllegalStateException("이미 존재하는 시나리오입니다.");
        }
    }

    @Transactional
    public void saveAccidentPortFacility(Scenario scenario, List<AccidentPortFacilityDto> accidentPortFacilityDtoList) {
        for (AccidentPortFacilityDto accidentPortFacilityDto : accidentPortFacilityDtoList) {
            scenario.addAccidentPortFacility(accidentPortFacilityDto);
        }
    }


    @Transactional
    public Integer modifyScenario(ScenarioDto scenarioDto, List<AccidentPortFacilityDto> accidentPortFacilityDtoList, List<AccidentResponseActivityDto> accidentResponseActivityDtoList) {
        String oldName = findNameById(scenarioDto.getId());
        String newName = scenarioDto.getName();

        if(!oldName.equals(newName)) {
            validateDuplicateScenario(newName);
        }

        Integer scenarioId = updateScenario(scenarioDto);
        updateAccidentPortFacility(scenarioId, accidentPortFacilityDtoList);
        modifyAccidentResponseActivity(accidentResponseActivityDtoList);

        return scenarioId;
    }

    @Transactional
    public Integer updateScenario(ScenarioDto scenarioDto) {
        Scenario scenario = scenarioRepository.findById(scenarioDto.getId()).get();
        scenario.update(scenarioDto);

        return scenarioRepository.save(scenario).getId();
    }

    @Transactional
    public Integer updateAccidentPortFacility(Integer scenarioId, List<AccidentPortFacilityDto> accidentPortFacilityDtoList) {
        Scenario scenario = scenarioRepository.findById(scenarioId).get();
        deleteAccidentPortFacility(scenario);
        saveAccidentPortFacility(scenario, accidentPortFacilityDtoList);

        return scenario.getId();
    }

    @Transactional
    public void deleteAccidentPortFacility(Scenario scenario) {
        List<AccidentPortFacility> accidentPortFacilityList = accidentPortFacilityRepository.findByScenarioId(scenario.getId());
        scenario.removeAccidentPortFacility(accidentPortFacilityList);
    }

    @Transactional
    public void deleteScenario(Integer scenarioId) {
        scenarioRepository.deleteById(scenarioId);
    }

    public Page<ScenarioFacilityDto> searchPageScenario(ScenarioSearchCondition condition, Pageable pageable) {
        return scenarioRepository.searchPageScenario(condition, pageable);
    }

    @Transactional
    public List<PortFacility> findAccidentPortFacilityNameByScenarioId(Integer scenarioId) {
        return Optional.ofNullable(accidentPortFacilityRepository.findNameByScenarioId(scenarioId))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디값입니다."));
    }


    /*
     * 사고 대응 활동
     * */

    public AccidentResponseActivity findByAccidentResponseActivityId(Integer accidentResponseActivityId) {
        return accidentResponseActivityRepository.findById(accidentResponseActivityId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디값입니다."));
    }

    @Transactional
    public Integer registerAccidentResponseActivity(Integer scenarioId, AccidentResponseActivityDto accidentResponseActivityDto) {
        Scenario scenario = scenarioRepository.findById(scenarioId).get();
        scenario.addAccidentResponseActivity(accidentResponseActivityDto);

        return saveAccidentResponseActivity(accidentResponseActivityDto);
    }

    @Transactional
    public Integer saveAccidentResponseActivity(AccidentResponseActivityDto accidentResponseActivityDto) {
        return accidentResponseActivityRepository.save(accidentResponseActivityDto.toEntity()).getId();
    }

    @Transactional
    public void modifyAccidentResponseActivity(List<AccidentResponseActivityDto> accidentResponseActivityDtoList) {
        for (AccidentResponseActivityDto activityDto : accidentResponseActivityDtoList) {
            updateAccidentResponseActivity(activityDto);
        }
    }

    @Transactional
    public Integer updateAccidentResponseActivity(AccidentResponseActivityDto accidentResponseActivityDto) {
        AccidentResponseActivity accidentResponseActivity = accidentResponseActivityRepository.findById(accidentResponseActivityDto.getId()).get();
        accidentResponseActivity.update(accidentResponseActivityDto);

        return saveAccidentResponseActivity(accidentResponseActivityDto);
    }

    @Transactional
    public void deleteAccidentResponseActivity(Integer activityId) {
        accidentResponseActivityRepository.deleteById(activityId);
    }

    @Transactional
    public List<AccidentResponseActivity> findAccidentResponseActivityByScenarioId(Integer scenarioId) {
        return Optional.ofNullable(accidentResponseActivityRepository.findByScenarioId(scenarioId))
                .orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디값입니다."));
    }

    /*
     * 시나리오 평가
     * */

    public ScenarioEvaluation findScenarioEvaluationById(Integer scenarioEvaluationId) {
        return scenarioEvaluationRepository.findById(scenarioEvaluationId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디값입니다."));
    }

    public Integer registerScenarioEvaluation(ScenarioEvaluationDto scenarioEvaluationDto) {
        Scenario scenario = scenarioRepository.findByName(scenarioEvaluationDto.getName()).get();

        scenarioEvaluationDto.setName(settingScenarioEvaluationName(scenario.getId(), scenario.getName()));

        scenario.addScenarioEvaluation(scenarioEvaluationDto);
        return saveScenarioEvaluation(scenarioEvaluationDto);
    }

    @Transactional
    public Integer saveScenarioEvaluation(ScenarioEvaluationDto scenarioEvaluationDto) {
        validateDuplicateScenarioEvaluation(scenarioEvaluationDto); // 중복 시나리오 평가 검증

        return scenarioEvaluationRepository.save(scenarioEvaluationDto.toEntity()).getId();
    }

    public String settingScenarioEvaluationName(Integer scenarioId, String scenarioName) {
        Page<String> beforeScenarioName = scenarioEvaluationRepository.findTopByNameByScenarioId(scenarioId, PageRequest.of(0, 1));

        if (beforeScenarioName.getTotalPages() != 0) {
            String[] split = beforeScenarioName.getContent().get(0).split("v");
            scenarioName = split[0] + "v" + (Integer.parseInt(split[1]) + 1);
        } else {
            scenarioName += "v1";
        }

        return scenarioName;
    }

    private void validateDuplicateScenarioEvaluation(ScenarioEvaluationDto scenarioEvaluationDto) {
        Optional<ScenarioEvaluation> findScenarioEvaluation = scenarioEvaluationRepository.findByName(scenarioEvaluationDto.getName());

        if (findScenarioEvaluation.isPresent()) {
            throw new IllegalStateException("이미 존재하는 시나리오 평가입니다.");
        }
    }

    @Transactional
    public Integer modifyScenarioEvaluation(ScenarioEvaluationDto scenarioEvaluationDto) {
        ScenarioEvaluation scenarioEvaluation = scenarioEvaluationRepository.findById(scenarioEvaluationDto.getId()).get();
        scenarioEvaluation.update(scenarioEvaluationDto);

        return scenarioEvaluationRepository.save(scenarioEvaluationDto.toEntity()).getId();
    }

    @Transactional
    public void deleteScenarioEvaluation(Integer scenarioEvaluationId) {
        scenarioEvaluationRepository.deleteById(scenarioEvaluationId);
    }

    @Transactional
    public Page<ScenarioEvaluation> searchPageScenarioEvaluation(EvaluationSearchCondition condition, Pageable pageable) {
        return scenarioEvaluationRepository.searchPageScenarioEvaluation(condition, pageable);
    }

}
