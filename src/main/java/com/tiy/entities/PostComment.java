package com.tiy.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * Created by dbash on 2/9/2017.
 */
@Entity
public class PostComment {
    @Id
    @GeneratedValue
    private Long id;
    private String comments;
    @ManyToOne
    private BlogPost blogPost;

    public PostComment() {
    }

    public PostComment(String comments) {
        this.comments = comments;
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }
}
