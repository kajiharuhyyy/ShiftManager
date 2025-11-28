package com.example.demo.web.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

	@NotNull(message = "スタッフIDは必須項目です。")
	private Long staffId;

	private String note;
}
