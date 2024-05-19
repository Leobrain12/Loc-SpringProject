package com.Murc.Loc.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.Murc.Loc.Model.*;

public interface UserRepository extends JpaRepository<User,Long> {
    void deleteById(Long userId);
    User findByUserId(Long userId);
    User findByEmail(String email);
}
