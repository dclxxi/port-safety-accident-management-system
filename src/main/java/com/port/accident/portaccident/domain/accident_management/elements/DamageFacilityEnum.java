package com.port.accident.portaccident.domain.accident_management.elements;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DamageFacilityEnum {

    Container("컨테이너"),
    Crane("크레인"),
    ForkLift("지게차"),
    Ladder("사다리");

    private final String value;
}
