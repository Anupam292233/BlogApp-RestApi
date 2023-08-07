package com.myblog.blogapp.service.impl;

import com.myblog.blogapp.entities.Post;
import com.myblog.blogapp.exception.ResourceNotFoundException;
import com.myblog.blogapp.payload.PostDto;
import com.myblog.blogapp.payload.PostResponse;
import com.myblog.blogapp.repository.PostRepository;
import com.myblog.blogapp.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepo;
    private ModelMapper mapper;

    public PostServiceImpl(PostRepository postRepo,ModelMapper mapper) {
        this.postRepo = postRepo;
        this.mapper = mapper;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post postEntity = postRepo.save(post);
        PostDto dto = mapToDto(postEntity);
        return dto;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize) {
        return null;
    }

    @Override
    public PostDto getAllPosts() {
        return null;
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy) {
        return null;
    }


    @Override
    public PostResponse getAllPosts(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by(sortBy));
        Page<Post> posts = postRepo.findAll(pageable);
        List<PostDto> contents = posts.getContent()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
        PostResponse postResponse = new PostResponse();
        postResponse.setContent(contents);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setLast(posts.isLast());
        return postResponse;
    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDto(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        Post updatedPost = postRepo.save(post);
        return mapToDto(updatedPost);
    }

    @Override
    public void deletePost(long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        postRepo.deleteById(id);
    }


    // convert Entity into DTO
    private PostDto mapToDto(Post post) {
        PostDto postDto = mapper.map(post, PostDto.class);

//        PostDto dto = new PostDto();
//        dto.setId(post.getId());
//        dto.setTitle(post.getTitle());
//        dto.setDescription(post.getDescription());
//        dto.setContent(post.getContent());
//        return dto;
        return postDto;
    }

    // convert DTO to entity
    private Post mapToEntity(PostDto postDto) {

        Post post = mapper.map(postDto, Post.class);

//        Post post = new Post();
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
//        post.setTitle(postDto.getTitle());
//        return post;
        return post;
    }
}
