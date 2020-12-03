package com.vm.numbergenerator.dto;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BulkNumGenerateRequestDTO {

	@NotEmpty
	@NonNull
	private List<NumGenerateRequestDTO> data = new ArrayList<>();
}
