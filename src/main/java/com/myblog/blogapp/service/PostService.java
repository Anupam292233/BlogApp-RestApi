package com.myblog.blogapp.service;

import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

    PostResponse getAllPosts(int pageNo, int pageSize);

    PostDto getAllPosts();

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy);

    PostDto getPostById(long id);

    PostDto updatePost(PostDto postDto, long id);


    void deletePost(long id);

    PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir);
}
