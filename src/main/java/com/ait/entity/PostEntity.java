package com.ait.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name = "post_tbl")
public class PostEntity {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer postId;

	private String title;

	private String description;

	private String content;
	@OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
	private List<Comment> comment;
}
