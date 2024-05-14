package com.Murc.Loc.Service.impl;

import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Repository.UserRepository;
import com.Murc.Loc.Service.UserService;

import lombok.AllArgsConstructor;
@Service
@AllArgsConstructor
@Primary
public class InMemUserServiceIMPL implements UserService  {

    private UserRepository repository;


    public List<User> findAllUser() {
        return repository.findAll();
    }

    
    public User saveUser(User newUser) {
        return repository.save(newUser);
    }

    
    public User updateUser(User user, Long userId) {
        return repository.save(user);
    }

    
    public void deleteUser(Long userId) {
        repository.deleteById(userId);
    }


    @Override
    public User findById(Long userId) {
        return repository.findByUserId(userId);
    }

    
    
}
