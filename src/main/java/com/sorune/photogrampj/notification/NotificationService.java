package com.sorune.photogrampj.notification;

import com.sorune.photogrampj.common.service.GenericService;
import com.sorune.photogrampj.repository.jpa.NotificationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.messaging.simp.SimpMessagingTemplate;

public class NotificationService extends GenericService<Notification,NotificationDTO> {

    private final SimpMessagingTemplate messagingTemplate;

    public NotificationService(NotificationRepository repository, ModelMapper modelMapper, SimpMessagingTemplate messagingTemplate) {
        super(repository, modelMapper, Notification.class, NotificationDTO.class);
        this.messagingTemplate = messagingTemplate;
    }

    public void sendNotification(String recipient, String message){
        Notification notification = Notification.builder()
                .isRead(false)
                .build();

        messagingTemplate.convertAndSendToUser(recipient,"/pg/noti",message);
    }

    public NotificationDTO checkRead(NotificationDTO notification) {
        notification.setRead(true);
        return notification;
    }
}
