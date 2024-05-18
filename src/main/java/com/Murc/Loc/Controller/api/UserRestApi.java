package com.Murc.Loc.Controller.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.Murc.Loc.Service.UserService;
import com.Murc.Loc.Model.*;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserRestApi {
    private final UserService service;
    @GetMapping
    public List<User> findAllUser(){
        return service.findAllUser();
    }
    @PostMapping("save_user")
    public User saveUser(@RequestBody User newUser){
        return service.saveUser(newUser);
    }
    @GetMapping("/{userId}")
    public User findById(@PathVariable Long userId){
        return service.findById(userId);
    }
    @PutMapping("update_user/{userId}")
    public User updateUser(@RequestBody User user, @PathVariable Long userId){
        return service.updateUser(user, userId);
    }
    @DeleteMapping("delete_user/{userId}")
    public void deleteUser(@PathVariable Long userId){
        service.deleteUser(userId);
    }
}
