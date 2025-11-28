package com.example.demo.web;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.demo.domain.Shift;
import com.example.demo.domain.Staff;
import com.example.demo.repo.ShiftRepository;
import com.example.demo.repo.StaffRepository;
import com.example.demo.web.form.ShiftForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
@RequiredArgsConstructor
public class ShiftController {

    private final ShiftRepository shiftRepository;
    private final StaffRepository staffRepository;

    @GetMapping("/shifts")
    public String listShift(Model model) {
        model.addAttribute("shifts", shiftRepository.findAll());
        return "shifts/list";
    }

    @GetMapping("/shifts/new")
    public String newShiftForm(Model model) {
        model.addAttribute("shiftForm", new ShiftForm());
        model.addAttribute("staff", staffRepository.findAll());
        return "shifts/new";
    }

    @PostMapping("/shifts")
    public String createShift(@ModelAttribute
        @Valid ShiftForm shiftForm,
        BindingResult bindingResult,
        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("staff", staffRepository.findAll());
            return "shifts/new";
        }

        Staff staff = staffRepository.findById(shiftForm.getStaffId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid staff ID"));

        LocalDate workDate = LocalDate.parse(shiftForm.getWorkDate());
        LocalTime startTime = LocalTime.parse(shiftForm.getStartTime());
        LocalTime endTime = LocalTime.parse(shiftForm.getEndTime());

        Shift shift = Shift.builder()
            .workDate(workDate)
            .startTime(startTime)
            .endTime(endTime)
            .staff(staff)
            .note(shiftForm.getNote())
            .build();

        shiftRepository.save(shift);

        return "redirect:/shifts";
    }
    

}
