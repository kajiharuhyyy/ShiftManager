package com.example.demo.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.domain.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
	
	Optional<Staff> findByName(String name); // 追加

}
