package com.vm.numbergenerator.service;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.vm.numbergenerator.constants.Status;
import com.vm.numbergenerator.dto.NumGenerateRequestDTO;
import com.vm.numbergenerator.entity.Sequence;
import com.vm.numbergenerator.entity.SequenceData;
import com.vm.numbergenerator.repository.NumberGeneratorRepository;

@ExtendWith(MockitoExtension.class)
public class NumberGeneratorServiceTest {

	@InjectMocks
	private NumberGeneratorService numberGenService;

	@Mock
	private NumberGeneratorRepository numGenRepo;

	@Test
	public void initiateNumberGenerationHAppyPath() {
		UUID randomUUID = UUID.randomUUID();
		Sequence seq = new Sequence();

		NumGenerateRequestDTO dto = new NumGenerateRequestDTO("10", "2");

		seq.setId(randomUUID);

		when(numGenRepo.save(any())).thenReturn(seq);

		UUID initiateNumberGeneration = numberGenService.initiateNumberGeneration(dto);

		assertEquals(initiateNumberGeneration, randomUUID);
	}

	@Test
	public void getSequenceHappyPath() {
		Sequence seq = new Sequence();
		seq.setStatus(Status.SUCCESS.getText());
		SequenceData data = new SequenceData(1L, seq, "10", "2");

		List<SequenceData> list = new ArrayList<>();
		list.add(data);

		seq.setData(list);

		when(numGenRepo.findById(any())).thenReturn(Optional.of(seq));
		
		List<List<Integer>> response = numberGenService.getSequence(UUID.randomUUID());
		
		assertEquals(response.get(0).size(), 6);
	}
	
	@Test
	public void getSequenceInvalidStatus() {
		Sequence seq = new Sequence();
		seq.setStatus(Status.CREATED.getText());
		SequenceData data = new SequenceData(1L, seq, "10", "2");

		List<SequenceData> list = new ArrayList<>();
		list.add(data);

		seq.setData(list);

		when(numGenRepo.findById(any())).thenReturn(Optional.of(seq));
		
		List<List<Integer>> response = numberGenService.getSequence(UUID.randomUUID());
		
		assertEquals(response.size(), 0);
	}
}
