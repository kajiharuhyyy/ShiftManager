package com.example.demo.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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


}
