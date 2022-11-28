package com.port.accident.portaccident.controller;

import com.port.accident.portaccident.domain.training_scenario.Scenario;
import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.dto.training_scenario.scenario_evaluation.ScenarioEvaluationDto;
import com.port.accident.portaccident.dto.training_scenario_result.EvaluationSearchCondition;
import com.port.accident.portaccident.repository.training_scenario.ScenarioRepository;
import com.port.accident.portaccident.service.ScenarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/TS_Assessment")
public class ScenarioEvaluationController {

    private final ScenarioService scenarioService;
    private final ScenarioRepository scenarioRepository;

    @GetMapping("/TSA_Register_Page")
    public String registerScenarioEvaluationPage(Model model) {

        List<Scenario> allScenarios = scenarioRepository.findAll();
        model.addAttribute("allScenarios", allScenarios);

        return "TS_Assessment/TSA_Register";
    }

    @RequestMapping("/TSA_Register")
    public String registerScenarioEvaluation(@RequestBody ScenarioEvaluationDto scenarioEvaluationDto) {

        ScenarioEvaluationDto registerScenarioEvaluationDto = scenarioService.toServiceScenarioEvaluation(scenarioEvaluationDto);
        scenarioService.registerScenarioEvaluation(registerScenarioEvaluationDto);

        return "redirect:/TS_Assessment/TSA_Check";
    }

    @GetMapping("/TSA_Modify_Page/{scenarioEvaluationId}")
    public String modifyScenarioEvaluationPage(Model model, @PathVariable(value = "scenarioEvaluationId") int scenarioEvaluationId) {

        ScenarioEvaluation scenarioEvaluation = scenarioService.findScenarioEvaluationById(scenarioEvaluationId);
        model.addAttribute("scenarioEvaluation", scenarioEvaluation);

        return "TS_Assessment/TSA_Modify";
    }

    @RequestMapping("/TSA_Modify")
    public String modifyScenarioEvaluation(@RequestBody ScenarioEvaluationDto scenarioEvaluationDto) {

        ScenarioEvaluationDto modifyScenarioEvaluationDto = scenarioService.toServiceScenarioEvaluation(scenarioEvaluationDto);
        scenarioService.modifyScenarioEvaluation(modifyScenarioEvaluationDto);

        return "redirect:/TS_Assessment/TSA_Check";
    }

    @RequestMapping("/TSA_Check")
    public String selectScenarioEvaluation(Model model,
                                           @RequestParam(required = false, defaultValue = "") String name,
                                           @PageableDefault Pageable pageable) {

        EvaluationSearchCondition condition = new EvaluationSearchCondition(name);
        Page<ScenarioEvaluation> evaluationList = scenarioService.searchPageScenarioEvaluation(condition, pageable);

        model.addAttribute("condition", condition);
        model.addAttribute("evaluations", evaluationList);

        return "TS_Assessment/TSA_Check";
    }

}
