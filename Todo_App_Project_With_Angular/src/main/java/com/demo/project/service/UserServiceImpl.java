package com.demo.project.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.demo.project.entity.User;
import com.demo.project.repository.UserRepository;

import net.bytebuddy.utility.RandomString;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	// change
	@Autowired
	private JavaMailSender mailSender;

	@Override
	// here user registration occur
	public User saveUser(User user) {
		String randomCode = RandomString.make(64);
		user.setVerificationCode(randomCode);
		user = userRepository.save(user);
		return user;
	}

	@Override
	public User findByUserId(Long userId) {
		User user = userRepository.findByUserId(userId);
		return user;
	}

	public void delete(Long userId) {
		Optional<User> user = userRepository.findById(userId);
		if (user.isPresent()) {
			userRepository.deleteById(userId);
		} else {
			System.out.println("user is not present in the table with id :" + userId);
		}
	}

	public List<User> getAllUsers() {
		return (List<User>) userRepository.findAll();
	}
//
	@Override
	public User fetchByUserEmailId(String email) {
		// TODO Auto-generated method stub
		System.out.println(email);
		User user = userRepository.findByUserEmailId(email);
		System.out.println(user);
		return user;
	}

	@Override
	public User fetchByEmailAndPassword(String email, String password) {
		// TODO Auto-generated method stub
		User user = userRepository.findByEmailAndPassword(email, password);
		System.out.println("email and password :*************************"+user);
		return user;
	}
//	
//	//CHANGE
//	public void register(User user) {
////		 String randomCode = RandomString.make(64);
////		    user.setVerificationCode(randomCode);
//		    user.setEnabled(false);
//		    userRepository.save(user);
//		    sendVerificationEmail(user,siteURL);
//	}

	@SuppressWarnings("unused")
	public void sendVerificationEmail(User user, String siteURL)
			throws UnsupportedEncodingException, MessagingException, MailException {
		String toAddress = user.getEmail();
		String fromAddress = "aparnalende@gmail.com";
		String senderName = "@Visioners";
		String subject = "Please verify your registration";
		String verifyURL = siteURL + "/verify?code=" + user.getVerificationCode();
		System.out.println(verifyURL+"==================");
		String mailContent = "Dear " + user.getfirst_name() + "<br>"
				+ "Please click the link below to verify your registration:<br>" + "<h3><a href=" + "'verifyURL'"
				+ ">VERIFY</a></h3>" + "Thank you,<br>" + "@Visioners";

		mailContent = mailContent.replace(user.getfirst_name(), user.getlast_name());

		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setFrom(fromAddress, senderName);
		helper.setTo(toAddress);
		helper.setSubject(subject);

		helper.setText(mailContent, true);
		mailSender.send(message);

	}

	@Override
	public boolean verify(String verificationCode) {
		System.out.print(" i am in verify method with verifivation code="+verificationCode);
		User user = userRepository.findByVerificationCode(verificationCode);
		if (user == null || user.isEnabled()) {
			return false;
		} else {
			user.setVerificationCode(null);
			user.setEnabled(true);
			userRepository.save(user);
			return true;
		}
	}



}
