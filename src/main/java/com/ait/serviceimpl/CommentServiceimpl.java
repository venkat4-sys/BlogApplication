package com.ait.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ait.dto.CommentDto;
import com.ait.entity.Comment;
import com.ait.entity.PostEntity;
import com.ait.exception.PostNotFoundException;
import com.ait.repository.CommentRepository;
import com.ait.repository.PostRepository;
import com.ait.service.CommentService;
import com.ait.utils.AppConstants;

@Service
public class CommentServiceimpl implements CommentService {

	@Autowired
	private CommentRepository commentRepo;
	
	@Autowired
	private PostRepository postRepo;
	
	@Autowired
	private ModelMapper mapper;

	@Override
	public CommentDto createComment(Integer postId, CommentDto commentDto) {
		
		Comment entity = maptoEntity(commentDto);
		Optional<PostEntity> post = postRepo.findById(postId);
		if(post.isPresent()) {
			PostEntity postEntity = post.get();
			
			entity.setPost(postEntity);
			Comment commentEntity = commentRepo.save(entity);
			return maptoDto(commentEntity);
			
		}
		throw new PostNotFoundException(AppConstants.POST_NOT_FOUND + postId);
	}

	private Comment maptoEntity(CommentDto commentDto) {

		
		Comment comment = mapper.map(commentDto, Comment.class);
		/*
		Comment comment = new Comment();
		comment.setBody(commentDto.getBody());
		comment.setEmail(commentDto.getEmail());
		comment.setName(commentDto.getName());
          */
		return comment;
	}

	private CommentDto maptoDto(Comment comments) {
        
		CommentDto commentDto = mapper.map(comments, CommentDto.class);
		/*
		CommentDto comment = new CommentDto();
		comment.setBody(comments.getBody());
		comment.setEmail(comments.getEmail());
		comment.setName(comments.getName());
		comment.setId(comments.getId());
       */
		return commentDto;
	}
	
	@Override
	public List<CommentDto> getAllCommentsByPostId(Integer postId) {
		 List<Comment> comments = commentRepo.findByPostPostId(postId);
		List<CommentDto> listOfComments = comments.stream().map(comment->maptoDto(comment)).collect(Collectors.toList());
		return listOfComments;
	}

	@Override
	public CommentDto updateComment(Integer postId,Integer commentId,CommentDto commentrequest) {
		Optional<PostEntity> post = postRepo.findById(postId);
		if(post.isPresent()) {
			PostEntity postEntity = post.get();
			Optional<Comment> comment = commentRepo.findById(commentId);
			if(comment.isPresent()) {
				Comment entity = comment.get();
				entity.setBody(commentrequest.getBody());
				entity.setEmail(commentrequest.getEmail());
				entity.setName(commentrequest.getName());
				commentRepo.save(entity);
				return maptoDto(entity);
		
	             }
			throw new PostNotFoundException(AppConstants.POST_NOT_FOUND + postId);
		}
		return null;
	}



	@Override
	public CommentDto getCommentByPodtId(Integer postId, Integer commentId) {
		 Optional<PostEntity> post = postRepo.findById(postId);
		    if (post.isPresent()) {
		        PostEntity postEntity = post.get();
		        Optional<Comment> comment = commentRepo.findById(commentId);
		        if (comment.isPresent()) {
		            Comment entity = comment.get();
		            return maptoDto(entity);
		        }
		        throw new PostNotFoundException(AppConstants.POST_NOT_FOUND + postId);
		    }
		return null;
	}
	@Override
	public void deleteComment(Integer postId, Integer commentId) {
		 Optional<PostEntity> post = postRepo.findById(postId);
		    if (post.isPresent()) {
		        PostEntity postEntity = post.get();
		        Optional<Comment> comment = commentRepo.findById(commentId);
		        if (comment.isPresent()) {
		        	Comment entity = comment.get();
		        	commentRepo.delete(entity);
		         }
		        
		    }else {
		    	throw new PostNotFoundException(AppConstants.POST_NOT_FOUND + postId);	
		    }
		    
		
	}
}