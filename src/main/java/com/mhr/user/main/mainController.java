package com.mhr.user.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import com.mhr.user.jwt.JwtAuth;
import com.mhr.user.jwt.JwtUtils;

@Controller
public class mainController {

	@Autowired
	private AuthenticationManager manager;
	
	@Autowired 
	private JwtUtils jwtUtils;
	
	@RequestMapping("/login")
	public String login() {
		return "login";
	}
	
	@GetMapping("/jwt/hello")
	public @ResponseBody String jwtHello() {
		return "hello jwt";
	}
	
	@PostMapping("/jwt/login")
	public @ResponseBody ResponseEntity<?> jwtLogin(@RequestBody JwtAuth jwtAuth,HttpServletResponse response){
		
		try{
			manager.authenticate(new UsernamePasswordAuthenticationToken(jwtAuth.getUsername(), jwtAuth.getPassword()));
		}catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		response.addHeader("Authrizion", jwtUtils.generateToken(jwtAuth.getUsername()));
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
}
