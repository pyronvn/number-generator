package com.vm.numbergenerator.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NumGenerateRequestDTO {

	@Min(1)
	@Max(Integer.MAX_VALUE)
	@Pattern(regexp = "^[0-9]*$")
	private String goal;
	
	@Min(1)
	@Max(Integer.MAX_VALUE)
	@Pattern(regexp = "^[0-9]*$")
	private String step;
}
