package com.tiy.entities;

import javax.persistence.*;

/**
 * Created by dbash on 2/9/2017.
 */
@Entity
public class BlogPostText {
    @Id
    @GeneratedValue
    private Long id;
    private String text;
    @OneToOne
    @JoinColumn(name = "blog_post_id", nullable = true)
//    @MapsId
    private BlogPost blogPost;

    public BlogPostText() {
    }

    public BlogPost getBlogPost() {
        return blogPost;
    }

    public void setBlogPost(BlogPost blogPost) {
        this.blogPost = blogPost;
    }

    public BlogPostText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
