 package com.bookapp.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.bookapp.exceptions.BookNotFoundException;
import com.bookapp.exceptions.IdkNotFoundException;
import com.bookapp.model.Book;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;

public class BookRepositoryImpl implements IBookRepository{

	DbManager dbManager = new DbManager();
	
	@Override
	public void addBook(Book book) {
		
		MongoCollection<Book> collection=dbManager.findCollection();
		collection.insertOne(book);
		
	}

	@Override
	public void updateBook(int bookid, double newprice) {
		
		MongoCollection<Book> collection=dbManager.findCollection();
		Bson filter=Filters.eq("_id",bookid);
		Bson update=Updates.set("price", newprice);
		UpdateResult result=collection.updateOne(filter, update);
		System.out.println(result);
	}

	@Override
	public void deleteBook(int bookid) {
		
		MongoCollection<Book> collection=dbManager.findCollection();
		DeleteResult result=collection.deleteOne(Filters.eq("_id",bookid));
		System.out.println(result);
		
		
	} 

	@Override
	public List<Book> findAll() {
		
		MongoCollection<Book> collection=dbManager.findCollection();
		
		return collection.find().into(new ArrayList<>( ));
	}

	@Override
	public List<Book> findByAuthor(String author) throws BookNotFoundException {
		
		MongoCollection<Book> collection=dbManager.findCollection();
		Document document=new Document("author",author);
		List<Book> books=collection.find(Filters.eq("author",author)).into(new ArrayList<>());
		if(books.isEmpty())
			throw new BookNotFoundException("author not found");
		
		return books;
	}

	@Override
	public List<Book> findByCategory(String category) throws BookNotFoundException {
		MongoCollection<Book> collection=dbManager.findCollection();
		
		List<Book> books=collection.find(Filters.eq("category",category)).into(new ArrayList<>());
		if(books.isEmpty())
			throw new BookNotFoundException("category not found");
		return books;
	}

	@Override
	public Book findById(int bookId) throws IdkNotFoundException {
		MongoCollection<Book> collection=dbManager.findCollection();
		Document document=new Document("_id",bookId);
		Book book=collection.find(document).first();
		if(book==null)
			throw new IdkNotFoundException("invalid id");
		return null;
	}

	@Override
	public List<Book> findByLesserPrice(double price) throws BookNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	

}
