package com.softuni.books.models.bindingModels;

public class AddBookModel {
	private String title;
	private String author;
	private Integer pages;
	
	public AddBookModel(String title, String author, Integer pages) {
		this.title = title;
		this.author = author;
		this.pages = pages;
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
}
