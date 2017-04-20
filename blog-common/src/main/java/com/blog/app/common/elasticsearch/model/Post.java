package com.blog.app.common.elasticsearch.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.InnerField;
import org.springframework.data.elasticsearch.annotations.MultiField;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.elasticsearch.annotations.FieldIndex.not_analyzed;
import static org.springframework.data.elasticsearch.annotations.FieldType.Nested;
import static org.springframework.data.elasticsearch.annotations.FieldType.String;

@Document(indexName = "blog", type = "post")
public class Post {

    @Id
    private String id;

    @MultiField(mainField = @Field(type = String), otherFields = { @InnerField(index = not_analyzed, suffix = "verbatim", type = String) })
    private String title;

    @MultiField(mainField = @Field(type = String), otherFields = { @InnerField(index = not_analyzed, suffix = "verbatim", type = String) })
    private String body;

    @Field(type = Nested)
    private List<Author> authors;

    @Field(type = String, index = not_analyzed)
    private String[] tags;

    public Post() {
    }

    public Post(String title) {
        this.title = title;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String[] getTags() {
        return tags;
    }

    public void setTags(String... tags) {
        this.tags = tags;
    }


    public java.lang.String getBody() {
        return body;
    }

    public void setBody(java.lang.String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return "Post{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", authors=" + authors + ", tags=" + Arrays.toString(tags) + '}';
    }
}
