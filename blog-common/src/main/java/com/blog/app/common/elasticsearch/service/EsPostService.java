package com.blog.app.common.elasticsearch.service;

import com.blog.app.common.elasticsearch.model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EsPostService {
    Post save(Post post);

    Post findOne(String id);

    Iterable<Post> findAll();

    Page<Post> findByAuthorName(String name, Pageable pageable);

    Page<Post> findByAuthorNameUsingCustomQuery(String name, Pageable pageable);

    List<Post> findByBodyContaining(String body);

    List<Post> findPostContaining(String text);

    long count();

    void delete(Post post);

}