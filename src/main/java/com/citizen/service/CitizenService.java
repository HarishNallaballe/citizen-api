package com.citizen.service;

import com.citizen.bindings.Login;
import com.citizen.bindings.Password;
import com.citizen.bindings.Register;

import jakarta.mail.MessagingException;

public interface CitizenService {

	public String registerCitizen(Register register) throws MessagingException;
	
	public String login(Login login);
	
	//public boolean forgotPassword(String email) throws MessagingException;
	
	public String updatePassword(Password passowrd,Integer id);
	
}
