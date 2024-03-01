/*
 * package com.citizen.utils;
 * 
 * import org.springframework.beans.factory.annotation.Autowired; import
 * org.springframework.context.annotation.Configuration; import
 * org.springframework.mail.javamail.JavaMailSender; import
 * org.springframework.mail.javamail.MimeMessageHelper;
 * 
 * import jakarta.mail.MessagingException; import
 * jakarta.mail.internet.MimeMessage;
 * 
 * @Configuration public class EmailUtils {
 * 
 * @Autowired public JavaMailSender sender;
 * 
 * public boolean sendEmail(String subject, String body,String to) throws
 * MessagingException { boolean flag=false; try { MimeMessage message =
 * sender.createMimeMessage(); MimeMessageHelper helper = new
 * MimeMessageHelper(message); helper.setText(body); helper.setSubject(subject);
 * helper.setTo(to); sender.send(message); flag=true; }catch (Exception e) {
 * e.printStackTrace(); } return flag; }
 * 
 * }
 */