package com.ashokit.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ashokit.book.model.Book;
import com.ashokit.book.repository.BookRepository;
import com.ashokit.service.ResponseStructure;
import com.ashokit.service.UserService;
import com.ashokit.user.model.User;
import com.ashokit.user.repository.UserRepository;

@RestController
public class DemoRestController {

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserService  service;

	@GetMapping("/addData")
	public String addData2DB() {
		userRepository.saveAll(Stream.of(new User(744, "John"), new User(455, "Smith")).collect(Collectors.toList()));
		bookRepository.saveAll(Stream.of(new Book(111, "Core Java"), new Book(222, "Spring Boot")).collect(Collectors.toList()));
		return "Data Added Successfully";
	}

	@GetMapping("/getUsers")
	public List<User> getUsers() {
		return userRepository.findAll();
	}
	
	
//	@PostMapping("/users")
//	public ResponseStructure<ResponseEntity<User>> saveUser(@RequestBody User user) {
//		
//		ResponseStructure<User> structure=new ResponseStructure<User>();
//		User user2=service.saveUser(user);
//		structure.setData(user2);
//		structure.setCode(HttpStatus.ACCEPTED.value());
//		structure.setMsg("Added");
//		
//		
//		return new ResponseStructure<ResponseEntity<User>>(structure,HttpStatus.OK);
//	}

	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<User>> saveUser(@RequestBody User user) {
	    // Create ResponseStructure for the User
	    ResponseStructure<User> structure = new ResponseStructure<>();
	    
	    // Save the user using the service
	    User savedUser = service.saveUser(user);
	    
	    // Set the data, status code, and message in the structure
	    structure.setData(savedUser);
	    structure.setCode(HttpStatus.CREATED.value()); // 201 Created is more appropriate than 200 OK
	    structure.setMsg("User added successfully");
	    
	    // Return ResponseEntity with ResponseStructure as body and HttpStatus.CREATED (201)
	    return new ResponseEntity<>(structure, HttpStatus.CREATED);
	}
	
	@GetMapping("/getBooks")
	public List<Book> getBooks() {
		return bookRepository.findAll();
	}

}
