package com.springboot.blog.springbootblogrestapi.paylod;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(description = "Comment model information")
@Data
public class CommentDto {

	@ApiModelProperty(value = "Comment Id")
	private long id;

	@ApiModelProperty(value = "Comment name")
	@NotEmpty(message = "name should not be null or empty")
	private String name;

	@ApiModelProperty(value = "Comment email")
	@NotEmpty(message = "email should not be null or empty")
	@Email
	private String email;

	@ApiModelProperty(value = "Comment body")
	@NotEmpty
	@Size(min = 10, message = "Comment body must be minimum 10 characters")
	private String body;

}
