/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.service.MessageService;
import org.springframework.stereotype.Service;

/**
 *
 * @author SUDIP
 */
@Service
public class MessageServiceImp implements MessageService{
String message="Hello World";
    @Override
    public String getGreetingMessage() {
        return "Hello World";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
}
