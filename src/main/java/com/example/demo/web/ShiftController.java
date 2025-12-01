package com.example.demo.web;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

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

    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm");

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

    @GetMapping("/shifts/{id}/edit")
    public String editShiftForm(@PathVariable Long id,
         Model model) {
        Shift shift = shiftRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid shift ID"));

        ShiftForm form = new ShiftForm();
        form.setId(shift.getId());
        form.setWorkDate(shift.getWorkDate().toString());
        form.setStartTime(shift.getStartTime().format(TIME_FORMATTER));
        form.setEndTime(shift.getEndTime().format(TIME_FORMATTER));
        form.setStaffId(shift.getStaff().getId());
        form.setNote(shift.getNote());

        model.addAttribute("shiftForm", form);
        model.addAttribute("staff", staffRepository.findAll());
        return "shifts/edit";
    }

    @PostMapping("/shifts/{id}/edit")
    public String updateShift(@PathVariable Long id,
        @ModelAttribute
        @Valid ShiftForm shiftForm,
        BindingResult bindingResult,
        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("staff", staffRepository.findAll());
            return "shifts/edit";
        }

        Shift shift = shiftRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid shift ID"));

        Staff staff = staffRepository.findById(shiftForm.getStaffId())
            .orElseThrow(() -> new IllegalArgumentException("Invalid staff ID"));

        LocalDate workDate = LocalDate.parse(shiftForm.getWorkDate());
        LocalTime startTime = LocalTime.parse(shiftForm.getStartTime());
        LocalTime endTime = LocalTime.parse(shiftForm.getEndTime());

        shift.setWorkDate(workDate);
        shift.setStartTime(startTime);
        shift.setEndTime(endTime);
        shift.setStaff(staff);
        shift.setNote(shiftForm.getNote());

        shiftRepository.save(shift);

        return "redirect:/shifts";
    }

    @PostMapping("/shifts/{id}/delete")
    public String deleteShift(@PathVariable Long id) {
        shiftRepository.findById(id)
            .orElseThrow(() ->new IllegalArgumentException("Invalid shift ID: " + id));

        shiftRepository.deleteById(id);

        System.out.println("** deletedShift 呼ばれたid=" + id);

        return "redirect:/shifts";  
    }  
}
