package ru.nsu.fit.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.nsu.fit.chat.domain.Message;
import ru.nsu.fit.chat.domain.User;
import ru.nsu.fit.chat.exception.UserExistException;
import ru.nsu.fit.chat.repository.MessageRepository;
import ru.nsu.fit.chat.repository.UserRepository;

import java.util.Date;
import java.util.List;


@Service
public class MessageService {
    private MessageRepository messageRepository;
    private UserRepository userRepository;

    @Autowired
    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    private User findByUserName(String username){
        return  userRepository.findByUsername(username);
    }

    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    public Message saveMessage(String username,
                               String messageText) throws UserExistException {
        User user = findByUserName(username);
        if(user == null){
            throw new UserExistException();
        }

        Message message = new Message();
        message.setText(messageText);
        message.setTime(new Date().getTime());
        message.setUsername(username);
        messageRepository.save(message);
        return message;
    }
}
