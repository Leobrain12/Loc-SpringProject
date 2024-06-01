package com.Murc.Loc.Service.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.context.annotation.Primary;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Murc.Loc.Model.User;
import com.Murc.Loc.Model.Vacancy;
import com.Murc.Loc.Repository.UserRepository;
import com.Murc.Loc.Repository.VacancyRepository;
import com.Murc.Loc.Service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Primary
public class InMemUserServiceIMPL implements UserService {

    private final UserRepository repository;
    private final VacancyRepository vacancyRepository;
    private final PasswordEncoder passwordEncoder;

    
    @Override
    public List<User> findAllUser() {
        return repository.findAll();
    }

    @Override
    public User saveUser(User newUser) {
        return repository.save(newUser);
    }

    @Override
    public User updateUser(User user, Long userId) {
        User existingUser = repository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
    
        existingUser.setFirstName(user.getFirstName());
        existingUser.setLastName(user.getLastName());
        existingUser.setTelephone(user.getTelephone());
        existingUser.setImage(user.getImage());
        existingUser.setDescription(user.getDescription());
    
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            existingUser.setPassword(passwordEncoder.encode(user.getPassword()));
        }
    
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            existingUser.setEmail(user.getEmail());
        }
    
        return repository.save(existingUser);
    }
    
    
    

    @Override
    public void deleteUser(Long userId) {
        repository.deleteById(userId);
    }

    @Override
    public User findById(Long userId) {
        return repository.findByUserId(userId);
    }
    @Transactional
    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public void addFavoriteVacancy(User user, Long vacancyId) {
        Vacancy vacancy = vacancyRepository.findById(vacancyId).orElseThrow(() -> new RuntimeException("Vacancy not found"));
        user.getFavoriteVacancies().add(vacancy);
        repository.save(user);
    }
    @Transactional
    @Override
    public Set<Vacancy> getFavoriteVacancies(User user) {
        return user.getFavoriteVacancies();
    }
}
