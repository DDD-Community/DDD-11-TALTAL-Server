package org.tatltal.proejct.controller;

import com.google.firebase.messaging.FirebaseMessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tatltal.proejct.service.MessageService;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    public static final String token = "cxkzdAfKSX6vGkDIT6-R63:APA91bHlRDziyjxcgDrV4HaauCaVTEHAqbmfiMBCFg1V6-CszJORKB5DQTLYOlm3yFPM9be86Qpczd68DqKb2VgRok6H0W-hrBjLcHx2l3-B8fwgC2d5y5TOyW5JbYnZAZ2tRzNA8Rxa";



    @GetMapping("/send")
    public void sendMessage(@RequestParam String token) throws FirebaseMessagingException {

        messageService.sendMessage(token, "hi", "body");
    }
}
