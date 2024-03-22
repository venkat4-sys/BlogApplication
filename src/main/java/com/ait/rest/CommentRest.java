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
import org.springframework.web.bind.annotation.RestController;

import com.ait.dto.CommentDto;
import com.ait.service.CommentService;
import com.ait.utils.AppConstants;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class CommentRest {

	@Autowired
	private CommentService commentService;

	@PostMapping("/posts/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@PathVariable Integer postId,@Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
	}

	@GetMapping("/posts/{postId}/comments")
	public ResponseEntity<List<CommentDto>> getAllCommentsByPostid(@PathVariable Integer postId) {

		return new ResponseEntity<>(commentService.getAllCommentsByPostId(postId), HttpStatus.OK);
	}

	@GetMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> getComment(@PathVariable Integer postId, @PathVariable Integer commentId) {

		return new ResponseEntity<>(commentService.getCommentByPodtId(postId, commentId), HttpStatus.OK);
	}

	@PutMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<CommentDto> updateComment(@PathVariable Integer postId, @PathVariable Integer commentId,
		@Valid @RequestBody CommentDto commentDto) {

		return new ResponseEntity<>(commentService.updateComment(postId, commentId, commentDto), HttpStatus.OK);
	}
	@DeleteMapping("/posts/{postId}/comments/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable Integer postId, @PathVariable Integer commentId) {
		commentService.deleteComment(postId, commentId);

		return new ResponseEntity<>(AppConstants.DELETE_SUCCESS_MSG+" "+commentId, HttpStatus.OK);
	}
	
}
