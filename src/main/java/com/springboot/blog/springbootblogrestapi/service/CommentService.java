package com.springboot.blog.springbootblogrestapi.service;

import java.util.List;

import com.springboot.blog.springbootblogrestapi.paylod.CommentDto;

public interface CommentService {
	CommentDto createComment(long postId, CommentDto commentDto);

	List<CommentDto> getCommentsByPostId(long postId);

	CommentDto getCommentById(long postId, long commentId);

	CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);
	
	void deleteComment(Long postId, Long commentId);
}
