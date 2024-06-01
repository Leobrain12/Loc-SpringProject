package com.Murc.Loc.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.Murc.Loc.Service.UserService;

import lombok.AllArgsConstructor;

import com.Murc.Loc.Model.*;
import com.Murc.Loc.Repository.DAO.UserDao;;
@Service
@AllArgsConstructor
public class UserServiceIMPL implements UserService {
    private final UserDao repository;

    @Override
    public List<User> findAllUser() {
        return repository.findAllUser();
    }

    @Override
    public User saveUser(User newUser) {
        return repository.saveUser(newUser);
    }

    @Override
    public User updateUser(User user,Long userId) {
        return repository.updateUser(user, userId);
    }

    @Override
    public void deleteUser(Long userId) {
        repository.deleteUser(userId);
    }

    @Override
    public User findById(Long userId) {
        return repository.findById(userId);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        /*
            TODO: Заменить этот временный код на полноценную реализацию
        */
        return null;
    }

    @Override
    public void addFavoriteVacancy(User user, Long vacancyId) {
        /*
            TODO: Заменить этот временный код на полноценную реализацию
        */
    }

    @Override
    public Set<Vacancy> getFavoriteVacancies(User user) {
        /*
            TODO: Заменить этот временный код на полноценную реализацию
        */
        return null;
    }

    @Override
    public void removeFavoriteVacancy(User user, Long vacancyId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'removeFavoriteVacancy'");
    }

    @Override
    public List<User> findAllUsersExcept(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAllUsersExcept'");
    }


}
