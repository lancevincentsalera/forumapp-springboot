package com.SaleraPrefinals.forumapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaleraPrefinals.forumapp.model.Post;

@Repository
public interface ForumPostRepository extends JpaRepository<Post, Integer> {
   
}
