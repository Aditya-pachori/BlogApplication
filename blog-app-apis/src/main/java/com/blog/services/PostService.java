package com.blog.services;

import java.util.List;

import com.blog.entities.Post;
import com.blog.payloads.PostDto;

public interface PostService 
{
	//create
	PostDto createPost(PostDto postDto , Integer userId , Integer categoryId);
	
	//update
	PostDto updatePost(PostDto postDto , Integer postId);
	
	//Delete
	void deletePost(Integer postId);
	
	//getAllPosts
	List<PostDto> getAllPost();
	
	//getSinglePOst
	PostDto getPostById(Integer postId);
	
	//getAllPostByCategory
	List<PostDto> getPostsByCategory(Integer categoryId);
	
	//getAllPostByUser
	List<PostDto> getPostsByUser(Integer userId);
	
	//searchPosts
	List<Post> searchPosts(String keyword);
}