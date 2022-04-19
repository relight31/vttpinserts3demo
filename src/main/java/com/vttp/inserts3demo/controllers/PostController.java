package com.vttp.inserts3demo.controllers;

import java.io.IOException;
import java.util.Optional;

import com.vttp.inserts3demo.model.Post;
import com.vttp.inserts3demo.repository.PostRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class PostController {
    @Autowired
    PostRepository repository;

    @PostMapping(path = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String postPost(@RequestParam MultipartFile image,
            @RequestPart String comment,
            @RequestPart String poster,
            Model model) {
        // String imageName = image.getOriginalFilename();
        // long imageSize = image.getSize();
        String imageType = image.getContentType();
        byte[] buff = new byte[0];
        try {
            buff = image.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // create new Post object
        Post post = new Post();
        post.setComment(comment);
        post.setPoster(poster);
        post.setImageType(imageType);
        post.setImage(buff);

        // insert into db
        if (repository.insertPost(post)) {
            return "result";
        }
        return "index";
    }

    @GetMapping(path = "/{postID}")
    public String getPostByID(@PathVariable int postID, Model model){
        Optional<Post> opt = repository.getPostByID(postID);
        if (opt.isPresent()) {
            model.addAttribute("post", opt.get());
        }
        return "post";
    }
}
