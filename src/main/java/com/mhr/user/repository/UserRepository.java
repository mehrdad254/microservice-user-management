package com.mhr.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mhr.user.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmaile(String emaile);
}
