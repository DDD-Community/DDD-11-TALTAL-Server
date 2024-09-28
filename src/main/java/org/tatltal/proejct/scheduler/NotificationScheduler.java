package org.tatltal.proejct.scheduler;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.tatltal.proejct.entity.UserEntity;
import org.tatltal.proejct.service.MessageService;
import org.tatltal.proejct.service.UserService;

import java.util.List;


@Component
@RequiredArgsConstructor
public class NotificationScheduler {

    private final MessageService messageService;
    private final UserService userService;

    @Scheduled(cron = "0 0 10 * * ?")
    public void sendMorningNotification() throws FirebaseMessagingException {
        List<UserEntity> allNotificationAgreedUser = userService.getAllNotificationAgreedUser();

        for (UserEntity userEntity : allNotificationAgreedUser) {
            messageService.sendMessage(userEntity.getToken(), "모닝 커피 하셨나요?", "커피 마셨다면 기록하러 고고~");
        }

    }

    @Scheduled(cron = "0 0 14 * * ?")
    public void sendDayNotification() throws FirebaseMessagingException {
        List<UserEntity> allNotificationAgreedUser = userService.getAllNotificationAgreedUser();

        for (UserEntity userEntity : allNotificationAgreedUser) {
            messageService.sendMessage(userEntity.getToken(), "배고프고 나른한 오후 시간", "커피 드셨다면 기록해봐요!");
        }
    }

    @Scheduled(cron = "0 0 20 * * ?")
    public void sendEveningNotification() throws FirebaseMessagingException {
        List<UserEntity> allNotificationAgreedUser = userService.getAllNotificationAgreedUser();

        for (UserEntity userEntity : allNotificationAgreedUser) {
            messageService.sendMessage(userEntity.getToken(), "오늘 하루도 고생하셨어요.", "하루동안 마신 커피양은?");
        }

    }
}
