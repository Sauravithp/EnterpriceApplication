package com.example.exercise03_2.domain.BidirectionalOneToMany;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long employeenumber;
	@NonNull
	private String name;
	@ManyToOne
	@JoinColumn(name="department_id")
	@NonNull
	private Department department;
}
