package com.tiy.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dbash on 2/9/2017.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;
    private String emailAddress;
    private String fullName;
    @ManyToMany(cascade =
            {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "User_BlogPost",
            joinColumns = {
                    @JoinColumn(
                            name = "user_id",
                            referencedColumnName = "id"
                    )
            },
            inverseJoinColumns = {
                    @JoinColumn(
                            name = "blog_post_id",
                            referencedColumnName = "id"
                    )
            }
    )
    private List<BlogPost> blogPosts = new ArrayList<BlogPost>();

    public User() {
    }

    public User(String emailAddress, String fullName) {
        this.emailAddress = emailAddress;
        this.fullName = fullName;
    }

    public void addBlogPost(BlogPost blogPost) {
        blogPosts.add(blogPost);
        blogPost.addUser(this);
    }

    public List<BlogPost> getBlogPosts() {
        return blogPosts;
    }

    public void setBlogPosts(List<BlogPost> blogPosts) {
        this.blogPosts = blogPosts;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
