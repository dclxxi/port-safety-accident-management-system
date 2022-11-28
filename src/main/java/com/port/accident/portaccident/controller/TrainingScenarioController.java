package com.port.accident.portaccident.controller;

import com.port.accident.portaccident.domain.training_scenario.Scenario;
import com.port.accident.portaccident.domain.training_scenario.elements.AccidentResponseActivity;
import com.port.accident.portaccident.dto.training_scenario.ScenarioFacilityActivityDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioSearchCondition;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentPortFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.elements.AccidentResponseActivityDto;
import com.port.accident.portaccident.enums.*;
import com.port.accident.portaccident.repository.training_scenario.ScenarioRepository;
import com.port.accident.portaccident.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Controller
@RequiredArgsConstructor
@RequestMapping("/TrainingScenarios")
public class TrainingScenarioController {

    private final ScenarioService scenarioService;
    private final ScenarioRepository scenarioRepository;

    @GetMapping("/TS_Register_Page")
    public String registerTrainingScenarioPage() {
        return "TrainingScenarios/TS_Register";
    }

    @PostMapping("/TS_Register")
    public String registerTrainingScenario(@RequestBody ScenarioFacilityActivityDto scenarioFacilityDto) {

        ScenarioDto registerScenarioDto = scenarioService.toServiceScenarioDto(scenarioFacilityDto);
        List<AccidentPortFacilityDto> registerFacilityDtoList = scenarioService.makeAccidentPortFacilityDtoBuilder(scenarioFacilityDto.getAccidentPortFacilityList());

        scenarioService.registerScenario(registerScenarioDto, registerFacilityDtoList);
        return "redirect:/TrainingScenarios/TS_Check";
    }

    @GetMapping("/TS_Modify_Page/{scenarioId}")
    public String modifyTrainingScenarioPage(Model model, @PathVariable(value = "scenarioId") Integer scenarioId) {

        Scenario scenario = scenarioService.findById(scenarioId);

        List<PortFacility> portFacilityNameList = scenarioService.findAccidentPortFacilityNameByScenarioId(scenarioId);
        List<AccidentResponseActivity> accidentResponseActivityList = scenarioService.findAccidentResponseActivityByScenarioId(scenarioId);

        model.addAttribute("scenario", scenario);
        model.addAttribute("portFacilityList", portFacilityNameList.toString());
        model.addAttribute("accidentResponseActivityList", accidentResponseActivityList);

        return "TrainingScenarios/TS_Modify";
    }

    @RequestMapping("/TS_Modify")
    public String modifyTrainingScenario(@RequestBody ScenarioFacilityActivityDto scenarioFacilityActivityDto) {

        ScenarioDto modifyScenarioDto = scenarioService.toServiceScenarioDto(scenarioFacilityActivityDto);
        List<AccidentPortFacilityDto> modifyFacilityDtoList = scenarioService.makeAccidentPortFacilityDtoBuilder(scenarioFacilityActivityDto.getAccidentPortFacilityList());
        List<AccidentResponseActivityDto> accidentResponseActivityDtoList = scenarioService.makeAccidentResponseActivityDtoBuilder(scenarioFacilityActivityDto.getAccidentResponseActivityList());

        scenarioService.modifyScenario(modifyScenarioDto, modifyFacilityDtoList, accidentResponseActivityDtoList);

        return "redirect:/TrainingScenarios/TS_Check";
    }

    @PostMapping("/TS_Modify_Check")
    public String modifyCheck(@RequestParam(value = "nameCheck") String nameCheck) throws Exception {
        Scenario scenario = scenarioRepository.findByName(nameCheck).orElseThrow(() -> new Exception("해당 시나리오명은 없습니다."));
        return "TrainingScenarios/TS_Modify";
    }


//    @GetMapping("/TS_Check_Page")
//    public String checkTrainingScenario(){
//        return "TrainingScenarios/TS_Check";
//    }

