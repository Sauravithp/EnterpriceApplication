package com.example.exercise03_2.domain.UnidirectionalManyToOne;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity(name="ManyToOneReservation")
@Data
@RequiredArgsConstructor
public class Reservation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NonNull
	private LocalDate date;
	@ManyToOne
	@JoinTable(name = "ManyToOneReservation_book")
	private Book book;
}
