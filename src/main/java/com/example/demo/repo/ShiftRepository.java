package com.example.demo.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Shift;
import com.example.demo.domain.Staff;


public interface ShiftRepository extends JpaRepository<Shift, Long> {
	
	List<Shift> findByStaff(Staff staff); // Staffオブジェクトで検索するメソッドを追加
	
	List<Shift> findByStaffId(Long staffId); // staffIdで検索するメソッドを追加
	
	List<Shift> findByWorkDate(LocalDate workDate); // workDateで検索するメソッドを追加
	
	List<Shift> findByStaffIdAndWorkDate(Long staffId, LocalDate workDate); // staffIdとworkDateで検索するメソッドを追加

}
