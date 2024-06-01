package com.Murc.Loc.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Model.Vacancy;

public interface UserService {
    List<User> findAllUser();
    User saveUser(User newUser);
    User updateUser(User user, Long userId);
    void deleteUser(Long userId);
    User findById(Long userId);
    Optional<User> findByEmail(String email);
    void addFavoriteVacancy(User user, Long vacancyId);
    Set<Vacancy> getFavoriteVacancies(User user);
    void removeFavoriteVacancy(User user, Long vacancyId);
}
