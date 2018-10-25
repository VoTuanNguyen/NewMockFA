package com.fpt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fpt.entity.User;

@Repository
public interface AdminRepository extends JpaRepository<User, Integer>{

}
