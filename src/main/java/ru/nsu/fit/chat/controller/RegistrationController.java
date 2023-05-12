package ru.nsu.fit.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.nsu.fit.chat.service.RegistrationUserService;
import ru.nsu.fit.chat.dto.UserRegistrationDto;
import ru.nsu.fit.chat.exception.DifferentPasswordsException;
import ru.nsu.fit.chat.exception.UserExistException;
import ru.nsu.fit.chat.template.RegistrationInfo;

@Controller
public class RegistrationController {
    RegistrationUserService registrationUserService;

    @Autowired
    public void setRegistrationUserService(RegistrationUserService registrationUserService) {
        this.registrationUserService = registrationUserService;
    }

    @GetMapping("/registration")
    public String registration(Model model){
        model.addAttribute("registrationDto", new UserRegistrationDto());
        model.addAttribute("info", new RegistrationInfo(false, ""));
        return "reg";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute UserRegistrationDto registrationDto, Model model){
        try {
            registrationUserService.register(registrationDto);
        } catch (UserExistException e) {
            model.addAttribute("registrationDto", new UserRegistrationDto());
            model.addAttribute("info", new RegistrationInfo(true, "Username already have been used"));
            return "reg";
        } catch (DifferentPasswordsException e) {
            model.addAttribute("registrationDto", new UserRegistrationDto());
            model.addAttribute("info", new RegistrationInfo(true, "You write different passwords"));
            return "reg";
        }

        return "redirect:/login";
    }

}
