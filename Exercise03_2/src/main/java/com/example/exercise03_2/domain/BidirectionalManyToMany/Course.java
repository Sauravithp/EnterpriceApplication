package com.example.exercise03_2.domain.BidirectionalManyToMany;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int coursenumber;
	@NonNull
	private String name;
	@ManyToMany(mappedBy = "course")
	private Collection<Student> course = new ArrayList<Student>();
}
