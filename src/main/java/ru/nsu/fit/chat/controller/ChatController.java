package ru.nsu.fit.chat.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.nsu.fit.chat.dto.UserInfoDto;

@Controller
@RequestMapping("/")
public class ChatController {
//    @Value("${spring.profiles.active}")
//    private String profile;

    @GetMapping("/chat")
    public String chat(@AuthenticationPrincipal User user, Model model) {
        UserInfoDto userInfo = new UserInfoDto();
        userInfo.setUsername(user.getUsername());
        model.addAttribute("userInfo", userInfo);
        model.addAttribute("isDevMode", /*"dev".equals(profile)*/true);
        return "chat";
    }
}
