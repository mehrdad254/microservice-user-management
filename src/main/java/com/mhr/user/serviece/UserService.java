package com.mhr.user.serviece;

import java.util.List;

import com.mhr.user.model.User;


public interface UserService {


	List<User> findAllUsers();

	User findById(Long userId);

	void deleteById(Long userId);

	User saveUser(User user);

	void deleteAllUser();



}
