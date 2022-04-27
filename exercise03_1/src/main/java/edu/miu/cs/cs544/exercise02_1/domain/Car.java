package edu.miu.cs.cs544.exercise02_1.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Getter
@Setter
@ToString
public class Car {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String brand;

	private String year;

	private double price;
	@ManyToOne
	@JoinTable(name="Car_Owner")
	@Cascade(org.hibernate.annotations.CascadeType.PERSIST)
	private Owner owner;

	public Car() {
	}

	public Car(String brand, String year, double price) {
		this.brand = brand;
		this.year = year;
		this.price = price;
	}
}
