package com.blog.app.common.elasticsearch.service;

import com.blog.app.common.elasticsearch.model.Post;
import com.blog.app.common.elasticsearch.repository.PostRepository;
import com.blog.app.common.elasticsearch.repository.PostRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsPostServiceImpl implements EsPostService {

    private PostRepository postRepository;
    private PostRepositoryCustom postRepositoryCustom;

    @Autowired
    public EsPostServiceImpl(PostRepository postRepository, PostRepositoryCustom postRepositoryCustom) {
        this.postRepository = postRepository;
        this.postRepositoryCustom = postRepositoryCustom;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }

    @Override
    public Post findOne(String id) {
        return postRepository.findOne(id);
    }

    @Override
    public Iterable<Post> findAll() {
        return postRepository.findAll();
    }

    @Override
    public Page<Post> findByAuthorName(String name, Pageable pageable) {
        return postRepository.findByAuthorsName(name, pageable);
    }

    @Override
    public Page<Post> findByAuthorNameUsingCustomQuery(String name, Pageable pageable) {
        return postRepository.findByAuthorsNameUsingCustomQuery(name, pageable);
    }

    @Override
    public List<Post> findByBodyContaining(String body) {
        return postRepositoryCustom.findByBodyContaining(body);
    }

    @Override
    public List<Post> findPostContaining(String text) {
        return postRepositoryCustom.findPostContainingText(text);
    }

    @Override
    public long count() {
        return postRepository.count();
    }

    @Override
    public void delete(Post post) {
        postRepository.delete(post);
    }
}