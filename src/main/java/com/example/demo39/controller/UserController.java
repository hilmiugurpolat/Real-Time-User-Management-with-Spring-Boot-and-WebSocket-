package com.example.demo39.controller;

import com.example.demo39.model.User;
import com.example.demo39.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/user")
    public  User getUser(@RequestParam Integer id)
    {
        Optional<User> user = userService.getUser(id);
        return user.orElse(null);
    }

    @PostMapping("/user")
    public User createUser(@RequestBody User user)
    {
        System.out.println(user.toString());
        return userService.saveUser(user);
    }



    @DeleteMapping("user")
    public void delete(@RequestParam Integer id)
    {
        userService.deleteUser(id);
    }

    @RequestMapping("/user/{id}")
    public User UpdateUser(@PathVariable Integer id , @RequestBody User user)
    {
        System.out.println(user.toString());
        Optional<User> existingUser= userService.getUser(id);
        if(existingUser.isPresent())
        {
            User updatedUser=existingUser.get();
            updatedUser.setName(user.getName());
            updatedUser.setSurname(user.getSurname());
            updatedUser.setUsername(user.getUsername());
            return userService.saveUser(updatedUser);
        }

        else {
            System.out.println("user no found "+ id);
        }

        return user;
    }

    private void broadcastUpdatedUsers() {
        List<User> users = userService.getAllUsers();
        UserControllerFromDatabase.broadcastUsers(users);
    }
}
