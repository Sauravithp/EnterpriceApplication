package com.example.exercise03_2.domain.OptionalUnidirectionalManyToOne;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int isbn;
	@NonNull
	private String title;
	@NonNull
	private String author;
	@ManyToOne
	@JoinTable(name = "book_publisher")
	private Publisher publisher;
}
