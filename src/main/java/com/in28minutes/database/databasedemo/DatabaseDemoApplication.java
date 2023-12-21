package com.in28minutes.database.databasedemo;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.in28minutes.database.databasedemo.entity.Person;
import com.in28minutes.database.databasedemo.jdbc.PersonJdbcDao;

@SpringBootApplication
public class DatabaseDemoApplication implements CommandLineRunner {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	PersonJdbcDao dao;
	
	public static void main(String[] args) {
		SpringApplication.run(DatabaseDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		logger.info("All users -> {}", dao.findAll());
		logger.info("User id 10001 -> {}", dao.findById(10001));
		logger.info("User with name 'Raj' -> {}", dao.findByName("Ranga"));
		logger.info("Location 'New York' -> {}", dao.findByLocation("New York"));		
		//logger.info("delete 10001 -> {}", dao.deleteById(10001));
		//logger.info("Delete by id and location -> {}", dao.deleteByIdAndLocation(10002, "New York"));
		//logger.info("Delete by id or name -> {}", dao.deleteByIdOrName(10000, "Ranga"));
		
		logger.info("Insert into -> {}", dao.insert(new Person(10005,"Rajeh","New Delhi",new Date())));
		
		logger.info("Update -> {}", dao.update(new Person(10005,"Rajeh111","New Delhi",new Date())));
	}

}
