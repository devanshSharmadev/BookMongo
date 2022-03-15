package com.bookapp.service;

import java.util.Collections;
import java.util.List;

import org.bson.Document;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdkNotFoundException;
import com.bookapp.model.Book;
import com.bookapp.repository.BookRepositoryImpl;
import com.bookapp.repository.IBookRepository;


public class BookServiceImpl implements IBookService {

	IBookRepository bookRepository=new BookRepositoryImpl();
	
	
	@Override
	public List<Book> findAll() {
		
		return bookRepository.findAll();
	}

	@Override
	public List<Book> getByAuthor(String author) throws BookNotFoundException {
		// TODO Auto-generated method stub
		List<Book> booksByAuthor=bookRepository.findByAuthor(author);
		if(booksByAuthor!=null)
			Collections.sort(booksByAuthor,(b1,b2)->b1.getTitle().compareTo(b2.getTitle()));
		
		return booksByAuthor;
	}

	@Override
	public List<Book> getByCategory(String category) throws BookNotFoundException {
		// TODO Auto-generated method stub
		
		List<Book> booksByCategory=bookRepository.findByCategory(category);
		if(booksByCategory!=null)
			Collections.sort(booksByCategory,(b1,b2)->b1.getTitle().compareTo(b2.getTitle()));
		
		return booksByCategory;
		
		
	}

	@Override
	public Book getById(int bookId) throws IdkNotFoundException {
		return bookRepository.findById(bookId);
		
	}

	@Override
	public List<Book> getByLesserPrice(double price) throws BookNotFoundException {
		
		List<Book> booksByPrice=bookRepository.findByLesserPrice(price);
		if(booksByPrice!=null)
			Collections.sort(booksByPrice,(b1,b2)->b1.getTitle().compareTo(b2.getTitle()));
		
		return booksByPrice;
		
	}

	@Override
	public void addBook(Book book) {
		
		bookRepository.addBook(book);
		
	}

	@Override
	public void updateBook(int bookid, double price) {
		
		bookRepository.updateBook(bookid,price);
		
	}

	@Override
	public void deleteBook(int bookid) {
		
		bookRepository.deleteBook(bookid);
		
	}

}
