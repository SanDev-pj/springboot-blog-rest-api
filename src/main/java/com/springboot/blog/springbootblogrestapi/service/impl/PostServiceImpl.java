package com.springboot.blog.springbootblogrestapi.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.springboot.blog.springbootblogrestapi.entity.Post;
import com.springboot.blog.springbootblogrestapi.exception.ResourceNotFoundException;
import com.springboot.blog.springbootblogrestapi.paylod.PostDto;
import com.springboot.blog.springbootblogrestapi.paylod.PostResponse;
import com.springboot.blog.springbootblogrestapi.repository.PostRepository;
import com.springboot.blog.springbootblogrestapi.service.PostService;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	@Override
	public PostDto createPost(PostDto postDto) {
		ModelMapper modelMapper = new ModelMapper();
		Post post = modelMapper.map(postDto, Post.class);
		Post newPost = postRepository.save(post);
		PostDto postResponse = modelMapper.map(newPost, PostDto.class);
		return postResponse;
	}

	@Override
	public PostResponse getAllPost(int pageNo, int pageSize, String sortBy, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
				: Sort.by(sortBy).descending();
		ModelMapper modelMapper = new ModelMapper();
		Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
		List<PostDto> content = postRepository.findAll(pageable).stream()
				.map(post -> modelMapper.map(post, PostDto.class)).collect(Collectors.toList());
		PostResponse postResponse = new PostResponse();
		postResponse.setContent(content);
		postResponse.setPageNo(postRepository.findAll(pageable).getNumber());
		postResponse.setPageSize(postRepository.findAll(pageable).getSize());
		postResponse.setTotalElements(postRepository.findAll(pageable).getTotalElements());
		postResponse.setTotalPages(postRepository.findAll(pageable).getTotalPages());
		postResponse.setLast(postRepository.findAll(pageable).isLast());

		return postResponse;
	}

	@Override
	public PostDto getPostById(Long id) {
		ModelMapper modelMapper = new ModelMapper();
		return modelMapper.map(
				postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id)),
				PostDto.class);
	}

	@Override
	public PostDto updatePost(PostDto postDto, long id) {
		ModelMapper modelMapper = new ModelMapper();
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		post.setTitle(postDto.getTitle());
		post.setDescription(postDto.getDescription());
		post.setContent(postDto.getContent());

		Post updatedPost = postRepository.save(post);
		return modelMapper.map(updatedPost, PostDto.class);
	}

	@Override
	public void deletePostById(long id) {
		Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
		postRepository.delete(post);

	}

}
