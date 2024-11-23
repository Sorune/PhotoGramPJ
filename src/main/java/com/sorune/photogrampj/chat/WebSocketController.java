package com.sorune.photogrampj.chat;

import com.sorune.photogrampj.chat.message.RedisMessage;
import com.sorune.photogrampj.chat.message.RedisPrivateMessage;
import com.sorune.photogrampj.common.util.kafka.KafkaProducer;
import com.sorune.photogrampj.notification.NotificationDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
public class WebSocketController {
    private final KafkaProducer kafkaProducer;

    @MessageMapping("/msg/send")
    public void sendMessage(@Payload RedisMessage message){
        kafkaProducer.sendMessage("chat-messages",message.toString());
    }

    @MessageMapping("/noti/send")
    public void sendNotification(@Payload NotificationDTO notificationDTO){
        kafkaProducer.sendMessage("notifications",notificationDTO.toString());
    }

    @MessageMapping("/pv/send")
    public void sendPrivateMessage(@Payload RedisPrivateMessage redisPrivateMessage){
        kafkaProducer.sendMessage("private-messages",redisPrivateMessage.toString());
    }

}
