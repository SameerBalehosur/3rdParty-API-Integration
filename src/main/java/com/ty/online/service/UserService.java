package com.ty.online.service;

import java.util.List;

import com.ty.online.model.User;

public interface UserService {
	public User addUser(User user);

	public User getData(int id);

	public User updateData(User user);

	public void deleteData(int id);
	
	public List<User> getAllData();

}
