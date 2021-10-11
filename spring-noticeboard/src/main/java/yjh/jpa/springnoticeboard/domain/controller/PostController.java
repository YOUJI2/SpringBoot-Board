package yjh.jpa.springnoticeboard.domain.controller;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import yjh.jpa.springnoticeboard.domain.ApiResponse;
import yjh.jpa.springnoticeboard.domain.dto.PostDto;
import yjh.jpa.springnoticeboard.domain.service.PostService;

@RestController
@RequestMapping(path = "/board/posts")
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping(path = "{postId}")
    public ApiResponse<PostDto> getPost(@PathVariable(name = "postId") Long postId) throws NotFoundException {
        PostDto postDto = postService.findPost(postId);
        return ApiResponse.ok(postDto);
    }

    @GetMapping(path = "")
    public ApiResponse<Page<Object>> getPostList(Pageable pageable){
        Page<Object> objects = postService.findAll(pageable);
        return ApiResponse.ok(objects);
    }

    @PostMapping(path = "")
    public ApiResponse<Long> insertPost(PostDto postDto){
        Long insert = postService.createPost(postDto);
        return ApiResponse.ok(insert);
    }

    @PutMapping(path = "{postId}")
    public ApiResponse<Long> updatePost(@PathVariable(name = "postId") Long postId, @RequestBody PostDto postDto) throws NotFoundException {
        Long update = postService.updatePost(postId, postDto);
        return ApiResponse.ok(update);
    }

    @DeleteMapping(path = "{postId}/{userId}")
    public void removePost(@PathVariable(name = "postId") Long postId, @PathVariable(name = "userId") Long userId) throws NotFoundException {
        postService.deletePost(postId, userId);
    }

    @DeleteMapping(path = "")
    public void removePostList(){
        postService.deleteAll();
    }

}
