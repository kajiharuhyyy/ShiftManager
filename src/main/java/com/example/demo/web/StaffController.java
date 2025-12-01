package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.domain.Staff;
import com.example.demo.repo.StaffRepository;
import com.example.demo.web.form.StaffForm;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class StaffController {

    private final StaffRepository staffRepository;

    @GetMapping("/staffs")
    public String listStaffs(Model model) {
        model.addAttribute("staffs", staffRepository.findAll());
        return "staffs/list";
    } // スタッフ一覧をmodelに追加

    @GetMapping("/staffs/new")
    public String newStaffForm(Model model) {
        StaffForm form = new StaffForm();
        form.setActive(true);
        model.addAttribute("staffForm", form);
        return "staffs/new";
    } // 新規作成画面を表示するメソッド

    @PostMapping("/staffs")
    public String createStaff(@Valid @ModelAttribute StaffForm staffForm, 
        BindingResult bindingResult) {

            if(bindingResult.hasErrors()) {
                return "staffs/new";
            }
        
        Staff staff = Staff.builder()
            .name(staffForm.getName())
            .email(staffForm.getEmail())
            .active(staffForm.getActive())
            .build();

        staffRepository.save(staff);

        return "redirect:/staffs";
    }  // 新規スタッフを作成するためのメソッド

    @GetMapping("/staffs/{id}/edit")
    public String editStaffForm(@PathVariable Long id, Model model) {
        Staff staff = staffRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid staff id " + id));

        StaffForm form = new StaffForm();
        form.setId(staff.getId());
        form.setName(staff.getName());
        form.setEmail(staff.getEmail());
        form.setActive(staff.isActive());

        model.addAttribute("staffForm", form);
        return "staffs/edit";
    }


    @PostMapping("/staffs/{id}/edit")
    public String upDateStaff(@PathVariable Long id,
            @Valid @ModelAttribute StaffForm staffForm,
            BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
                return "staffs/edit";
        }
         
        Staff staff = staffRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid staff id " + id));

        staff.setName(staffForm.getName());
        staff.setEmail(staffForm.getEmail());
        staff.setActive(staffForm.getActive());

        staffRepository.save(staff);

        return "redirect:/staffs";
    }

            
        @PostMapping("/staffs/{id}/delete")
        public String deleteStaff(@PathVariable Long id) {
            staffRepository.deleteById(id);
            return "redirect:/staffs";
        }
}
