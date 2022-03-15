package com.bookapp.main;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdkNotFoundException;
import com.bookapp.model.*;
import com.bookapp.repository.DbManager;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.bookapp.service.BookServiceImpl;
import com.bookapp.service.IBookService;

public class Client {

	public static void main(String[] args) {
		
		
		DbManager.openConnection();
		IBookService bookService=new BookServiceImpl();
		Book obook=new Book("All in one",6,"Kethy",1900,"Tech");
		bookService.addBook(obook);
		
		System.out.println("All Books");
		List<Book> allBooks=bookService.findAll();
		for(Book book:allBooks) {
			System.out.println(book);
		}
		System.out.println();
		System.out.println("Books by Author");
		
		try {
			List<Book> booksByAuthor=bookService.getByAuthor("Kathy");
			for(Book book:booksByAuthor) {
				System.out.println(book);
			}
		}catch(BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println();
		System.out.println("Books by Category");
		try {
			List<Book> booksByCategory=bookService.getByCategory("Selfhelp1");
			for(Book book:booksByCategory) {
				System.out.println(book);
			}
		}catch(BookNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Book By Id");
		try {
			Book nbook=bookService.getById(1);
			System.out.println(nbook);
		}catch(IdkNotFoundException e) {
			System.out.println(e.getMessage());
		}
		DbManager.closeConnection();
		
	
	}
}
