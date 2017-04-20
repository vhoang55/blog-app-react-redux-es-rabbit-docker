package com.blog.app.common.elasticsearch.repository;

import com.blog.app.common.elasticsearch.model.Post;

import java.util.List;

public interface PostRepositoryCustom {
    List<Post> findByBodyContaining(String body);

    List<Post> findPostContainingText(String text);
}
