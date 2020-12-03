package com.vm.numbergenerator.controller;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vm.numbergenerator.constants.ActionConstants;
import com.vm.numbergenerator.constants.MessageConstants;
import com.vm.numbergenerator.dto.NumGenerateRequestDTO;
import com.vm.numbergenerator.service.NumberGeneratorService;
import com.vm.numbergenerator.validator.NumberGeneratorRequestValidator;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value = "/api/")
@AllArgsConstructor
public class NumberGeneratorController {

	private final NumberGeneratorService numGeneratorService;

	@PostMapping(value = "/generate")
	public ResponseEntity<?> initiateNumberGeneration(@Valid @RequestBody NumGenerateRequestDTO dto)
			throws InterruptedException, ExecutionException {

		if (!NumberGeneratorRequestValidator.validateStepAndGoal(dto)) {
			return new ResponseEntity<>(MessageConstants.INVALID_GOAL_STEP, HttpStatus.BAD_REQUEST);
		}

		UUID id = numGeneratorService.initiateNumberGeneration(dto);
		numGeneratorService.generateSequence(id);
		return new ResponseEntity<>(id, HttpStatus.OK);

	}

	@GetMapping(value = "/tasks/{id}/status")
	public ResponseEntity<String> getStatus(@PathVariable("id") UUID id) {
		return new ResponseEntity<>(numGeneratorService.getStatus(id), HttpStatus.OK);
	}

	@GetMapping(value = "/tasks/{id}")
	public ResponseEntity<?> getSequence(@PathVariable("id") UUID id, @RequestParam String action) {
		if (action == ActionConstants.GET_NUMLIST.getText()) {
			return new ResponseEntity<>(numGeneratorService.getSequence(id), HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(MessageConstants.INVALID_ACTION, HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping(value = "/bulkGenerate")
	public ResponseEntity<UUID> initiateBulkSequenceGeneration(@Valid @RequestBody List<NumGenerateRequestDTO> dto) {

		UUID generateBulkNumber = numGeneratorService.generateBulkNumber(dto);
		numGeneratorService.generateSequence(generateBulkNumber);
		return new ResponseEntity<>(generateBulkNumber, HttpStatus.OK);
	}
}
