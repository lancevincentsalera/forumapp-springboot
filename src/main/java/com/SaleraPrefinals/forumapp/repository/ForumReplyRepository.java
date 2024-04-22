package com.SaleraPrefinals.forumapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SaleraPrefinals.forumapp.model.Reply;

@Repository
public interface ForumReplyRepository extends JpaRepository<Reply, Integer>{

}
