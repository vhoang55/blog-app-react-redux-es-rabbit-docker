package com.blog.app.controller;


import com.blog.app.entity.PostEntity;
import com.blog.app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static java.util.Arrays.asList;
import static org.apache.commons.lang.StringUtils.isBlank;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllPosts() {
        List<PostEntity> allPosts =  postService.findAll();
        return new ResponseEntity<>(allPosts, HttpStatus.OK);
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public ResponseEntity<?> searchPosts(@RequestParam(value="q", required=false) String q) {
        List<PostEntity> postEnties = isBlank(q)
                ? postService.findAll()
               // : postService.findSearchTerm(q);
                : postService.findPostContaining(q);
        return new ResponseEntity<>(postEnties, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<PostEntity> getPostById(@PathVariable Long id) {
        PostEntity post = postService.findOnePostById(id);
        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> addNewPost(@RequestBody PostDTO postDTO) {
        PostEntity post = toPost(postDTO);
        postService.save(post);
        return new ResponseEntity<>(postDTO.getTitle(), HttpStatus.CREATED);
    }

    private PostEntity toPost(PostDTO postDTO) {
        PostEntity post = new PostEntity();
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAuthor(asList(postDTO.getAuthor()));
        post.setTags(asList(postDTO.getTag()));
        return post;
    }
}
