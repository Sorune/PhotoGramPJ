package com.sorune.photogrampj.notification;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JPANotificationRepository extends JpaRepository<Notification,Long> {
}
