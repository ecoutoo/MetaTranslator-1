package uk.uoa.cs.princSwEng.resource;

import java.util.*;
import javax.mail.*; 
import javax.activation.*;
import javax.mail.internet.*; 
import javax.mail.internet.MimeMessage; 
import javax.mail.Message.RecipientType;
import java.lang.Object; 

public class Mailer{
    private String host; 
    private int port; 
    private String username; 
    private String password; 

    public Mailer(final String host, final int port, final String username, String password) {
        this.host = host; 
        this.port = port; 
        this.username = username; 
        this.password = password; 
    }
    
    public final String getHost(){
        return host; 
    }
    
    public final int getPort(){
        return port;
    }
    
    public final String getUsername(){
        return username; 
    }
     
    public final String getPassword(){
        return password; 
    }
    
    public static void send(String to, String subject, String msg){
        final String user="MetaTranslate19@gmail.com";
        final String pass="EchoTeam19*";
        
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");
        // props.setProperty("mail.smtp.starttls.required", "true");
        props.setProperty("mail.smtp.starttls.enable", "true"); 
        // props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        // props.setProperty("mail.smtp.socketFactory.fallback", "false");        
        props.put("mail.smtp.socketFactory.port", "587");
        
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {  
            protected PasswordAuthentication getPasswordAuthentication() {  
            return new PasswordAuthentication(user,pass);  
        }  
        });  
        
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(user));
            message.addRecipient(RecipientType.TO,new InternetAddress(to));
            message.setSubject(subject);
            message.setText(msg);
            
            Transport.send(message);
            System.out.println("Done");
            
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}