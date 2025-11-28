package com.example.demo.web;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class ShiftForm {

	private Long id;

	@NotBlank(message = "勤務日は必須項目です。")
	private String workDate;

	@NotBlank(message = "開始時間は必須項目です。")
	private String startTime;

	@NotBlank(message = "終了時間は必須項目です。")
	private String endTime;

	@NotBlank(message = "スタッフIDは必須項目です。")
	private Long staffId;

	private String note;
}
