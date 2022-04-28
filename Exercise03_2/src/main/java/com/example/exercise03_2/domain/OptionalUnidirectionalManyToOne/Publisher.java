package com.example.exercise03_2.domain.OptionalUnidirectionalManyToOne;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@RequiredArgsConstructor
public class Publisher {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isbn;
	@NonNull
	private String name;
}
