package com.vm.numbergenerator.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vm.numbergenerator.entity.Sequence;

@Repository
public interface NumberGeneratorRepository extends JpaRepository<Sequence, UUID> {

}
