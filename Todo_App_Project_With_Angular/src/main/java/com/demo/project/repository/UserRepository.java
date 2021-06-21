package com.demo.project.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.project.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Serializable> {

	public User findByUserId(Long userId);

	@Query("SELECT u FROM User u WHERE u.email = ?1")
	public User findByUserEmailId(String email);
	

////
////	@Query("SELECT * FROM user where email=?1 and password=?1")
	public User findByEmailAndPassword(String email, String password);

	@Query("SELECT u FROM User u WHERE u.verificationCode = ?1")
	public User findByVerificationCode(String code);
}
