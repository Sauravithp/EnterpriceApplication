package com.example.exercise03_2.domain.BidirectionalManyToOne;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Office {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roomnumber;
	@NonNull
	private String building;
	@OneToMany(mappedBy = "office")
	@NonNull
	private Collection<Employee> employees = new ArrayList<Employee>();
}
