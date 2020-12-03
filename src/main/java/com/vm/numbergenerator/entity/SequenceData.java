package com.vm.numbergenerator.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SEQUENCE_DATA")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SequenceData implements Serializable {

	// Batchdata

	private static final long serialVersionUID = 6023822753133923589L;

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "uuid")
	private Sequence sequenceVal;

	@Column(name = "goal", nullable = false)
	private String goal;

	@Column(name = "step", nullable = false)
	private String step;

}
