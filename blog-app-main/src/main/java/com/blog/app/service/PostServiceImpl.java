package com.blog.app.service;


import com.blog.app.entity.PostEntity;
import com.blog.app.common.elasticsearch.model.Author;
import com.blog.app.common.elasticsearch.event.EsPostSynchEvent;
import com.blog.app.common.elasticsearch.model.Post;
import com.blog.app.common.elasticsearch.service.EsPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.blog.app.messaging.RabbitMqMessageGateway;
import com.blog.app.repository.PostEntityRepository;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostEntityRepository dbPostEntityRepository;
    private final EsPostService esPostService;
    private final RabbitMqMessageGateway workUnitGateway;

    @Autowired
    public PostServiceImpl(PostEntityRepository dbPostEntityRepository,
                           EsPostService esPostService,
                           RabbitMqMessageGateway workUnitGateway){

        this.dbPostEntityRepository = dbPostEntityRepository;
        this.esPostService = esPostService;
        this.workUnitGateway = workUnitGateway;
    }


    @Override
    public List<PostEntity> findAll() {
        return dbPostEntityRepository.findAll();
    }

    @Override
    public PostEntity findOnePostById(Long id) {
        return dbPostEntityRepository.findById(id);
    }

    @Override
    public void save(PostEntity postEntity) {
        PostEntity entity = dbPostEntityRepository.save(postEntity);
        workUnitGateway.generate(toEsSynchEvent(entity));
    }

    private EsPostSynchEvent toEsSynchEvent(PostEntity postEntity) {
        return new EsPostSynchEvent(
                postEntity.getId().toString(),
                postEntity.getTitle(),
                postEntity.getContent(),
                toAuthors(postEntity),
                toTags(postEntity)
        );
    }

    private String[] toTags(PostEntity postEntity) {
        return postEntity.getTags().toArray(new String[postEntity.getTags().size()]);
    }

    private List<Author> toAuthors(PostEntity postEntity) {
        return postEntity.getAuthors().stream().map(this::toAuthor).collect(Collectors.toList());
    }

    private Author toAuthor(String author){
        return new Author(author);
    }


    public List<PostEntity> findSearchTerm(String q) {
        List<Post> posts = esPostService.findByBodyContaining(q);
        return posts.stream().map(this::toPostEntity).collect(Collectors.toList());
    }

    public List<PostEntity> findPostContaining(String q) {
        List<Post> posts = esPostService.findPostContaining(q);
        return posts.stream().map(this::toPostEntity).collect(Collectors.toList());
    }

    private PostEntity toPostEntity(Post post) {
        return new PostEntity(
                Long.valueOf(post.getId()),
                post.getTitle(),
                post.getBody(),
                getAuthors(post.getAuthors()),
                Arrays.asList(post.getTags())
        );
    }

    private List<String> getAuthors(List<Author> authors) {
        return authors.stream().map(a->a.getName()).collect(Collectors.toList());
    }


}
