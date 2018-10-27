package dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.mail.*;
import javax.mail.internet.*;
import javax.activation.*;

public class SendEmail
{
	public String sendEmail(String template,String date,final String email,final String password){
		// Recipient's email ID needs to be mentioned.
      String to = "ansleyrodrigues25@gmail.com";

      // Sender's email ID needs to be mentioned
      String from = "ansleyrodrigues25@gmail.com";

      // Using Gmail to send email
      String host = "smtp.gmail.com";

      // Get system properties
      Properties properties = System.getProperties();

      // Setup mail server
      properties.setProperty("mail.smtp.host", host);
      properties.put("mail.smtp.auth", true);
      properties.put("mail.smtp.starttls.enable",true);
      properties.put("mail.smtp.EnableSSL.enable",true);

      // Get the default Session object.
      Session session = Session.getDefaultInstance(properties,
    		    new javax.mail.Authenticator() {
    		        protected PasswordAuthentication getPasswordAuthentication() {
    		            return new PasswordAuthentication(email,password);
    		        }
    		    }
    		);

      try{
         // Create a default MimeMessage object.
         MimeMessage message = new MimeMessage(session);

         // Set From: header field of the header.
         message.setFrom(new InternetAddress(from));

         // Set To: header field of the header.
         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
         
         
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		SimpleDateFormat formatter1 = new SimpleDateFormat("EEEE MMM dd, yyyy");
		String dateInString = date;
		String my_new_str = dateInString.replaceAll("-", "/");
		System.out.println(my_new_str);
		Date formattedDate=null;
		try {
		
			formattedDate = formatter.parse(my_new_str);
			System.out.println(formattedDate);
			System.out.println(formatter1.format(formattedDate));
		
		} catch (ParseException e) {
			e.printStackTrace();
		}
         
         
         // Set Subject: header field
         message.setSubject("Word list for "+formatter1.format(formattedDate));

         // Now set the actual message
        // message.setText("This is actual message");
         
         // Send the actual HTML message, as big as you like
         System.out.println(template);
         message.setContent(template, "text/html" );


         // Send message
         Transport.send(message);
         System.out.println("Sent message successfully....");
         return "Sent message successfully....";
      }catch (MessagingException mex) {
         mex.printStackTrace();
         return "Failed to Send Email...";
      }	
      
	} 
}
