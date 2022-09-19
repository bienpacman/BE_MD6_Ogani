package com.codegym.service;

import com.codegym.model.Notification;
import com.codegym.repository.INotificationRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {

    @Autowired
    INotificationRepo iNotificationRepo;

    public Notification save(Notification notification){
       return iNotificationRepo.save(notification);
    }
}
