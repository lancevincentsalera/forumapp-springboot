package com.SaleraPrefinals.forumapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaleraPrefinals.forumapp.model.User;

@Repository
public interface ForumUserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByFirstNameAndLastName(String firstName, String lastName);
    
}
