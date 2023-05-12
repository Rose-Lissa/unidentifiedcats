package ru.nsu.fit.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.fit.chat.service.OnlineUsersInfoService;
import ru.nsu.fit.chat.dto.OnlineUserInfoDto;

import java.util.List;

@RestController
@RequestMapping("online")
public class OnlineUsersInfoController {
    private OnlineUsersInfoService onlineUsersInfoService;

    @Autowired
    public void setOnlineUsersInfoService(OnlineUsersInfoService onlineUsersInfoService) {
        this.onlineUsersInfoService = onlineUsersInfoService;
    }

    @GetMapping
    public List<OnlineUserInfoDto> online(){
       return onlineUsersInfoService.getUserOnlineList();
    }


//    @SubscribeMapping("/online")
//    @SendTo("/topic/online")
//    public List<OnlineUserInfoDto> online(){
//        return onlineUsersInfoService.getUserOnlineList();
//    }
}
