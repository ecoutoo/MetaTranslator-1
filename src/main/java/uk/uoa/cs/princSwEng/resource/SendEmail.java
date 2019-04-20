package uk.uoa.cs.princSwEng.resource;

import java.util.*;
import javax.mail.*; 
import javax.activation.*;
import javax.mail.internet.*; 
import java.lang.Object;
import uk.uoa.cs.princSwEng.resource.Mailer;


public class SendEmail{

    public static void main(String to, String subject, String message) {
        String hostname = "smtp.gmail.com";
        int port = 587;
        String username = "MetaTranslate19@gmail.com";
        String password = "EchoTeam19*";
        Mailer mailer = new Mailer(hostname, port, username, password); 
        
        to = to; 
        subject = subject; 
        message = message; 
        
        mailer.send(to, subject, message);
    }
}