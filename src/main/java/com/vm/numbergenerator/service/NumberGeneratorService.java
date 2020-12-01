package com.vm.numbergenerator.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vm.numbergenerator.dto.NumGenerateRequestDTO;

@Service
public class NumberGeneratorService {
	
	@Async("priceExecutor")
	public String initiateNumberGeneration(NumGenerateRequestDTO dto) {
		generate();
		
		return "Started";
	}

	private void generate() {

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Generated");
	}
}
