package com.example.newsarticlemanagement.Controller;


import com.example.newsarticlemanagement.Api.ApiResponse;
import com.example.newsarticlemanagement.Model.NewsArticle;
import com.example.newsarticlemanagement.Service.NewsArticleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/news-article")
@RequiredArgsConstructor
public class NewsArticleController {


    private final NewsArticleService newsArticleService;



    @GetMapping("/get")
    public ResponseEntity getNewsArticle() {

        return ResponseEntity.status(HttpStatus.OK).body(
                newsArticleService.getNewsArticles()
        );
    }

    @PostMapping("/add")
    public ResponseEntity addNewsArticle(@Valid @RequestBody NewsArticle newsArticle , Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }
        newsArticleService.addNewsArticle(newsArticle);
        return ResponseEntity.status(HttpStatus.OK).body(
                new ApiResponse("Successfully added new article")
        );

    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateNewsArticle(@PathVariable String id ,@Valid @RequestBody NewsArticle newsArticle, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    errors.getFieldError().getDefaultMessage()
            );
        }

        boolean isUpdated = newsArticleService.updateNewsArticle(newsArticle,id);

        if (isUpdated){

            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("Successfully updated new article")
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse("Id not found ")
        );

    }


    @DeleteMapping ("/delete/{id}")

    public ResponseEntity deleteNews(@PathVariable String id){

        boolean isDeleted = newsArticleService.deleteNewsArticle(id);
        if(isDeleted){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("newsArticle deleted successfully"
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse("newsArticle not found")
        );
    }

    @PutMapping ("/publish/{id}")

    public ResponseEntity publishNewsArticle(@PathVariable String id) {

        boolean isPublished = newsArticleService.publishNewsArticle(id);
        if(isPublished){
            return ResponseEntity.status(HttpStatus.OK).body(
                    new ApiResponse("newsArticle published successfully"
                    )
            );
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ApiResponse("newsArticle not found")
        );
    }

    @GetMapping("/publish/getAll")
    public ResponseEntity getAllPublishedNewsArticles() {
        ArrayList<NewsArticle> newArray=newsArticleService.getPublishedNewsArticles();
        if (newArray.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(" newsArticles are empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(
                newArray
        );
    }
    @GetMapping("/get/category/{category}")
    public ResponseEntity getCategory(@PathVariable String category) {
        ArrayList<NewsArticle> newArray=newsArticleService.getNewsArticlesByCategory(category);
        if (!category.equalsIgnoreCase("politics")&&!category.equalsIgnoreCase("sports")&&!category.equalsIgnoreCase("technology")){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ApiResponse("category not found")
            );
        }

        if (newArray.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ApiResponse(" newsArticles category are empty")
            );
        }

        return ResponseEntity.status(HttpStatus.OK).body(

                newArray
        );
    }
















}
