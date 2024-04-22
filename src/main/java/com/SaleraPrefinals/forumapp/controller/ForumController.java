package com.SaleraPrefinals.forumapp.controller;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SaleraPrefinals.forumapp.model.*;
import com.SaleraPrefinals.forumapp.service.ForumService;

@RestController
@RequestMapping("/forum")
@CrossOrigin
public class ForumController {
    @Autowired
    private ForumService forumService;

    @GetMapping("/posts") // page starts at index 0
    public ResponseEntity<List<Post>> getPosts(@RequestParam int page) {
        return new ResponseEntity<>(forumService.getPosts(page), HttpStatus.OK);
    }

    @PostMapping("/create-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User newuser = forumService.createUser(user.getFirstName(), user.getLastName());
        return new ResponseEntity<>(newuser, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Optional<User> loggedUser = forumService.login(user);
        if(loggedUser.isPresent()) {
            return new ResponseEntity<>(loggedUser.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
        }

    }

    @PostMapping("/create-post")
    public ResponseEntity<Boolean> newPost(@RequestBody Map<String, String> post) {
        Integer userId = Integer.parseInt(post.get("id"));
        String text = post.get("post");
        boolean result = forumService.newPost(userId, text);
        if (result) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete-post/{id}")
    public ResponseEntity<Boolean> deletePost(@PathVariable int id) {
        return new ResponseEntity<>(forumService.deletePost(id), HttpStatus.OK);
    }

    @PostMapping("/reply-post")
    public ResponseEntity<Boolean> replyPost(@RequestBody Map<String, String> reply) {
        Integer userID = Integer.parseInt(reply.get("user_id"));
        Integer postID = Integer.parseInt(reply.get("post_id"));
        String text = reply.get("reply");
        boolean result = forumService.replyPost(userID, postID, text);
        if(result) {
            return new ResponseEntity<>(true, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/delete-reply/{id}")
    public ResponseEntity<Boolean> deleteReply(@PathVariable int id) {
       boolean result = forumService.deleteReply(id);
       if(result) {
           return new ResponseEntity<>(true, HttpStatus.OK);
       } else {
           return new ResponseEntity<>(false, HttpStatus.NOT_FOUND);
       }
    }
}
