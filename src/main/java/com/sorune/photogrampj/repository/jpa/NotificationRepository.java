package com.sorune.photogrampj.repository.jpa;

import com.sorune.photogrampj.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification,Long> {
}
