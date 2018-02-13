package com.softuni.books.services;

import java.util.Collection;

import com.softuni.books.models.bindingModels.AddBookModel;
import com.softuni.books.models.viewModels.ViewBookModel;

public interface BookService {
	void saveBook(AddBookModel addBookModel);
	
	Collection<ViewBookModel> getAllBooks();
	
	ViewBookModel findBookByTitle(String title);
	
	void deleteBookByTitle(String title);

}
