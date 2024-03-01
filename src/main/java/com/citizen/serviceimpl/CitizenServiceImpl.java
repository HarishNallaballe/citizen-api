package com.citizen.serviceimpl;

import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.citizen.bindings.Login;
import com.citizen.bindings.Password;
import com.citizen.bindings.Register;
import com.citizen.entity.Citizen;
import com.citizen.props.AppProps;
import com.citizen.repo.CitizenRepository;
import com.citizen.service.CitizenService;
/*import com.citizen.utils.EmailUtils;*/

import jakarta.mail.MessagingException;

@Service
public class CitizenServiceImpl implements CitizenService{
	
	@Autowired
	private CitizenRepository citizenRepository;
	
	@Autowired
	private AppProps appProps;
	
	/*
	 * @Autowired private EmailUtils emailUtils;
	 */

	@Override
	public String registerCitizen(Register register) throws MessagingException {
		
		Citizen citizen = new Citizen();
		BeanUtils.copyProperties(register, citizen);
		citizen.setPassword(generateRandomPwd());
		citizen.setPwdUpdated("No");
		Citizen save = citizenRepository.save(citizen);
		if(save.getId()!=null) {
//			Citizen findByEmail = citizenRepository.findByEmail(save.getEmail());
//			if(findByEmail!=null) {
//				String subject="Welcome to HIS Application";
//				String body="Thankyou for Registering with HIS Application.Your Password is "+findByEmail.getPassword();
//			    emailUtils.sendEmail(subject, body, findByEmail.getEmail());
//			   // return "Email Send Successfully";
//			}
			return appProps.getMessages().get("CitizenSaved");
		}
		return appProps.getMessages().get("CitizenNotSaved");
		
		
	}
	
	private static String generateRandomPwd() {
	    String alphanumericCharacters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789abcdefghijklmnopqrstuv";
	    int length=7;
	    StringBuffer randomString = new StringBuffer(length);
	    Random random = new Random();
	 
	    for (int i = 0; i < length; i++) {
	        int randomIndex = random.nextInt(alphanumericCharacters.length());
	        char randomChar = alphanumericCharacters.charAt(randomIndex);
	        randomString.append(randomChar);
	    }
	 
	    return randomString.toString();
	}

	@Override
	public String login(Login login) {
		Citizen citizen = citizenRepository.findByEmailAndPassword(login.getEmail(), login.getPassword());
		if(citizen!=null) {
			return appProps.getMessages().get("LoginSuccess");
		}
		return appProps.getMessages().get("LoginFailed");
	}
	

	/*
	 * @Override public boolean forgotPassword(String email) throws
	 * MessagingException { Citizen citizen = citizenRepository.findByEmail(email);
	 * if(citizen!=null) { String body="Your Password id : "+citizen.getPassword();
	 * String subject=appProps.getMessages().get("RecoverPassword"); return
	 * emailUtils.sendEmail(subject, body, citizen.getEmail()); } return false; }
	 */

	@Override
	public String updatePassword(Password passowrd,Integer id) {
		Citizen citizen = citizenRepository.findById(id).get();
		
		if(citizen.getPwdUpdated().equalsIgnoreCase("No") && passowrd.getNewPassword().equals(passowrd.getConfirmPassword()) && 
				passowrd.getOldPassword().equals(citizen.getPassword())){
			citizen.setPassword(passowrd.getConfirmPassword());
			citizen.setPwdUpdated("Yes");
			citizenRepository.save(citizen);
			return appProps.getMessages().get("PasswordUpdated");
		}
		return appProps.getMessages().get("PasswordNotUpdated");
	}

}
