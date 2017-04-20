package com.blog.app.common.elasticsearch.repository;


import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import com.blog.app.common.elasticsearch.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.elasticsearch.index.query.QueryBuilders.multiMatchQuery;
import static org.elasticsearch.index.query.QueryBuilders.regexpQuery;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final ElasticsearchTemplate elasticsearchTemplate;

    @Autowired
    public PostRepositoryImpl(ElasticsearchTemplate elasticsearchTemplate){
        this.elasticsearchTemplate = elasticsearchTemplate;
    }


    @Override
    public List<Post> findByBodyContaining(String body) {
        final SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withFilter(regexpQuery("body", ".*" + body + ".*")).build();
        final List<Post> posts = elasticsearchTemplate.queryForList(searchQuery, Post.class);
        return posts;
    }

    @Override
    public List<Post> findPostContainingText(String text) {
        final SearchQuery searchQuery = new NativeSearchQueryBuilder()
                    .withQuery(multiMatchQuery(text)
                            .field("title")
                            .field("body").type(MultiMatchQueryBuilder.Type.BEST_FIELDS)).build();

        return elasticsearchTemplate.queryForList(searchQuery, Post.class);
    }
}
