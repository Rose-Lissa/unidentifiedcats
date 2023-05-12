package ru.nsu.fit.chat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.nsu.fit.chat.domain.Role;
import ru.nsu.fit.chat.domain.User;
import ru.nsu.fit.chat.dto.UserRegistrationDto;
import ru.nsu.fit.chat.exception.DifferentPasswordsException;
import ru.nsu.fit.chat.exception.UserExistException;
import ru.nsu.fit.chat.repository.RoleRepository;
import ru.nsu.fit.chat.repository.UserRepository;

import java.util.Collections;

@Service
public class RegistrationUserService {
    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder encoder;

    @Autowired
    public void setRoleRepository(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setEncoder(PasswordEncoder encoder) {
        this.encoder = encoder;
    }


    public void register(UserRegistrationDto dto) throws UserExistException, DifferentPasswordsException {
        User user = userRepository.findByUsername(dto.getUsername());
        if(user != null){
            throw new UserExistException();
        }
        if(!dto.getPassword().equals(dto.getRepeatPassword())) {
            throw new DifferentPasswordsException();
        }
        user = createUser(dto.getUsername(), dto.getPassword());
        userRepository.save(user);
    }

    private User createUser(String username, String password){
        User user = new User();
        user.setUsername(username);
        user.setPassword(encoder.encode(password));
        user.setActive(true);
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRoles(Collections.singleton(userRole));
        return user;
    }
}
