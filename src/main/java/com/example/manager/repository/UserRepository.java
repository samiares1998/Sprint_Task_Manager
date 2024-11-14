package com.example.manager.repository;

import com.example.manager.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findOneByEmail(String email);

    //@EntityGraph(attributePaths = {"tasks"})
    Optional<User> findById(Long id);
}
