package com.springboot.blog.springbootblogrestapi.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.springboot.blog.springbootblogrestapi.paylod.PostDto;
import com.springboot.blog.springbootblogrestapi.paylod.PostResponse;
import com.springboot.blog.springbootblogrestapi.paylod.postDtoV2;
import com.springboot.blog.springbootblogrestapi.service.PostService;
import com.springboot.blog.springbootblogrestapi.util.AppConstants;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(value = "CRUD Rest Apis for Post resources")
@RestController
@RequestMapping
public class PostController {

	@Autowired
	private PostService postService;

	@ApiOperation(value = "Create Post REST API")
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/api/v1/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
		return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
	}

	@ApiOperation(value = "Get all Posts REST API")
	@GetMapping("/api/v1/posts")
	public PostResponse getAllPost(
			@RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
			@RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
			@RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
			@RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir

	) {
		return postService.getAllPost(pageNo, pageSize, sortBy, sortDir);
	}

	@ApiOperation(value = "Get Post by Id REST API")
	@GetMapping("/api/v1/posts/{id}")
	public ResponseEntity<PostDto> getPostByIdV1(@PathVariable(name = "id") long id) {
		return ResponseEntity.ok(postService.getPostById(id));
	}

//	@GetMapping("/api/v2/posts/{id}")
//	public ResponseEntity<postDtoV2> getPostByIdV2(@PathVariable(name = "id") long id) {
//		PostDto postDto = postService.getPostById(id);
//		postDtoV2 postDtoV2 = new postDtoV2();
//		postDtoV2.setId(postDto.getId());
//		postDtoV2.setTitle(postDto.getTitle());
//		postDtoV2.setDescription(postDto.getDescription());
//		postDtoV2.setContent(postDto.getContent());
//		List<String> tags = new ArrayList<String>();
//		tags.add("Java");
//		tags.add("Spring Boot");
//		tags.add("AWS");
//		postDtoV2.setTags(tags);
//		return ResponseEntity.ok(postDtoV2);
//	}

	@ApiOperation(value = "Update Post by Id REST API")
	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/api/v1/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@Valid @RequestBody PostDto postDto, @PathVariable(name = "id") long id) {
		return new ResponseEntity<>(postService.updatePost(postDto, id), HttpStatus.OK);
	}

	@ApiOperation(value = "Delete Post by Id REST API")
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/api/v1/posts/{id}")
	public ResponseEntity<String> deletePost(@PathVariable(name = "id") long id) {
		postService.deletePostById(id);
		return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
	}

}
