package com.vm.numbergenerator.validator;

import com.vm.numbergenerator.dto.NumGenerateRequestDTO;

public class NumberGeneratorRequestValidator {

	public static boolean validateStepAndGoal(NumGenerateRequestDTO dto) {
		if (Integer.parseInt(dto.getStep()) > Integer.parseInt(dto.getGoal())) {
			return false;
		}
		return true;

	}
}
