package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.User;

public interface LoginRepository extends JpaRepository<User, Integer>{
	@Query("SELECT u FROM User u WHERE u.userName = :userName")
	User findUserByUserName(@Param("userName") String userName);
}
