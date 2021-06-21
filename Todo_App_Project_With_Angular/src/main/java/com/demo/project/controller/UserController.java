package com.demo.project.controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.project.entity.User;
import com.demo.project.service.UserService;

@RestController
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/{userId}")
	public User getUserDetails(@PathVariable Long userId) {
		User userResponse = userService.findByUserId(userId);

		return userResponse;
	}

	@SuppressWarnings("unused")
	private String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}

//	@PostMapping("/saveuser")
//	public User saveUser(@RequestBody User user, HttpServletRequest request) throws UnsupportedEncodingException, MessagingException {
//		User userResponse = userService.saveUser(user);
//		userService.sendVerificationEmail(user, getSiteURL(request));
//		return userResponse;
//	}

	@GetMapping("/verify")
	public String verifyUser(@Param("code") String code) {
		System.out.print("In verify method");
	    if (userService.verify(code)) {
	    	
	        return "verify_success";
	    } else {
	        return "verify_fail";
	    }
	}

	@DeleteMapping("/deleteUser/{userId}")
	public HttpStatus delete(@PathVariable("userId") Long userId) {
		userService.delete(userId);
		return HttpStatus.FORBIDDEN;
	}

	@GetMapping("/users")
	public List<User> getUsers() {
		return userService.getAllUsers();
	}

//	user registration post method
	@PostMapping("/registeruser")
	public ResponseEntity<User> RegisterUser(@RequestBody User user,HttpServletRequest req) {
		String tempEmailId = user.getEmail();
		if (tempEmailId != null && !"".equals(tempEmailId)) {
			User userObj = userService.fetchByUserEmailId(tempEmailId);
			if (userObj != null) {
				return new ResponseEntity<User>(userObj,HttpStatus.FORBIDDEN);
			}
		}
		User userObj = null;
		userObj = userService.saveUser(user);
		return new ResponseEntity<User>(userObj, HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<User> loginUser(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		String tempPassword = user.getPassword();
		User userObj = null;
		if (tempEmail != null && tempPassword != null) {
			userObj = userService.fetchByEmailAndPassword(tempEmail, tempPassword);
		}
		if (userObj == null) {
//			throw new Exception("Bad credentials");
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(userObj, HttpStatus.OK);
	}

}