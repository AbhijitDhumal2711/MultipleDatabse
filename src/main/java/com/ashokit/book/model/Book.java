package com.ashokit.book.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "BOOK_TB")
public class Book {

	@Id
	private int id;
	private String name;
	public Book(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
}
