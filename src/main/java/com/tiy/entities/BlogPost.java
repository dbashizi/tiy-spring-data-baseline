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

    @ManyToMany(mappedBy = "blogPosts",
            cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<User> users = new ArrayList<User>();

    public void addUser(User user) {
        users.add(user);
    }

    public void addUserBiDirectional(User user) {
        users.add(user);
        user.addBlogPost(this);
    }

    public void removeUserBiDirectional(User user){
        users.remove(user);
        user.removeBlogPost(this);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(List<PostComment> postComments) {
        this.postComments = postComments;
    }

    public void addPostComment(PostComment postComment) {
        postComments.add(postComment);
        // ensure the bi-directional aspect of the relationship here
        postComment.setBlogPost(this);
    }

    public BlogPostText getBlogPostText() {
        return blogPostText;
    }

    public void setBlogPostText(BlogPostText blogPostText) {
        this.blogPostText = blogPostText;
        // set the reverse for the bi-directional relationship
        blogPostText.setBlogPost(this);
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
