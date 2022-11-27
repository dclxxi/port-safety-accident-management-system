package com.port.accident.portaccident.repository.training_scenario;

import com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.ScenarioEvaluation;
import com.port.accident.portaccident.dto.training_scenario_result.EvaluationSearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.port.accident.portaccident.domain.training_scenario.scenario_evaluation.QScenarioEvaluation.scenarioEvaluation;
import static org.springframework.util.ObjectUtils.isEmpty;


@Repository
@Transactional
@RequiredArgsConstructor
public class ScenarioEvaluationRepositoryCustomImpl implements ScenarioEvaluationRepositoryCustom {

    private static final String VERSION = "v";

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ScenarioEvaluation> searchPageScenarioEvaluation(EvaluationSearchCondition condition, Pageable pageable) {
        List<ScenarioEvaluation> content = queryFactory
                .selectFrom(scenarioEvaluation)
                .where(nameContains(condition.getName()))
                .orderBy(scenarioEvaluation.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(scenarioEvaluation.count())
                .from(scenarioEvaluation)
                .where(nameContains(condition.getName()));

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression nameContains(String nameCondition) {
        return isEmpty(nameCondition) ? null : scenarioEvaluation.name.contains(nameCondition);
    }

    @Override
    public String setScenarioEvaluationName(Integer scenarioId, String scenarioName) {
        String beforeScenarioName = findTopByNameByScenarioId(scenarioId);

        System.out.println(beforeScenarioName);

        if (beforeScenarioName == null) {
            return scenarioName + "v1";
        }

        String version = beforeScenarioName.substring(beforeScenarioName.lastIndexOf(VERSION) + 1);
        return scenarioName + VERSION + (Integer.parseInt(version) + 1);
    }

    private String findTopByNameByScenarioId(Integer scenarioId) {
        return queryFactory
                .select(scenarioEvaluation.name)
                .from(scenarioEvaluation)
                .where(scenarioIdEquals(scenarioId))
                .orderBy(scenarioEvaluation.id.desc())
                .fetchFirst();
    }

    private BooleanExpression scenarioIdEquals(Integer scenarioId) {
        return scenarioEvaluation.scenario.id.eq(scenarioId);
    }
}
