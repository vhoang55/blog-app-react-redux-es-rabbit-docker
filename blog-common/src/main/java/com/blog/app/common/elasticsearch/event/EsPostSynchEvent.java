package com.blog.app.common.elasticsearch.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.blog.app.common.elasticsearch.model.Author;

import java.util.List;

public class EsPostSynchEvent {

    private String id;
    private String title;
    private String body;
    private List<Author> authors;
    private String[] tags;

    public EsPostSynchEvent() {
        //For Spring Web layer to bind cleanly..
    }

    @JsonCreator
    public EsPostSynchEvent(@JsonProperty("id") String id,
                            @JsonProperty("titel") String title,
                            @JsonProperty("body") String body,
                            @JsonProperty("authors") List<Author> authors,
                            @JsonProperty("tags") String [] tags) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authors = authors;
        this.tags = tags;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public void setTags(String[] tags) {
        this.tags = tags;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("body", body)
                .add("authors", authors)
                .add("tags", tags)
                .toString();
    }



}
