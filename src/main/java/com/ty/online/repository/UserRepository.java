package com.ty.online.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.ty.online.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByid(int id);
}
