package com.ait.rest;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ait.dto.PostDto;
import com.ait.dto.PostResponse;
import com.ait.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

	@Autowired
	private PostService postService;

	@PostMapping("/posts")
	public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postdto) {
		return new ResponseEntity<>(postService.createPost(postdto), HttpStatus.CREATED);
	}

	@GetMapping("/posts/{id}")
	public ResponseEntity<PostDto> getPostById(@PathVariable Integer id) {
		return new ResponseEntity<>(postService.getPostById(id), HttpStatus.OK);
	}
	@GetMapping("/posts")
	public ResponseEntity<List<PostDto>> getAllPosts() {
		return new ResponseEntity<>(postService.getAllPosts(), HttpStatus.OK);
	}
	@PutMapping("/posts/{id}")
	public ResponseEntity<PostDto> updatePost(@PathVariable Integer id,@Valid @RequestBody PostDto postdto) {
		return new ResponseEntity<>(postService.updatePost(id,postdto), HttpStatus.OK);
	}
	@DeleteMapping("/posts/{id}")
	public ResponseEntity<String> deletePost(@PathVariable Integer id) {
		return new ResponseEntity<>(postService.deletePostById(id), HttpStatus.OK);
	}
	
	@GetMapping("/page")
	public ResponseEntity<PostResponse> allPostsWithPagination(@RequestParam Integer pageNumber,@RequestParam Integer pageSize,@RequestParam String sortBy) {
		return new ResponseEntity<>(postService.getAllPostPagination(pageSize,pageNumber-1,sortBy), HttpStatus.OK);
	}
	

}
