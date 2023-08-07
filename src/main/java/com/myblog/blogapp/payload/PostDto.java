package com.myblog.blogapp.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

@Data
public class PostDto {

    private long id;
    @NotNull
    @Size(min = 2, message = "post description should have atleast 10 character or more")
    private String title;

    @Null
    @Size(min = 10, message = "post description should have atleast 10 character or more")
    private String description;

    @Null
    private String content;

}
