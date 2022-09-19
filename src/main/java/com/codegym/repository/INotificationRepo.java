package com.codegym.repository;

import com.codegym.model.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface INotificationRepo extends JpaRepository<Notification, Long> {

}
