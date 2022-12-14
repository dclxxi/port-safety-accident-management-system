package com.port.accident.portaccident.service.training_scenario;

import com.port.accident.portaccident.domain.training_scenario.Scenario;
import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.enums.*;
import com.port.accident.portaccident.dto.training_scenario.ScenarioDto;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentPortFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.scenario_evaluation.ScenarioEvaluationDto;
import com.port.accident.portaccident.dto.training_scenario_result.EvaluationSearchCondition;
import com.port.accident.portaccident.repository.training_scenario.ScenarioEvaluationRepository;
import com.port.accident.portaccident.repository.training_scenario.ScenarioRepository;
import com.port.accident.portaccident.service.ScenarioService;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Transactional
public class ScenarioEvaluationServiceTest {

    @Autowired
    ScenarioService scenarioService;

    @Autowired
    ScenarioRepository scenarioRepository;

    @Autowired
    ScenarioEvaluationRepository scenarioEvaluationRepository;


    @Before
    public void 시나리오_등록() {
        ScenarioDto scenarioDto = ScenarioDto.builder()
                .name("SY2")
                .incidentLevel(IncidentLevel.LEVEL_3)
                .incidentImpact(IncidentImpact.DAMAGE)
                .incidentType(IncidentType.INCIDENT)
                .incidentDetailType(IncidentDetailType.DROP)
                .portArea(TrainingPlace.PLACE1)
                .build();

        AccidentPortFacilityDto accidentPortFacilityDto = AccidentPortFacilityDto.builder()
                .name(PortFacility.CRANE)
                .build();

        AccidentPortFacilityDto accidentPortFacilityDto2 = AccidentPortFacilityDto.builder()
                .name(PortFacility.CONTAINER)
                .build();

        List<AccidentPortFacilityDto> accidentPortFacilityDtoList = new ArrayList<>();
        accidentPortFacilityDtoList.add(accidentPortFacilityDto);
        accidentPortFacilityDtoList.add(accidentPortFacilityDto2);

        Integer scenarioId = scenarioService.registerScenario(scenarioDto, accidentPortFacilityDtoList);
    }

    @Test
    public void 시나리오_평가_등록() {
        //given
        ScenarioEvaluationDto scenarioEvaluationDto = ScenarioEvaluationDto.builder()
                .name("SY2")
                .developmentStandard1(SuitableCheck.A)
                .developmentStandard2(SuitableCheck.C)
                .developmentStandard3(SuitableCheck.A)
                .possibleStandard1(SuitableCheck.A)
                .possibleStandard2(SuitableCheck.B)
                .possibleStandard3(SuitableCheck.C)
                .completeStandard1(SuitableCheck.B)
                .completeStandard2(SuitableCheck.A)
                .completeStandard3(SuitableCheck.C)
                .build();

        //when
        Integer scenarioEvaluationId = scenarioService.registerScenarioEvaluation(scenarioEvaluationDto);

        //then
        Scenario scenario = scenarioRepository.findByName("SY2").get();

        ScenarioEvaluation scenarioEvaluation = scenarioEvaluationRepository.findById(scenarioEvaluationId).get();
        List<ScenarioEvaluation> scenarioEvaluationList = scenarioEvaluationRepository.findAll();

        assertEquals(1, scenarioEvaluationList.size());
        assertEquals(scenarioEvaluationDto.getName(), scenarioEvaluation.getName());
        assertEquals(scenario.getId(), scenarioEvaluation.getScenario().getId());
    }

    @Ignore
    @Test(expected = IllegalStateException.class)
    public void 시나리오_평가_중복_예외() {
        //given
        ScenarioEvaluationDto scenarioEvaluationDto = ScenarioEvaluationDto.builder()
                .name("SY2")
                .build();

        ScenarioEvaluationDto scenarioEvaluationDto2 = ScenarioEvaluationDto.builder()
                .name("SY2")
                .build();

        //when
        scenarioService.registerScenarioEvaluation(scenarioEvaluationDto);
        scenarioService.registerScenarioEvaluation(scenarioEvaluationDto2);

        //then
        fail("이미 존재하는 시나리오 평가입니다.");
    }

    @Test
    public void 시나리오_평가_수정() {
        //given
        ScenarioEvaluationDto scenarioEvaluationDto = ScenarioEvaluationDto.builder()
                .name("SY2")
                .developmentStandard1(SuitableCheck.A)
                .developmentStandard2(SuitableCheck.C)
                .developmentStandard3(SuitableCheck.A)
                .possibleStandard1(SuitableCheck.A)
                .possibleStandard2(SuitableCheck.B)
                .possibleStandard3(SuitableCheck.C)
                .completeStandard1(SuitableCheck.B)
                .completeStandard2(SuitableCheck.A)
                .completeStandard3(SuitableCheck.C)
                .build();

        Integer scenarioEvaluationId = scenarioService.registerScenarioEvaluation(scenarioEvaluationDto);

        ScenarioEvaluationDto updateScenarioEvaluationDto = ScenarioEvaluationDto.builder()
                .id(scenarioEvaluationId)
                .name("SY2")
                .developmentStandard1(SuitableCheck.A)
                .developmentStandard2(SuitableCheck.C)
                .developmentStandard3(SuitableCheck.A)
                .possibleStandard1(SuitableCheck.A)
                .possibleStandard2(SuitableCheck.B)
                .possibleStandard3(SuitableCheck.C)
                .completeStandard1(SuitableCheck.B)
                .completeStandard2(SuitableCheck.A)
                .completeStandard3(SuitableCheck.C)
                .build();

        //when
        Integer updateScenarioEvaluationId = scenarioService.modifyScenarioEvaluation(updateScenarioEvaluationDto);

        //then
        ScenarioEvaluation updateScenarioEvaluation = scenarioEvaluationRepository.findById(updateScenarioEvaluationId).get();

        assertEquals(scenarioEvaluationId, updateScenarioEvaluationId);
        assertEquals(updateScenarioEvaluationDto.getName(), updateScenarioEvaluation.getName());
        assertEquals(updateScenarioEvaluationDto.getCompleteStandard2(), updateScenarioEvaluation.getCompleteStandard2());
        assertEquals(updateScenarioEvaluationDto.getCompleteStandard1(), updateScenarioEvaluation.getCompleteStandard1());
    }

