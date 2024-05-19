package com.Murc.Loc.Repository.DAO;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.Murc.Loc.Model.*;

@Repository
public class UserDao {
    private final List<User> USERS = new ArrayList<>();

    
    public List<User> findAllUser() {
        return USERS;
    }

    
    public User saveUser(User newUser) {
        USERS.add(newUser);
        return newUser;
    }
    
    public User findById(Long userId){
        return USERS.stream()
            .filter(element -> element.getUserId().equals(userId))
            .findFirst()
            .orElse(null);
    }
    public User updateUser(User user,Long userId) {
        var userIndex = -1;
        for(User CurretUser: USERS)
        {
            if(CurretUser.getUserId().equals(userId))
            {
                userIndex = USERS.indexOf(CurretUser);
                USERS.set(userIndex,user);
            }
        }
        return(userIndex == -1) ? saveUser(user) : user;
    }

    
    public void deleteUser(Long userId) {
        var user = findById(userId);
        if(user != null)
        {
            USERS.remove(user);
        }
    }
    public User findByEmail(String email){
        return USERS.stream()
            .filter(element -> element.getEmail().equals(email))
            .findFirst()
            .orElse(null);
    }
}
