package com.example.demo.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
	
@Entity
@Table(name = "shifts")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Shift {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "work_date", nullable = false)
	private LocalDate workDate;
	
	@Column(name = "start_time", nullable = false)
	private LocalTime startTime;
	
	@Column(name = "end_time", nullable = false)
	private LocalTime endTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "staff_id", nullable = false)
	private Staff staff;
	
	@Column(length = 500)
	private String note;
}
