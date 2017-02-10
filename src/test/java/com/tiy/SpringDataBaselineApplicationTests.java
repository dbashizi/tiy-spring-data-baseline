package com.tiy;

import com.tiy.entities.BlogPost;
import com.tiy.entities.BlogPostText;
import com.tiy.entities.PostComment;
import com.tiy.entities.User;
import com.tiy.repos.BlogPostRepo;
import com.tiy.repos.BlogPostTextRepo;
import com.tiy.repos.PostCommentRepo;
import com.tiy.repos.UserRepo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringDataBaselineApplicationTests {

	@Autowired
	private BlogPostRepo blogPostRepo;
	@Autowired
	private BlogPostTextRepo blogPostTextRepo;
	@Autowired
	private PostCommentRepo postCommentRepo;
	@Autowired
	private UserRepo userRepo;

	@Test
	public void contextLoads() {
	}

	@Test
	public void testSimpleBlogPost() {
		BlogPost blogPost = new BlogPost();
		Date creationDate = blogPost.getDateCreated();
		long numPostsBefore = blogPostRepo.count();
		blogPostRepo.save(blogPost);
		long numPostsAfter = blogPostRepo.count();

		assertNotNull(blogPost.getId());
		assertEquals(numPostsBefore+1, numPostsAfter);
		BlogPost retrievedBlogPost = blogPostRepo.findOne(blogPost.getId());
		assertEquals(creationDate, retrievedBlogPost.getDateCreated());

		blogPostRepo.delete(blogPost);
		numPostsAfter = blogPostRepo.count();
		assertEquals(numPostsBefore, numPostsAfter);
	}

	@Test
	public void testSimpleBlogPostText() {
		BlogPostText blogPostText = new BlogPostText("Testing simple post text");
		long numTextsBefore = blogPostTextRepo.count();
		blogPostTextRepo.save(blogPostText);
		long numTextsAfter = blogPostTextRepo.count();

		assertNotNull(blogPostText.getId());
		assertEquals(numTextsBefore+1, numTextsAfter);

		blogPostTextRepo.delete(blogPostText);
		numTextsAfter = blogPostTextRepo.count();
		assertEquals(numTextsBefore, numTextsAfter);
	}

	/**
	 * Test OneToOne bi-directional relationship
	 */
	@Test
	public void testBlogPostWithText() {
		BlogPostText blogPostText = new BlogPostText("Testing blog post with text relationship");
		assertNull(blogPostText.getId());
		BlogPost blogPost = new BlogPost();
		// for the bi-directional relationship to be fully functional
		// (i.e. actually bi-directional - see "bi-directional" assert section below)
		// we need to set the references on each side
		// First - from the blog post
		blogPost.setBlogPostText(blogPostText);
		// Second - from the blog post text
//		blogPostText.setBlogPost(blogPost); <---- no longer necessary because it's done inside of
		// the setBlogPostText() method
		long numTextsBefore = blogPostTextRepo.count();

		// because we cascade, we just need to save the
		// blog post (mapped_by side of the relationship)
		// and it will save the blog post text (as long
		// as the blog post text object is manually set as a reference
		// on the blog post object - see above)
		blogPostRepo.save(blogPost);

		// make sure the blog post and the blog post text both got saved to the database
		// with just the single call to save()
		assertNotNull(blogPost.getId());
		assertNotNull(blogPostText.getId());
		assertNotNull(blogPost.getBlogPostText());
		long numTextsAfter = blogPostTextRepo.count();
		assertEquals(numTextsBefore+1, numTextsAfter);

		// it's a bi-directional relationship, so I should be able to get either record from
		// the database and have a reference to the related record
		// First - from the BlogPost
		BlogPost retrievedPost = blogPostRepo.findOne(blogPost.getId());
		assertNotNull(retrievedPost.getBlogPostText());
		assertEquals(blogPostText.getId(), retrievedPost.getBlogPostText().getId());
		// Second - from the BlogPostText
		BlogPostText retrievedBlogPostText = blogPostTextRepo.findOne(blogPostText.getId());
		assertNotNull(blogPostText.getBlogPost());
		assertEquals(blogPost.getId(), blogPostText.getBlogPost().getId());

		blogPostRepo.delete(blogPost);
		// make sure the blog post text was deleted when the blog post was deleted
		numTextsAfter = blogPostTextRepo.count();
		assertEquals(numTextsBefore, numTextsAfter);
	}

	@Test
	public void testSimplePostComments() {
		PostComment postComment = new PostComment("Interesting take on this unit testing logic stuff. But why???");
		long numCommentsBefore = postCommentRepo.count();
		postCommentRepo.save(postComment);

		long numCommentsAfter = postCommentRepo.count();
		assertNotNull(postComment.getId());
		assertEquals(numCommentsBefore+1, numCommentsAfter);

		postCommentRepo.delete(postComment);
		numCommentsAfter = postCommentRepo.count();
		assertEquals(numCommentsBefore, numCommentsAfter);
	}

	@Test
	public void testBlogPostWithComments() {
		BlogPost blogPost = new BlogPost();
		PostComment postComment = new PostComment("comment on a blog post for testing one-to-many relationship");
		// using the addPostComment() method instead of adding directly
		// to the collection, so that we don't have to manually set the other
		// side of the relationship (see addPostComemnt() method in BlogPost for more info
		blogPost.addPostComment(postComment);
//		postComment.setBlogPost(blogPost); ^----------- see comment above - not required anymore
		long numCommentsBefore = postCommentRepo.count();

		blogPostRepo.save(blogPost);

		long numCommentsAfter = postCommentRepo.count();
		assertNotNull(postComment.getId());
		assertEquals(numCommentsBefore+1, numCommentsAfter);

		// Make sure the relationship is actually bi-directional
		// From the blog post side
		BlogPost retrievedBlogPost = blogPostRepo.findOne(blogPost.getId());
		assertNotNull(retrievedBlogPost.getPostComments());
		assertEquals(postComment.getId(), retrievedBlogPost.getPostComments().get(0).getId());
		// From the comment side
		PostComment retrievedComment = postCommentRepo.findOne(postComment.getId());
		assertNotNull(retrievedComment.getBlogPost());
		assertEquals(blogPost.getId(), retrievedComment.getBlogPost().getId());

		// Add a second comment
		PostComment secondComment = new PostComment("second test comment");
		secondComment.setBlogPost(blogPost);
		blogPost.addPostComment(secondComment);

		// Note that adding an entity that was never saved to the database
		// to an entity that already exists in the database
		// only requires you to save the new entity
		// Also note that your "old" reference to the existing entity
		// will not have a reference to the new entity until you re-retrieve
		// the exising entity from the database (as we do in the asserts below)
		postCommentRepo.save(secondComment);
		assertNotNull(secondComment.getId());
		numCommentsAfter = postCommentRepo.count();
		assertEquals(numCommentsBefore+2, numCommentsAfter);

		// test the bi-directional relationship again
		// From the blog post side
		retrievedBlogPost = blogPostRepo.findOne(blogPost.getId());
		assertNotNull(retrievedBlogPost.getPostComments());
		assertEquals(2, retrievedBlogPost.getPostComments().size());
		// From the comment side
		retrievedComment = postCommentRepo.findOne(postComment.getId());
		assertNotNull(retrievedComment.getBlogPost());
		assertEquals(blogPost.getId(), retrievedComment.getBlogPost().getId());


		blogPostRepo.delete(blogPost);
		numCommentsAfter = postCommentRepo.count();
		assertEquals(numCommentsBefore, numCommentsAfter);
	}

	@Test
	public void testSimpleUser() {
		User user = new User("test@tiy.com", "Test Tester");
		long numUsersBefore = userRepo.count();
		userRepo.save(user);

		long numUsersAfter = userRepo.count();
		assertNotNull(user.getId());
		assertEquals(numUsersBefore+1, numUsersAfter);

		userRepo.delete(user);
		numUsersAfter = userRepo.count();
		assertEquals(numUsersBefore, numUsersAfter);
	}

	@Test
	public void testBlogPostWithUser() {
		User user = new User("test@tiy.com", "Test Tester");
		long numBlogPostsBefore = blogPostRepo.count();
		long numUsersBefore = userRepo.count();
		BlogPost blogPost = new BlogPost();
		blogPost.addUser(user);

		blogPostRepo.save(blogPost);
		long numBlogPostsAfter = blogPostRepo.count();
		long numUsersAfter = userRepo.count();
		assertNotNull(blogPost.getId());
		assertNotNull(user.getId());
		assertEquals(numBlogPostsBefore+1, numBlogPostsAfter);
		assertEquals(numUsersBefore+1, numUsersAfter);
		assertNotNull(blogPost.getUsers());
		assertEquals(1, blogPost.getUsers().size());
		assertEquals(user.getId(), blogPost.getUsers().get(0).getId());

		// add another blog post to the user
		BlogPost secondPost = new BlogPost();
		user.addBlogPost(secondPost);
		userRepo.save(user);

		numBlogPostsAfter = blogPostRepo.count();
		assertEquals(numBlogPostsBefore+2, numBlogPostsAfter);

		// add another user to the first blog post (let's pretend each user is a co-author
		// to make this make sense)
		User secondUser = new User("test2@tiy.com", "Test2 Tester");
		blogPost.addUser(secondUser);
		blogPostRepo.save(blogPost);
		numUsersAfter = userRepo.count();
		assertEquals(numUsersBefore+2, numUsersAfter);

		blogPost.removeUserBiDirectional(user);
		blogPost.removeUserBiDirectional(secondUser);
		blogPostRepo.save(blogPost);
		blogPostRepo.delete(blogPost);
		secondPost.removeUserBiDirectional(user);
		blogPostRepo.save(secondPost);
		blogPostRepo.delete(secondPost);
		numBlogPostsAfter = blogPostRepo.count();
		assertEquals(numBlogPostsBefore, numBlogPostsAfter);
		userRepo.delete(user);
		numUsersAfter = userRepo.count();
		assertEquals(numUsersBefore, numUsersAfter);
	}
}
