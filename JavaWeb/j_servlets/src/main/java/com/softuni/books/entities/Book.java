package com.softuni.books.entities;

import java.time.LocalDate;

public class Book {
	private Long id;
	private String title;
	private String author;
	private Integer pages;
	private LocalDate creationDate;
	
	public Book(Long id, String title, String author, Integer pages, LocalDate creationDate) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.pages = pages;
		this.creationDate = creationDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public LocalDate getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(LocalDate creationDate) {
		this.creationDate = creationDate;
	}
		
}
