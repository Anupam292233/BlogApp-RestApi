package com.myblog.blogapp.service;

import com.myblog.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto) ;

    List<CommentDto> getCommentsByPostId(Long postId);

    List<CommentDto> getCommentByPostId(Long postId);

    CommentDto createComment(long postId, CommentDto commentDto);

    CommentDto updateComment(Long postId, Long commentId, CommentDto commentDto);

    CommentDto updateComment(Long postId, long commentId, CommentDto commentRequest);

    void deleteComment(Long postId, Long commentId);
}
