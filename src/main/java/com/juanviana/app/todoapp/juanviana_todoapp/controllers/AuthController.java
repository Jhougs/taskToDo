package com.juanviana.app.todoapp.juanviana_todoapp.controllers;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.juanviana.app.todoapp.juanviana_todoapp.model.Message;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/auth")
public class AuthController {


    @GetMapping("list") // read and write
    public List<Message> list(){
        return Collections.singletonList(new Message("Test List"));
    }

    @PostMapping("create") // create para admins
    public Message createUser(@RequestBody Message message) {
        System.out.println("Mensaje guardado" + message);
        return message;
    }

    
    
    @GetMapping("authorized")
    public Map<String, String> authorized(@RequestParam String code){
        return Collections.singletonMap("code", code);
    }
    

}
