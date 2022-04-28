package com.example.exercise03_2.domain.BidirectionalOneToMany;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Department {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NonNull
	private String name;
	@OneToMany(mappedBy = "department")
	Collection<Employee> employee = new ArrayList<Employee>();
}
