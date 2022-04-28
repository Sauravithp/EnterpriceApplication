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
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int studentid;
	@NonNull
	private String firstname;
	private String lastname;
	@ManyToMany
	@JoinTable(
			name="Student_Course",
			joinColumns = { @JoinColumn(name="Studnet_id") },
			inverseJoinColumns = { @JoinColumn(name="Course_id")}
	)
	private Collection<Course> course = new ArrayList<Course>();
}
