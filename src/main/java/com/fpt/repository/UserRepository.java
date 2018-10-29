package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("SELECT u FROM User u WHERE u.username = :username")
	User findUserByUserName(@Param("username") String username);

	@Query("SELECT u FROM User u WHERE u.email = :email")
	User findUserByEmail(@Param("email") String email);

	// search by user name
	@Query("SELECT u FROM User u WHERE u.username like :keySearch")
	Page<User> getUserByUsername(@Param("keySearch") String keySearch, Pageable pageable);

	// filter by role
	@Query("SELECT u FROM User u WHERE u.role.id = :role")
	Page<User> getUserByRole(@Param("role") int role, Pageable pageable);

	// filter by creator
	@Query("SELECT u FROM User u WHERE u.creator = :creator")
	Page<User> getUserByCreator(@Param("creator") int creator, Pageable pageable);

	//filter role and search
	@Query("SELECT u FROM User u WHERE u.role.id = :role AND u.username like :keySearch")
	Page<User> getUserByRoleSearch(@Param("role") int role, @Param("keySearch") String keySearch, Pageable pageable);
	
	//filter creator and search
	@Query("SELECT u FROM User u WHERE u.creator = :creator AND u.username like :keySearch")
	Page<User> getUserByCreatorSearch(@Param("creator") int creator, @Param("keySearch") String keySearch, Pageable pageable);
	
	//filter role, creator and search
	@Query("SELECT u FROM User u WHERE u.role.id = :role AND u.creator = :creator AND u.username like :keySearch")
	Page<User> getUserByRoleCreatorSearch(@Param("role") int role, @Param("creator") int creator, @Param("keySearch") String keySearch, Pageable pageable);
	
	//filter role and creator
	@Query("SELECT u FROM User u WHERE u.role.id = :role AND u.creator = :creator")
	Page<User> getUserByRoleCreator(@Param("role") int role, @Param("creator") int creator, Pageable pageable);
}
