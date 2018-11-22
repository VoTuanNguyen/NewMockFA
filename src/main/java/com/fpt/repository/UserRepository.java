package com.fpt.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fpt.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	User findUserByUsername(String username);

	User findUserByEmail(String email);

	// search by user name
	Page<User> getUserByUsernameContaining(String keySearch, Pageable pageable);

	// filter by role
	@Query("SELECT u FROM User u WHERE u.role.id = :role")
	Page<User> getUserByRole(@Param("role") int role, Pageable pageable);

	// filter by creator
	Page<User> getUserByCreator(int creator, Pageable pageable);

	// filter role and search
	@Query("SELECT u FROM User u WHERE u.role.id = :role AND u.username like :keySearch")
	Page<User> getUserByRoleSearch(@Param("role") int role, @Param("keySearch") String keySearch, Pageable pageable);

	// filter creator and search
	Page<User> getUserByCreatorAndUsernameContaining(int creator, String keySearch, Pageable pageable);

	// filter role, creator and search
	@Query("SELECT u FROM User u WHERE u.role.id = :role AND u.creator = :creator AND u.username like :keySearch")
	Page<User> getUserByRoleCreatorSearch(@Param("role") int role, @Param("creator") int creator,
			@Param("keySearch") String keySearch, Pageable pageable);

	// filter role and creator
	@Query("SELECT u FROM User u WHERE u.role.id = :role AND u.creator = :creator")
	Page<User> getUserByRoleCreator(@Param("role") int role, @Param("creator") int creator, Pageable pageable);
}
