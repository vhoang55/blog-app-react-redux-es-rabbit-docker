package com.blog.app.entity;


import com.google.common.base.MoreObjects;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "POST")
public class PostEntity implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 2000)
    private String content;

    @ElementCollection
    @CollectionTable(
            name="AUTHOR",
            joinColumns=@JoinColumn(name="POST_ID")
    )
    @Column(name="author")
    private List<String> authors;

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    @ElementCollection
    @CollectionTable(
            name="TAG",
            joinColumns=@JoinColumn(name="TAG_ID")
    )
    @Column(name="author")
    private List<String> tags;

    public PostEntity() {
    }

    public PostEntity(String title, String content, List<String> authors, List<String> tags) {
        this.title = title;
        this.content = content;
        this.authors = authors;
        this.tags = tags;
    }

    public PostEntity(Long id, String title, String content, List<String> authors, List<String> tags) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.authors = authors;
        this.tags = tags;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthor(List<String> authors) {
        this.authors = authors;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PostEntity)) return false;
        PostEntity postEntity = (PostEntity) o;
        return Objects.equals(id, postEntity.id) &&
                Objects.equals(title, postEntity.title) &&
                Objects.equals(content, postEntity.content) &&

                Objects.equals(authors, postEntity.authors);

    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, content, authors);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("title", title)
                .add("content", content)
                .add("authors", authors)
                .add("tags", tags)
                .toString();
    }
}
