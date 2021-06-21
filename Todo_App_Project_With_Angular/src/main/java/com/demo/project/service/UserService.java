package com.demo.project.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import org.springframework.stereotype.Component;

import com.demo.project.entity.User;

@Component
public interface UserService {
	public User saveUser(User user);

	public User findByUserId(Long userId);

	public void delete(Long userId);

	public List<User> getAllUsers();

	
	public User fetchByUserEmailId(String email);
////
	public User fetchByEmailAndPassword(String email, String password);

	public void sendVerificationEmail(User user,String abc) throws UnsupportedEncodingException, MessagingException;
	
	public boolean verify(String verify);
}
