package com.example.newsarticlemanagement.Model;


import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NewsArticle {

    @NotEmpty(message = "ID cant be empty")
    private String id;

    @NotEmpty(message = "title cant be empty")
    @Size(max = 100, message = "Max size of title is 100 char")
    private String title;

    @NotEmpty(message = "author cant be empty")
    @Size(min = 4, max = 20, message = "Max size of author is 20 and the min is 4char")
    private String author;


    @NotEmpty(message = "content cant be empty")
    @Size(min = 200 , message = "Must be more then 200 char")
    private String content;

    @NotEmpty(message = "category cant be empty")
    @Pattern(regexp = "politics|sports|technology", message = "Content should be only one of these politics|sports|technology ")
    private String category;

    @NotEmpty(message = "imageUrl cant be author")
    private String imageUrl;

    @AssertFalse(message = "isPublished must be false")
    private boolean isPublished;

    private LocalDate publishDate;


}
