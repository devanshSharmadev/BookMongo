package com.bookapp.repository;

import java.util.List;

import org.bson.Document;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdkNotFoundException;
import com.bookapp.model.Book;

public interface IBookRepository {
	
	void addBook(Book book);
	void updateBook(int bookid,double price);
	void deleteBook(int bookid);
	
	List<Book> findAll();
	List<Book> findByAuthor(String author) throws BookNotFoundException;
	List<Book> findByCategory(String category)throws BookNotFoundException;
	Book findById(int bookId)throws IdkNotFoundException;
	List<Book> findByLesserPrice(double price) throws BookNotFoundException;

}
