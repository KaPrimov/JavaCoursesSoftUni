package com.softuni.books.services;

import java.util.Collection;

import com.softuni.books.daos.BookRepositoryImpl;
import com.softuni.books.entities.Book;
import com.softuni.books.models.bindingModels.AddBookModel;
import com.softuni.books.models.viewModels.ViewBookModel;
import com.softuni.books.utils.DTOConvertUtil;

public class BookServiceImpl implements BookService {

	@Override
	public void saveBook(AddBookModel addBookModel) {
		BookRepositoryImpl.getInstance().saveBook(DTOConvertUtil.convert(addBookModel, Book.class));
	}

	@Override
	public Collection<ViewBookModel> getAllBooks() {
		return DTOConvertUtil.convertToSet(BookRepositoryImpl.getInstance().getAllBooks(), ViewBookModel.class);
	}

	@Override
	public ViewBookModel findBookByTitle(String title) {
		return DTOConvertUtil.convert(BookRepositoryImpl.getInstance().findBookByTitle(title), ViewBookModel.class);
	}

	@Override
	public void deleteBookByTitle(String title) {
		BookRepositoryImpl.getInstance().deleteBookByTitle(title);
	}

}
