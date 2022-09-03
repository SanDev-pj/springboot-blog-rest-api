package com.springboot.blog.springbootblogrestapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.blog.springbootblogrestapi.paylod.CommentDto;
import com.springboot.blog.springbootblogrestapi.service.CommentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CRUD REST Api for comment Resources")
@RestController
@RequestMapping("/api/v1")
public class CommentController {

	@Autowired
	CommentService commentService;

	@ApiOperation(value = "Create Comment REST API")
	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable(value = "postId") long postId,
			@Valid @RequestBody CommentDto commentDto) {
		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get all Comments by Post ID REST API")
	@GetMapping("/posts/{postId}/comments")
	public List<CommentDto> getCommentsByPostId(@PathVariable(value = "postId") long postId) {
		return commentService.getCommentsByPostId(postId);
	}

	@ApiOperation(value = "Get Single Comment by ID REST API")
	@GetMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> getCommentById(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "id") long commentId) {
		return new ResponseEntity<>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
	}

	@ApiOperation(value = "Update Comment by ID REST API")
	@PutMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "id") long commentId, @Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity<CommentDto>(commentService.updateComment(postId, commentId, commentDto),
				HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Comment by ID REST API")
	@DeleteMapping("/posts/{postId}/comments/{id}")
	public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") long postId,
			@PathVariable(value = "id") long commentId) {
		commentService.deleteComment(postId, commentId);
		return new ResponseEntity<String>("Comment deleted Successfully", HttpStatus.OK);
	}

}
