package com.ty.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.online.model.User;
import com.ty.online.service.UserService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/v1/rest")
@Slf4j
public class UserController {

	@Autowired
	UserService userService;

	@PostMapping
	public ResponseEntity<?> addData(@RequestBody User user) {
		if (user != null) {
			log.info("Data added");
			User addUser = userService.addUser(user);
			return new ResponseEntity<>(addUser, HttpStatus.OK);
		}
		log.error("User not found");
		return new ResponseEntity<>("Something wenting wrong!!!!!!!!!", HttpStatus.NOT_FOUND);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> getData(@PathVariable("id") int id) {
		if (id != 0) {
			log.info("Data fetched");
			User data = userService.getData(id);
			return new ResponseEntity<>(data, HttpStatus.OK);
		}
		log.error("ID not found");
		return new ResponseEntity<>("Something went wrong check it out !!!", HttpStatus.BAD_REQUEST);

	}

	@PutMapping(path = "/id")
	public ResponseEntity<?> updateData(@RequestBody User user) {
		if (user.getId() != 0) {
			log.info("Data Updated");
			User updateData = userService.updateData(user);
			return new ResponseEntity<>(updateData, HttpStatus.OK);
		}
		log.error("ID not found or Invalid ID");
		return new ResponseEntity<>("Something went wrong check it out !!!", HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteData(@PathVariable("id") int id) {
		if (id != 0) {
			log.info("Data delted");
			userService.deleteData(id);
			return new ResponseEntity<>("Data delted", HttpStatus.OK);
		}
		log.error("ID not found or Invalid ID");
		return new ResponseEntity<>("Wrongggg", HttpStatus.BAD_REQUEST);
	}

	@GetMapping
	public ResponseEntity<List<User>> getAllUserData() {
		List<User> allData = userService.getAllData();
		if (allData.isEmpty()) {
			log.error("No Data ");
			return new ResponseEntity<>(allData, HttpStatus.NO_CONTENT);
		}
		log.info("All Data fetched");
		return new ResponseEntity<>(allData, HttpStatus.OK);
	}
}
