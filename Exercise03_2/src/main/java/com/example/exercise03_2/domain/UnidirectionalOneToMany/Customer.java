package com.example.exercise03_2.domain.UnidirectionalOneToMany;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Data
@RequiredArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private String name;
	@OneToMany
	@JoinTable(
			name="Customer_Reservation",
			joinColumns = { @JoinColumn(name="Customer_id") },
			inverseJoinColumns = { @JoinColumn(name="Resevation_id")}
	)
	private Collection<Reservation> reservation = new ArrayList<Reservation>();
}
