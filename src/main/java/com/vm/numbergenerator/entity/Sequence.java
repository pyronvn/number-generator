package com.vm.numbergenerator.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "SEQUENCE")
@Setter
@Getter
public class Sequence implements Serializable {

	private static final long serialVersionUID = -1736260273334454667L;

	@Id
	@Column(name = "uuid")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;

	@Column(name = "status", nullable = false)
	private String status;

	@OneToMany(mappedBy = "sequenceVal", cascade = CascadeType.ALL)
	private List<SequenceData> data = new ArrayList<>();

	
	


}
