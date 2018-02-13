package com.softuni.books.models.viewModels;

public class ViewBookModel {
	
	private String title;
	private String author;
	private String pages;
	
	public ViewBookModel() {
		super();
	}

	public ViewBookModel(String title, String author) {
		this.title = title;
		this.author = author;
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

	public String getPages() {
		return pages;
	}

	public void setPages(String pages) {
		this.pages = pages;
	}
	
}
