package ru.nsu.fit.chat.event;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import ru.nsu.fit.chat.service.OnlineUsersInfoService;

@Log4j2
@Component
public class StompDisconnectEvent implements ApplicationListener<SessionDisconnectEvent> {
    private OnlineUsersInfoService onlineUsersService;

    @Autowired
    public void setOnlineUsersService(OnlineUsersInfoService onlineUsersService) {
        this.onlineUsersService = onlineUsersService;
    }


    @Override
    public void onApplicationEvent(SessionDisconnectEvent event) {
        StompHeaderAccessor sha = StompHeaderAccessor.wrap(event.getMessage());
        var user = sha.getUser();

        assert user != null;
        String username = user.getName();
        onlineUsersService.userOffline(username);
        onlineUsersService.notifyAllUsers();

        log.info(username + " disconnect");
    }
}
