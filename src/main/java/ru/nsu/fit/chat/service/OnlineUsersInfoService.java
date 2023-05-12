package ru.nsu.fit.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Service;
import ru.nsu.fit.chat.domain.OnlineUserInfo;
import ru.nsu.fit.chat.dto.OnlineUserInfoDto;
import ru.nsu.fit.chat.repository.OnlineUserInfoRepository;

import java.util.List;

@Service
public class OnlineUsersInfoService {
    private final OnlineUserInfoRepository userInfoRepository;
    private final SimpMessageSendingOperations sendingOperations;

    @Autowired
    public OnlineUsersInfoService(OnlineUserInfoRepository userInfoRepository, SimpMessageSendingOperations sendingOperations) {
        this.userInfoRepository = userInfoRepository;
        this.sendingOperations = sendingOperations;
        userInfoRepository.deleteAll();
    }

    public void userOnline(String username){
        OnlineUserInfo onUserInfo = userInfoRepository.findByUsername(username);
        if(onUserInfo == null){
            onUserInfo = new OnlineUserInfo();
            onUserInfo.setUsername(username);
            userInfoRepository.save(onUserInfo);
        }
    }

    public void userOffline(String username){
        OnlineUserInfo onUserInfo = userInfoRepository.findByUsername(username);
        if(onUserInfo != null){
            userInfoRepository.delete(onUserInfo);
        }
    }

    public OnlineUserInfoDto mapToDto(OnlineUserInfo userInfo){
        var dto = new OnlineUserInfoDto();
        dto.setUsername(userInfo.getUsername());
        return dto;
    }

    public List<OnlineUserInfoDto> getUserOnlineList() {
        List<OnlineUserInfo> onlineUsersInfo = userInfoRepository.findAll();
        return onlineUsersInfo
                .stream()
                .map(this::mapToDto)
                .toList();
    }

    public void notifyAllUsers() {
        sendingOperations.convertAndSend("/topic/online", getUserOnlineList());
    }
}
