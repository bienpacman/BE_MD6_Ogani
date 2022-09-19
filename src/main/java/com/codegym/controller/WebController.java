package com.codegym.controller;

import com.codegym.model.Notification;
import com.codegym.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
public class WebController {
    @Autowired
    NotificationService notificationService;

    @MessageMapping("/notifications")
    @SendTo("/topic/notification")
    public Notification createNewNot(Notification notification){
        return notificationService.save(notification);
    }

//    @MessageMapping("/hello")
//    @SendTo("/topic/hi")
//    public Hello greeting(User user) throws Exception {
//        return new Hello("Hi, " + user.getName() + "!");
//    }
}
