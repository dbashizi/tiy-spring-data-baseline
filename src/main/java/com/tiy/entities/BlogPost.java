package com.tiy.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by dbash on 2/9/2017.
 */
@Entity
public class BlogPost {
    @Id
    @GeneratedValue
    private Long id;
    @OneToOne(mappedBy = "blogPost",
              cascade = CascadeType.ALL,
              orphanRemoval = true,
              optional = true)
    private BlogPostText blogPostText;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated = new Date();
    @OneToMany(cascade = CascadeType.ALL,
               mappedBy = "blogPost",
               orphanRemoval = true,
               fetch = FetchType.EAGER)
    private List<PostComment> postComments = new ArrayList<>();

    public List<PostComment> getPostComments() {
        if (postComments == null) {
            postComments = new ArrayList<PostComment>();
        }
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public BlogPostText getBlogPostText() {
        return blogPostText;
    }

    public void setBlogPostText(BlogPostText blogPostText) {
        this.blogPostText = blogPostText;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }
}
