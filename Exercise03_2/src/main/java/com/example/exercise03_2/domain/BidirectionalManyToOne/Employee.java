package com.example.exercise03_2.domain.BidirectionalManyToOne;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity(name="ManyToOneEmployee")
@Data
@RequiredArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeenumber;
	@NonNull
	private String name;
	@ManyToOne
	@JoinColumn(name = "office_id")
	@NonNull
	private Office office;
}

