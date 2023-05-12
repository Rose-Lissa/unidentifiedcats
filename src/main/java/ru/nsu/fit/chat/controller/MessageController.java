package ru.nsu.fit.chat.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import ru.nsu.fit.chat.service.MessageService;
import ru.nsu.fit.chat.domain.Message;
import ru.nsu.fit.chat.dto.MessageDto;
import ru.nsu.fit.chat.exception.UserExistException;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("message")
public class MessageController {
    private MessageService messageService;

    @Autowired
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<Message> allMessages(){
        return messageService.getAllMessages();
    }

    @PostMapping("")
    public Message sendMessage(@RequestBody Map.Entry<String, String> text, @AuthenticationPrincipal User user) throws UserExistException {
        return messageService.saveMessage(user.getUsername(), text.getValue());
    }

    @MessageMapping("/send")
    @SendTo("/topic/activity")
    public List<Message> send(MessageDto message) throws UserExistException {
        return List.of(messageService.saveMessage(message.getUsername(), message.getText()));
    }
}
