package com.port.accident.portaccident.domain.code;

import com.port.accident.portaccident.dto.code.DetailedCodeDto;
import com.port.accident.portaccident.dto.code.RepresentativeCodeDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "detailed_code")
@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class DetailedCode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detailed_code_id")
    private Integer id;

    @Column(name = "detailed_code")
    private String code;

    @Column(name = "detailed_code_name")
    private String name;

    @Column(name = "detailed_code_comment")
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "representative_code_id")
    private RepresentativeCode representativeCode;

    @Builder
    public DetailedCode(Integer id, String code, String name, String comment, RepresentativeCode representativeCode) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.comment = comment;
        this.representativeCode = representativeCode;
    }

    public DetailedCode(String code, String name, String comment) {
        this.code = code;
        this.name = name;
        this.comment = comment;
    }

    public void setRepresentativeCode(RepresentativeCode representativeCode) {
        this.representativeCode = representativeCode;
    }

    public void updateDetCode(DetailedCodeDto dto) {
        this.name = dto.getName();
        this.comment = dto.getComment();
    }


}
