package com.blog.app.service;


import com.blog.app.entity.PostEntity;

import java.util.List;

public interface PostService {
    List<PostEntity> findAll();
    PostEntity findOnePostById(Long id);
    void save(PostEntity post);
    List<PostEntity> findSearchTerm(String q);
    List<PostEntity> findPostContaining(String q);
}
