package com.port.accident.portaccident.controller;

import com.port.accident.portaccident.domain.staff.Staff;
import com.port.accident.portaccident.dto.staff.StaffDto;
import com.port.accident.portaccident.dto.staff.StaffSearchCondition;
import com.port.accident.portaccident.service.StaffService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/EmergencyContact")
public class StaffController {
    private final StaffService staffService;

    @GetMapping("/EC_Register_Page")
    public String registerStaffPage() {
        return "EmergencyContact/EC_Register";
    }

    @PostMapping("/EC_Register")
    public String registerStaff(@RequestBody StaffDto staffDto) {
        StaffDto registerStaffDto = staffService.toServiceDto(staffDto);
        staffService.registerStaff(registerStaffDto);

        return "redirect:/EmergencyContact/EC_Check";
    }

    @GetMapping("/EC_Modify_Page/{staffId}")
    public String modifyStaffPage(Model model, @PathVariable(value = "staffId") Integer staffId) {
        Staff staff = staffService.findByStaffId(staffId);
        model.addAttribute("staff", staff);

        return "EmergencyContact/EC_Modify";
    }

    @PostMapping("/EC_Modify")
    public String modifyStaff(@RequestBody StaffDto staffDto) {
        StaffDto modifyStaffDto = staffService.toServiceDto(staffDto);
        staffService.updateStaff(modifyStaffDto);

        return "redirect:/EmergencyContact/EC_Check";
    }

    @PostMapping("/EC_Delete/{staffId}")
    public String deleteStaff(@PathVariable(value = "staffId") Integer staffId) {
        staffService.deleteStaff(staffId);

        return "redirect:/EmergencyContact/EC_Check";
    }

    @GetMapping("/EC_Check")
    public String checkStaff(Model model,
                             @RequestParam(required = false, defaultValue = "") String name,
                             @RequestParam(required = false, defaultValue = "") String corporation,
                             @PageableDefault Pageable pageable) {

        StaffSearchCondition condition = new StaffSearchCondition(name, corporation);
        Page<Staff> staffs = staffService.searchPageStaff(condition, pageable);

        model.addAttribute("condition", condition);
        model.addAttribute("staffs", staffs);

        return "EmergencyContact/EC_Check";
    }

}
