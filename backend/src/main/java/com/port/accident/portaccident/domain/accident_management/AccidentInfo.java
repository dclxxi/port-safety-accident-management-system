package com.port.accident.portaccident.domain.accident_management;

import com.port.accident.portaccident.domain.accident_management.elements.CausesSafetyAccident;
import com.port.accident.portaccident.domain.accident_management.elements.DamageFacility;
import com.port.accident.portaccident.domain.accident_management.type.AccidentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Table(name = "accident_info")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AccidentInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "accident_info_id")
    private Integer id;

    @Column(name = "accident_date")
    private LocalDateTime accidentDate;

    @Column(name = "accident_area")
    private String accidentArea;

    @Column(name = "accident_level")
    private String accidentLevel;

    @Column(name = "accident_impact")
    private String accidentImpact;

    @Column(name = "accident_path")
    private String accidentPath;

    @Column(name = "accident_manager")
    private String accidentManager;

    @Column(name = "victim")
    private String victim;

    @OneToMany(mappedBy = "accident_info")
    private List<AccidentType> accidentTypeList= new ArrayList<>();

    @OneToMany(mappedBy = "accident_info")
    private List<CausesSafetyAccident> causesSafetyAccidentList = new ArrayList<>();

    @OneToMany(mappedBy = "accident_info")
    private List<DamageFacility> damageFacilityList = new ArrayList<>();

}
