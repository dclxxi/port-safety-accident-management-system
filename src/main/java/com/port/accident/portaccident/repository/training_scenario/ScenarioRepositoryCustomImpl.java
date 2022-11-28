package com.port.accident.portaccident.repository.training_scenario;

import com.port.accident.portaccident.dto.training_scenario.QScenarioFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioFacilityDto;
import com.port.accident.portaccident.dto.training_scenario.ScenarioSearchCondition;
import com.port.accident.portaccident.enums.IncidentDetailType;
import com.port.accident.portaccident.enums.IncidentLevel;
import com.port.accident.portaccident.enums.IncidentType;
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

import static com.port.accident.portaccident.domain.training_scenario.QScenario.scenario;
import static org.springframework.util.ObjectUtils.isEmpty;

@Repository
@Transactional
@RequiredArgsConstructor
public class ScenarioRepositoryCustomImpl implements ScenarioRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    @Override
    public Page<ScenarioFacilityDto> searchPageScenario(ScenarioSearchCondition condition, Pageable pageable) {
        List<ScenarioFacilityDto> content = queryFactory
                .select(new QScenarioFacilityDto(
                        scenario.id,
                        scenario.name,
                        scenario.incidentLevel,
                        scenario.incidentImpact,
                        scenario.incidentType,
                        scenario.incidentDetailType,
                        scenario.portArea))
                .from(scenario)
                .where(
                        nameContains(condition.getName()),
                        incidentLevelEq(condition.getIncidentLevel()),
                        incidentTypeEq(condition.getIncidentType()),
                        incidentDetailTypeEq(condition.getIncidentDetailType())
                )
                .orderBy(scenario.name.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(scenario.count())
                .from(scenario)
                .where(
                        nameContains(condition.getName()),
                        incidentLevelEq(condition.getIncidentLevel()),
                        incidentTypeEq(condition.getIncidentType()),
                        incidentDetailTypeEq(condition.getIncidentDetailType())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchOne);
    }

    private BooleanExpression nameContains(String nameCondition) {
        return isEmpty(nameCondition) ? null : scenario.name.contains(nameCondition);
    }

    private BooleanExpression incidentLevelEq(IncidentLevel incidentLevelCondition) {
        return isEmpty(incidentLevelCondition) ? null : scenario.incidentLevel.eq(incidentLevelCondition);
    }

    private BooleanExpression incidentTypeEq(IncidentType incidentTypeCondition) {
        return isEmpty(incidentTypeCondition) ? null : scenario.incidentType.eq(incidentTypeCondition);
    }

    private BooleanExpression incidentDetailTypeEq(IncidentDetailType incidentDetailTypeCondition) {
        return isEmpty(incidentDetailTypeCondition) ? null : scenario.incidentDetailType.eq(incidentDetailTypeCondition);
    }
}
