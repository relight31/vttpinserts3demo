package com.vttp.inserts3demo.controllers;

import java.util.Optional;

import com.vttp.inserts3demo.model.Post;
import com.vttp.inserts3demo.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/post")
public class PostRestController {
    @Autowired
    PostRepository repository;

    @GetMapping(path = "/{postID}/image")
    public ResponseEntity<byte[]> getPostImage(@PathVariable int postID) {
        Optional<Post> opt = repository.getPostByID(postID);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        final Post post = opt.get();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", post.getImageType());
        headers.add("Cache-Control", "max-age=604800");
        return ResponseEntity.ok()
                .header(null)
                .body(post.getImage());
    }

}
