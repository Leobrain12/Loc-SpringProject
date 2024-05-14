package com.Murc.Loc.Service;

import java.util.List;

import com.Murc.Loc.Model.User;

public interface UserService {
    List<User> findAllUser();
    User saveUser(User newUser);
    User updateUser(User user, Long userId);
    void deleteUser(Long userId);
    public User findById(Long userId);
}
