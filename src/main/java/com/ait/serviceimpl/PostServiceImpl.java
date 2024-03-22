package com.ait.serviceimpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ait.dto.PostDto;
import com.ait.dto.PostResponse;
import com.ait.entity.Comment;
import com.ait.entity.PostEntity;
import com.ait.exception.PostNotFoundException;
import com.ait.repository.CommentRepository;
import com.ait.repository.PostRepository;
import com.ait.service.PostService;
import com.ait.utils.AppConstants;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private PostRepository postrepo;
	
	@Autowired
	private ModelMapper mapper;
	
	@Autowired
	private CommentRepository commentRepo;

	@Override
	public PostDto createPost(PostDto postdto) {
		
		PostEntity postEntity = mapToentity(postdto); 
		PostEntity post = postrepo.save(postEntity);
		PostDto postDto = mapToDto(post);
		return postDto;
	}
	private PostDto mapToDto(PostEntity entity) {
		
		PostDto post = mapper.map(entity, PostDto.class);
		/*
		PostDto postDto=new PostDto();
		postDto.setContent(entity.getContent());
		postDto.setDescription(entity.getDescription());
		postDto.setTitle(entity.getTitle());
		postDto.setPostId(entity.getPostId());
		*/
		return post;
	}
	
     private PostEntity mapToentity(PostDto postDto) {
    	 PostEntity postEntity = mapper.map(postDto, PostEntity.class);
    	 /*
    	 PostEntity entity=new PostEntity();
 		entity.setContent(postDto.getContent());
 		entity.setDescription(postDto.getDescription());
 		entity.setTitle(postDto.getTitle());
 		*/
 		return postEntity;
 		
	}
	

	@Override
	public PostDto getPostById(Integer id) {
		Optional<PostEntity> post = postrepo.findById(id);
		if(post.isPresent()) {
			PostEntity postEntity = post.get(); 
			PostDto postDto = mapToDto(postEntity);
			return postDto; 
		}
		throw new PostNotFoundException(AppConstants.POST_NOT_FOUND + id);
	}

	@Override
	public List<PostDto> getAllPosts() {
		List<PostEntity> posts = postrepo.findAll();
		List<PostDto> postDto = posts.stream().map(post->mapToDto(post)).collect(Collectors.toList());
		return postDto;
	}

	@Override
	public PostDto updatePost(Integer id, PostDto postdto) {
		Optional<PostEntity> post = postrepo.findById(id);
		if(post.isPresent()) {
		PostEntity postEntity = post.get();
		postEntity.setContent(postdto.getContent());
		postEntity.setDescription(postdto.getDescription());
		postEntity.setTitle(postdto.getTitle());
		PostEntity entity = postrepo.save(postEntity);
		PostDto postDto = mapToDto(entity);
		return postDto;
		}
		throw new PostNotFoundException(AppConstants.POST_NOT_FOUND + id);
	}

	@Override
	public String deletePostById(Integer id) {
		Optional<PostEntity> post = postrepo.findById(id);
		if(post.isPresent()) {
			PostEntity postEntity = post.get();	
			postrepo.deleteById(postEntity.getPostId());
			return AppConstants.MESSAGE+" "+id;
		}
		throw new PostNotFoundException(AppConstants.POST_NOT_FOUND + id);
	}
	@Override
	public PostResponse getAllPostPagination(int pageSize, int pageNumber,String sortBy) {
		
		PageRequest page = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
		
		 Page<PostEntity> pagePosts = postrepo.findAll(page);
		 
		 List<PostEntity> content = pagePosts.getContent();
		 List<PostDto> listofPosts = content.stream().map(post->mapToDto(post)).collect(Collectors.toList());
		
		 PostResponse response=new PostResponse();
		 
		 response.setContent(listofPosts);
		 response.setPageNumber(pagePosts.getNumber());
		 response.setPageSize(pagePosts.getSize());
		 response.setTotalElements(pagePosts.getNumberOfElements());
		 response.setTotalPages(pagePosts.getTotalPages());
		 response.setLast(pagePosts.isLast());
		return response;
	}

}
