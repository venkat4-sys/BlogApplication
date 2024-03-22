package com.ait.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ait.entity.PostEntity;

public interface PostRepository extends JpaRepository<PostEntity, Integer>{

}
