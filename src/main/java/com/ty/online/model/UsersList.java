package com.ty.online.model;

import java.util.List;

public class UsersList {
	private List<User> users;

	public UsersList(List<User> users) {
		super();
		this.users = users;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
}
