package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.User;

public interface AccountRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findUserByUserName(@Param("username") String username);
}