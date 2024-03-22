package com.ait.service;

import java.util.List;

import com.ait.dto.CommentDto;

public interface CommentService {
	
	CommentDto createComment(Integer postId,CommentDto commentDto);
	List<CommentDto> getAllCommentsByPostId(Integer postId);
	CommentDto getCommentByPodtId(Integer postId,Integer commentId);
	CommentDto updateComment(Integer postId,Integer commentId,CommentDto commentrequest);
	
	void deleteComment(Integer postId,Integer commentId);

}
