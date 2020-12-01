package com.vm.numbergenerator.controller;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vm.numbergenerator.dto.NumGenerateRequestDTO;
import com.vm.numbergenerator.service.NumberGeneratorService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(value="/api/")
@AllArgsConstructor
public class NumberGeneratorController {
	
	private final NumberGeneratorService numGeneratorService;
	

	@PostMapping(value="/generate")
	public String initiateNumberGeneration(@Valid @RequestBody NumGenerateRequestDTO dto) throws InterruptedException, ExecutionException {
		
		return numGeneratorService.initiateNumberGeneration(dto);
	}
	
	@GetMapping(value="/tasks/{id}/status")
	public String getStatus(@PathVariable("id") UUID id) {
		return "test";
	}
	
	@GetMapping(value="/tasks/{id}")
	public String getSequence(@PathVariable("id") UUID id, @RequestParam String action) {
		return "test";
	}
}
