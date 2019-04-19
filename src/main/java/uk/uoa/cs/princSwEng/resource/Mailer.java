package uk.uoa.cs.princSwEng.resource;

import java.util.*;
import javax.mail.*; 
import javax.activation.*;
import javax.mail.internet.*; 
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
}