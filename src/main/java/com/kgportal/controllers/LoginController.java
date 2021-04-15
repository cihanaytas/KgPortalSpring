package com.kgportal.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kgportal.business.dto.LoginBody;
import com.kgportal.business.dto.UserKGDto;
import com.kgportal.business.service.UserService;
import com.kgportal.data.entity.UserKG;
import com.kgportal.data.repository.UserRepository;
import com.kgportal.security.JwtResponse;
import com.kgportal.security.JwtUtils;

@RestController
public class LoginController {

	 	@Autowired
	    private UserService userService;
	    
	    @Autowired
	    private BCryptPasswordEncoder bCryptPasswordEncoder;



	    @PostMapping("/signup")
	    public void signUp(@RequestBody UserKGDto userDto) {
	       // user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	    	userService.addUser(userDto);
	    }
	    
	    
		@PostMapping("/signin")
		public ResponseEntity<?> authenticateUser(@RequestBody LoginBody user) {
			return userService.loginUser(user);
		}
	    
	    
	    
	    @GetMapping("test")
	    public String aa() {
	    	return "user test giris";
	    }
	    
	    @GetMapping("home")
	    public String all() {
	    	return "home testi";
	    }
}