    @Test
    public void 시나리오_평가_삭제() {
        //given
        ScenarioEvaluationDto scenarioEvaluationDto = ScenarioEvaluationDto.builder()
                .name("SY2")
                .developmentStandard1(SuitableCheck.A)
                .developmentStandard2(SuitableCheck.C)
                .developmentStandard3(SuitableCheck.A)
                .possibleStandard1(SuitableCheck.A)
                .possibleStandard2(SuitableCheck.B)
                .possibleStandard3(SuitableCheck.C)
                .completeStandard1(SuitableCheck.B)
                .completeStandard2(SuitableCheck.A)
                .completeStandard3(SuitableCheck.C)
                .build();

        Integer scenarioEvaluationId = scenarioService.registerScenarioEvaluation(scenarioEvaluationDto);

        //when
        scenarioService.deleteScenarioEvaluation(scenarioEvaluationId);

        //then
        Optional<ScenarioEvaluation> deleteScenarioEvaluation = scenarioEvaluationRepository.findById(scenarioEvaluationId);
        assertFalse(deleteScenarioEvaluation.isPresent());
    }

    @Test
    public void 시나리오_평가_조회_페이징() {
        //given
        IntStream.rangeClosed(1, 5).forEach(i -> {
            ScenarioEvaluationDto scenarioEvaluationDto = ScenarioEvaluationDto.builder()
                    .name("SY2")
                    .build();

            scenarioService.registerScenarioEvaluation(scenarioEvaluationDto);
        });

        EvaluationSearchCondition condition = new EvaluationSearchCondition();
        PageRequest pageRequest = PageRequest.of(0, 3);

        //when
        Page<ScenarioEvaluation> scenarioEvaluation = scenarioEvaluationRepository.searchPageScenarioEvaluation(condition, pageRequest);

        //then
        List<ScenarioEvaluation> content = scenarioEvaluation.getContent();
        assertEquals("조회된 데이터 수", 3, content.size());
        assertEquals("전체 데이터 수", 5, scenarioEvaluation.getTotalElements());
        assertEquals("페이지 번호", 0, scenarioEvaluation.getNumber());
        assertEquals("전체 페이지 번호", 2, scenarioEvaluation.getTotalPages());
        assertTrue("첫번째 항목인가?", scenarioEvaluation.isFirst());
        assertTrue("다음 페이지가 있는가?", scenarioEvaluation.hasNext());
        assertEquals("SY2v1", content.get(0).getName());
    }

    @Test
    public void 시나리오_평가_검색_페이징() {
        //given
        ScenarioDto scenarioDto = ScenarioDto.builder()
                .name("SN2")
                .build();

        IntStream.rangeClosed(1, 5).forEach(i -> {
            ScenarioEvaluationDto scenarioEvaluationDto = ScenarioEvaluationDto.builder()
                    .name("SY2")
                    .build();

            scenarioService.registerScenarioEvaluation(scenarioEvaluationDto);

            ScenarioEvaluationDto scenarioEvaluationDto2 = ScenarioEvaluationDto.builder()
                    .name("SN2")
                    .build();

            scenarioService.registerScenarioEvaluation(scenarioEvaluationDto2);
        });

        EvaluationSearchCondition condition = new EvaluationSearchCondition();
        condition.setName("SY");
        PageRequest pageRequest = PageRequest.of(0, 3);

        //when
        Page<ScenarioEvaluation> scenarioEvaluation = scenarioEvaluationRepository.searchPageScenarioEvaluation(condition, pageRequest);

        //then
        List<ScenarioEvaluation> content = scenarioEvaluation.getContent();
        assertEquals("조회된 데이터 수", 3, content.size());
        assertEquals("전체 데이터 수", 5, scenarioEvaluation.getTotalElements());
        assertEquals("페이지 번호", 0, scenarioEvaluation.getNumber());
        assertEquals("전체 페이지 번호", 2, scenarioEvaluation.getTotalPages());
        assertTrue("첫번째 항목인가?", scenarioEvaluation.isFirst());
        assertTrue("다음 페이지가 있는가?", scenarioEvaluation.hasNext());
        assertEquals("SY2v1", content.get(0).getName());
    }
}
