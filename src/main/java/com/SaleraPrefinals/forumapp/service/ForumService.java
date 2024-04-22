package com.SaleraPrefinals.forumapp.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import com.SaleraPrefinals.forumapp.model.*;
import com.SaleraPrefinals.forumapp.repository.*;

@Service
public class ForumService {
    @Autowired
    private ForumUserRepository userRepository;

    @Autowired
    private ForumPostRepository postRepository;

    @Autowired
    private ForumReplyRepository replyRepository;

    public List<Post> getPosts(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        return postRepository.findAll(pageable).getContent();
    }

    public User createUser(String firstName, String lastName) {
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }

    public Optional<User> login(User user) {
        return userRepository.findByFirstNameAndLastName(user.getFirstName(), user.getLastName());
    }


    public boolean newPost(Integer userId, String text) {
    try {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found"));

        Post post = new Post();
        post.setUser(user);
        post.setText(text);

        postRepository.save(post);
        return true;
    } catch (Exception e) {
        return false;
    }
}


    public boolean deletePost(int id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean replyPost(int userId, int postId, String text) {
        try {
            Reply reply = new Reply();
            User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
            Post post = postRepository.findById(postId).orElseThrow(() -> new RuntimeException("Post not found"));

            reply.setUser(user);
            reply.setPost(post);
            reply.setText(text);

            replyRepository.save(reply);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteReply(int id) {
        if(replyRepository.existsById(id)) {
            replyRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
}
