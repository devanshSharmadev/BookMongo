package com.bookapp.repository;

import org.bson.Document;
import static org.bson.codecs.configuration.CodecRegistries.*;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.bookapp.model.Book;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class DbManager {
	
	static MongoClient mongoInstance;

	public static void openConnection() {
		
		String connectionString="mongodb://localhost:27017";
		ConnectionString connection=new ConnectionString(connectionString);
		
		CodecRegistry pojoCodecRegistry=fromProviders(PojoCodecProvider.builder().automatic(true).build());
		CodecRegistry codecRegistry=fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),pojoCodecRegistry);
		MongoClientSettings clientSettings=MongoClientSettings.builder().applyConnectionString(connection).codecRegistry(codecRegistry).build();
		mongoInstance=MongoClients.create(clientSettings);
		
	}
	public static void closeConnection() {
		mongoInstance.close();
	}
	
	public MongoDatabase findDatabase() {
		MongoDatabase database=mongoInstance.getDatabase("klouddb");
		return database;
	}
	
	public MongoCollection<Book> findCollection(){
		MongoDatabase database=findDatabase();
		MongoCollection<Book> collection=database.getCollection("book",Book.class);
		return collection;  
	}
	
}
