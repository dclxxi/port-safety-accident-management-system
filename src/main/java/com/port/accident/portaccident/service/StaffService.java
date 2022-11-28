package com.port.accident.portaccident.service;

import com.port.accident.portaccident.domain.staff.Staff;
import com.port.accident.portaccident.dto.staff.StaffDto;
import com.port.accident.portaccident.dto.staff.StaffSearchCondition;
import com.port.accident.portaccident.repository.staff.StaffRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class StaffService {

    private final StaffRepository staffRepository;

    public StaffDto toServiceDto(StaffDto staffDto) {
        return StaffDto.builder()
                .id(staffDto.getId())
                .name(staffDto.getName())
                .corporation(staffDto.getCorporation())
                .group(staffDto.getGroup())
                .position(staffDto.getPosition())
                .email(staffDto.getEmail())
                .phoneNumber(staffDto.getPhoneNumber())
                .build();
    }

    public Staff findByStaffId(Integer staffId) {
        return staffRepository.findById(staffId).orElseThrow(() -> new NoSuchElementException("존재하지 않는 아이디값입니다."));
    }

    @Transactional
    public Integer registerStaff(StaffDto staffDto) {
        validateDuplicateStaff(staffDto); // 중복 비상연락망 검증

        return staffRepository.save(staffDto.toEntity()).getId();
    }

    private void validateDuplicateStaff(StaffDto staffDto) {
        Optional<Staff> findStaff = staffRepository.findByPhoneNumber(staffDto.getPhoneNumber());

        if (findStaff.isPresent()) {
            throw new IllegalStateException("이미 존재하는 비상연락망입니다.");
        }

    }

    @Transactional
    public Integer updateStaff(StaffDto staffDto) {
        Staff staff = staffRepository.findById(staffDto.getId()).get();
        staff.update(staffDto);

        return staffRepository.save(staff).getId();
    }

    @Transactional
    public void deleteStaff(Integer staffId) {
        staffRepository.deleteById(staffId);
    }

    @Transactional
    public Page<Staff> searchPageStaff(StaffSearchCondition condition, Pageable pageable) {
        return staffRepository.searchPageStaff(condition, pageable);
    }
}
