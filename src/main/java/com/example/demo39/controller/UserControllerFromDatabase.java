package com.example.demo39.controller;
import com.example.demo39.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import com.example.demo39.service.UserService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import java.util.List;
@EnableScheduling
@Controller

public class UserControllerFromDatabase {

    private final UserService userService;
    private static SimpMessagingTemplate template = null;

    @Autowired
    public UserControllerFromDatabase(UserService userService, SimpMessagingTemplate template) {
        this.userService = userService;
        this.template = template;
    }

    @Scheduled(fixedRate = 2000)
    public void updateUsers() {
        List<User> users = userService.getAllUsers();
        broadcastUsers(users);
    }

    static void broadcastUsers(List<User> users) {
        template.convertAndSend("/topic/users", users);
    }
}