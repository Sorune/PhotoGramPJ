package com.sorune.photogrampj.common.controller;

import com.sorune.photogrampj.notification.NotificationDTO;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class WebSocketController {

    @MessageMapping("/noti.send")
    @SendTo("/pg/noti")
    public NotificationDTO sendNotification(NotificationDTO notification){
        return  notification;
    }
}
