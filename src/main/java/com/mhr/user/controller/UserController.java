package com.mhr.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mhr.user.model.User;
import com.mhr.user.serviece.UserService;

@RestController
public class UserController {

	
	private UserService userService;
	
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping(value = "/test")
	@PreAuthorize("hasAuthority('ADMIN')")
	public ResponseEntity<?> Test(){
		return new ResponseEntity<>("this is ok",HttpStatus.ACCEPTED);
	}


	@GetMapping(value = {"/show/{userId}"})
	@PreAuthorize("#userId == principal.id")
	public ResponseEntity<User> getUser(@PathVariable("userId") Long userId){
		return new ResponseEntity<User>(userService.findById(userId),HttpStatus.OK);
	}
	
	@GetMapping(value = "/shows")
	public ResponseEntity<List<User>> findAllUser(){
		return new ResponseEntity<List<User>>(this.userService.findAllUsers(),HttpStatus.OK);
	}

	@PostMapping(value = "/add")
	@PreAuthorize("permitAll()")
	public ResponseEntity<?> save(@RequestBody User user) {
		user.setId((long) 0);	
		return new ResponseEntity<User>(userService.saveUser(user) , HttpStatus.CREATED);
	}
	
	@PutMapping(value = "/update")
	public ResponseEntity<User> updateUser(@RequestBody User user){
		this.userService.saveUser(user);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	@PreAuthorize("hasAuthority('ADMIN')")
	@DeleteMapping(value = "/deleteAll")
	public ResponseEntity<Void> deleteAllUser(){
		this.userService.deleteAllUser();
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") Long userId){
		this.userService.deleteById(userId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	
}
