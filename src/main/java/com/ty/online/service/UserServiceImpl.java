package com.ty.online.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ty.online.model.User;
import com.ty.online.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RestTemplate restTemplate;
	String url = "https://gorest.co.in/public/v2/users";

	@Override
	public User addUser(User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("5ef6bdc800ae2ab62b7872254684fa01715f9b87f47f51e77e8497fb4ad7755e");
		HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
		User user2 = restTemplate.postForObject(url, httpEntity, User.class);
		User user1 = new User();
		BeanUtils.copyProperties(user2, user1);
		User user3 = userRepository.save(user1);
		BeanUtils.copyProperties(user3, user2);
		return user2;

	}

	@Override
	public User getData(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("5ef6bdc800ae2ab62b7872254684fa01715f9b87f47f51e77e8497fb4ad7755e");
		HttpEntity<User> httpEntity = new HttpEntity<>(null, headers);
		ResponseEntity<User> user = restTemplate.exchange(url + "/" + id, HttpMethod.GET, httpEntity, User.class);
		User body = user.getBody();
		userRepository.findById(id).get();
		return body;
	}

	@Override
	public User updateData(User user) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("5ef6bdc800ae2ab62b7872254684fa01715f9b87f47f51e77e8497fb4ad7755e");
		HttpEntity<User> httpEntity = new HttpEntity<>(user, headers);
		ResponseEntity<User> user1 = restTemplate.exchange(url + "/" + user, HttpMethod.PUT, httpEntity, User.class);
		userRepository.save(user1.getBody());
		return user;
	}

	@Override
	public void deleteData(int id) {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("5ef6bdc800ae2ab62b7872254684fa01715f9b87f47f51e77e8497fb4ad7755e");
		HttpEntity<User> httpEntity = new HttpEntity<>(null, headers);
		ResponseEntity<User> user = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, httpEntity, User.class);
		User body = user.getBody();
		userRepository.findById(id).get().getId();
		userRepository.deleteById(id);
	}

	@Override
	public List<User> getAllData() {
		HttpHeaders headers = new HttpHeaders();
		headers.setBearerAuth("5ef6bdc800ae2ab62b7872254684fa01715f9b87f47f51e77e8497fb4ad7755e");
		HttpEntity<User> httpEntity = new HttpEntity<>(null, headers);
		ResponseEntity<List<User>> exchange = restTemplate.exchange(url, HttpMethod.GET, httpEntity,
				new ParameterizedTypeReference<List<User>>() {
				});
		List<User> body = exchange.getBody();
		List<User> list = new ArrayList<>();
		for (User user : body) {
			User user2 = new User();
			BeanUtils.copyProperties(user, user2);
			System.out.println(user2);
			list.add(user2);
		}
		System.out.println(body + " List of Users");
		List<User> saveAll = userRepository.saveAll(list);
		return saveAll;
	}
}
