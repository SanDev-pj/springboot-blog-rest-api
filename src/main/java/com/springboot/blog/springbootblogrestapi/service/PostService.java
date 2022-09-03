package com.springboot.blog.springbootblogrestapi.service;

import com.springboot.blog.springbootblogrestapi.paylod.PostDto;
import com.springboot.blog.springbootblogrestapi.paylod.PostResponse;


public interface PostService {
    PostDto createPost(PostDto postDto);

    PostResponse getAllPost(int pageNo,int pageSize, String sortBy,String sortDir); 

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto postDto, long id);

    void deletePostById(long id);
}
