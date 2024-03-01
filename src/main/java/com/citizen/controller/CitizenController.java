package com.citizen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.citizen.bindings.Login;
import com.citizen.bindings.Password;
import com.citizen.bindings.Register;
import com.citizen.props.AppProps;
import com.citizen.service.CitizenService;

import jakarta.mail.MessagingException;

@RestController
@RequestMapping("/citizen")
public class CitizenController {
	
	@Autowired
	private CitizenService citizenService;
	
	@Autowired
	private AppProps appProps;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Register register) throws MessagingException{
		String citizen = citizenService.registerCitizen(register);
		return new ResponseEntity<String>(citizen,HttpStatus.CREATED);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Login login){
		String message = citizenService.login(login);
		return new ResponseEntity<String>(message,HttpStatus.OK);
	}
	
	/*
	 * @PostMapping("/forgot") public ResponseEntity<String> forgetPwd(@RequestBody
	 * String email) throws MessagingException{ boolean pwd =
	 * citizenService.forgotPassword(email); if(pwd) { return new
	 * ResponseEntity<String>(appProps.getMessages().get("PasswordSent"),HttpStatus.
	 * OK); }else { return new
	 * ResponseEntity<String>(appProps.getMessages().get("PasswordNotSent"),
	 * HttpStatus.INTERNAL_SERVER_ERROR); } }
	 */
	
	@PostMapping("/update/{id}")
	public ResponseEntity<String> updatePassword(@RequestBody Password password,@PathVariable Integer id){
		String message = citizenService.updatePassword(password,id);
		return new ResponseEntity<>(message,HttpStatus.OK);
	}
	
	

}