    @RequestMapping("/TS_Check")
    public String checkTrainingScenario(Model model,
                                        @RequestParam(required = false, defaultValue = "") String name,
                                        @RequestParam(required = false, defaultValue = "") String incidentLevel,
                                        @RequestParam(required = false, defaultValue = "") String incidentType,
                                        @RequestParam(required = false, defaultValue = "") String incidentDetailType,
                                        @PageableDefault Pageable pageable) {

        ScenarioSearchCondition condition = new ScenarioSearchCondition();
        if (hasText(name))
            condition.setName(name);
        if (hasText(incidentLevel))
            condition.setIncidentLevel(IncidentLevel.valueOf(incidentLevel));
        if (hasText(incidentType))
            condition.setIncidentType(IncidentType.valueOf(incidentType));
        if (hasText(incidentDetailType))
            condition.setIncidentDetailType(IncidentDetailType.valueOf(incidentDetailType));

        Page<ScenarioFacilityDto> scenarios = scenarioService.searchPageScenario(condition, pageable);

        model.addAttribute("condition", condition);
        model.addAttribute("scenarios", scenarios);
        return "TrainingScenarios/TS_Check";
    }


    @RequestMapping("/TS_Detail/{scenarioId}")
    public String detailTrainingScenario(Model model, @PathVariable(value = "scenarioId") Integer scenarioId) {
        Scenario scenario = scenarioService.findById(scenarioId);

        List<PortFacility> portFacilityNameList = scenarioService.findAccidentPortFacilityNameByScenarioId(scenarioId);
        List<AccidentResponseActivity> accidentResponseActivityList = scenarioService.findAccidentResponseActivityByScenarioId(scenarioId);

        model.addAttribute("scenario", scenario);
        model.addAttribute("portFacilityList", portFacilityNameList.toString());
        model.addAttribute("accidentResponseActivityList", accidentResponseActivityList);

        return "TrainingScenarios/TS_Detail";
    }


    @GetMapping("/ARA_Register_Page/{scenarioId}")
    public String registerAccidentResponseActivityPage(Model model, @PathVariable(value = "scenarioId") Integer scenarioId) {
        String scenarioName = scenarioService.findNameById(scenarioId);

        model.addAttribute("scenarioId", scenarioId);
        model.addAttribute("scenarioName", scenarioName);
        return "TrainingScenarios/AccidentResponseActivity/ARA_Register";
    }

    @PostMapping("/ARA_Register")
    public String registerAccidentResponseActivity(RedirectAttributes redirectAttributes,
                                                   @RequestBody AccidentResponseActivityDto accidentResponseActivityDto) {

        AccidentResponseActivityDto registerAccidentResponseActivityDto = scenarioService.toServiceAccidentResponseActivity(accidentResponseActivityDto);
        scenarioService.registerAccidentResponseActivity(accidentResponseActivityDto.getScenarioId(), registerAccidentResponseActivityDto);

        redirectAttributes.addAttribute("scenarioId", accidentResponseActivityDto.getScenarioId());

        return "redirect:/TrainingScenarios/TS_Detail/{scenarioId}";
    }

    @GetMapping("/ARA_Modify_Page")
    public String modifyAccidentResponseActivityPage(Model model,
                                                     @RequestParam("accidentResponseActivityId") Integer accidentResponseActivityId) {
        AccidentResponseActivity accidentResponseActivity = scenarioService.findByAccidentResponseActivityId(accidentResponseActivityId);

        model.addAttribute("accidentResponseActivity", accidentResponseActivity);

        return "TrainingScenarios/ARA_Modify";
    }

    @RequestMapping("/ARA_Modify")
    public String modifyAccidentResponseActivity(RedirectAttributes redirectAttributes,
                                                 @RequestBody AccidentResponseActivityDto accidentResponseActivityDto) {

        /*TODO:: 혜원 현정님 - 안전사고대응활동 수정*/

        AccidentResponseActivityDto modifyAccidentResponseActivityDto = scenarioService.toServiceAccidentResponseActivity(accidentResponseActivityDto);
        scenarioService.updateAccidentResponseActivity(modifyAccidentResponseActivityDto);

        redirectAttributes.addAttribute("scenarioId", modifyAccidentResponseActivityDto.getScenario().getId());

        return "redirect:/TrainingScenarios//TS_Detail/{scenarioId}";
    }
}