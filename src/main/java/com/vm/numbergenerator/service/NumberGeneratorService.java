package com.vm.numbergenerator.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.vm.numbergenerator.constants.Status;
import com.vm.numbergenerator.dto.NumGenerateRequestDTO;
import com.vm.numbergenerator.entity.Sequence;
import com.vm.numbergenerator.entity.SequenceData;
import com.vm.numbergenerator.repository.NumberGeneratorRepository;

@Service
public class NumberGeneratorService {

	private final NumberGeneratorRepository repo;

	public NumberGeneratorService(NumberGeneratorRepository repo) {
		this.repo = repo;
	}

	public UUID initiateNumberGeneration(NumGenerateRequestDTO dto) {

		Sequence sequenceEntity = new Sequence();
		sequenceEntity.setStatus(Status.CREATED.getText());

		List<SequenceData> list = new ArrayList<>();
		mapDTOtoEntity(dto, sequenceEntity, list);

		sequenceEntity.setData(list);

		Sequence saveAndFlush = repo.save(sequenceEntity);
		return saveAndFlush.getId();
	}

	private List<SequenceData> mapDTOtoEntity(NumGenerateRequestDTO dto, Sequence sequenceEntity,
			List<SequenceData> list) {
		SequenceData data = new SequenceData();
		data.setGoal(dto.getGoal());
		data.setStep(dto.getStep());

		data.setSequenceVal(sequenceEntity);
		list.add(data);
		return list;
	}

	@Async("numberExecutor")
	public void generateSequence(UUID id) {

		Optional<Sequence> entity = repo.findById(id);

		if (entity.isPresent() && entity.get().getStatus() == Status.CREATED.getText()) {
			entity.get().setStatus(Status.IN_PROGRESS.getText());
			Sequence save = repo.save(entity.get());

			try {
				Thread.sleep(30000);
				save.setStatus(Status.SUCCESS.getText());
				repo.save(save);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public String getStatus(UUID uuid) {

		Optional<Sequence> entity = repo.findById(uuid);
		if (entity.isPresent()) {
			return entity.get().getStatus();
		} else {
			return "INVALID";
		}
	}

	public List<List<Integer>> getSequence(UUID uuid) {

		Optional<Sequence> entity = repo.findById(uuid);
		List<List<Integer>> result = new ArrayList<>();

		if (entity.isPresent() && entity.get().getStatus().equalsIgnoreCase(Status.SUCCESS.getText())) {
			Sequence sequence = entity.get();
			if (sequence.getData().size() == 1) {

				result.add(mapEntityToDto(sequence.getData().get(0).getGoal(), sequence.getData().get(0).getStep()));
				return result;
			}

			else {
				return getBulkNumberResponse(sequence);
			}

		} else {
			return result;
		}

	}

	private List<Integer> mapEntityToDto(String goal, String step) {
		int start = Integer.parseInt(goal);
		int stepLength = Integer.parseInt(step);

		List<Integer> resultList = new ArrayList<>();

		int val = start;

		while (val >= 0) {
			resultList.add(val);
			val -= stepLength;
		}

		return resultList;
	}

	public UUID generateBulkNumber(List<NumGenerateRequestDTO> dto) {

		Sequence sequenceEntity = new Sequence();
		sequenceEntity.setStatus(Status.CREATED.getText());
		List<SequenceData> list = new ArrayList<>();
		for (NumGenerateRequestDTO individual : dto) {

			mapDTOtoEntity(individual, sequenceEntity, list);
		}
		sequenceEntity.setData(list);
		Sequence savedEntity = repo.save(sequenceEntity);

		return savedEntity.getId();
	}

	public List<List<Integer>> getBulkNumberResponse(Sequence entity) {
		List<List<Integer>> result = new ArrayList<>();

		for (SequenceData individualSequence : entity.getData()) {
			result.add(mapEntityToDto(individualSequence.getGoal(), individualSequence.getStep()));
		}
		return result;

	}

}
