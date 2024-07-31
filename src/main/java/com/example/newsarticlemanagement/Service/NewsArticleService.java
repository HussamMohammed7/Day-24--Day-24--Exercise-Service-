package com.example.newsarticlemanagement.Service;


import com.example.newsarticlemanagement.Model.NewsArticle;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class NewsArticleService {

    ArrayList<NewsArticle> newsArticles = new ArrayList<>();


    public ArrayList<NewsArticle> getNewsArticles() {
        return newsArticles;
    }

    public ArrayList<NewsArticle> getPublishedNewsArticles() {
        ArrayList<NewsArticle> newsArticlesNew = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.isPublished()){
                newsArticlesNew.add(newsArticle);
            }
        }
        return newsArticlesNew;
    }

    public void addNewsArticle(NewsArticle newsArticle) {
        newsArticles.add(newsArticle);
    }



    public Boolean updateNewsArticle(NewsArticle newsArticle , String id) {
        if (newsArticles.isEmpty()){
            return false;
        }
        for (int i = 0; i < newsArticles.size(); i++) {
            if (newsArticles.get(i).getId().equals(id)){
                newsArticles.set(i,newsArticle);
                return true;
            }
        }
        return false;
    }

    public boolean deleteNewsArticle( String id ) {

        if (newsArticles.isEmpty()){
            return false;
        }
        for (int i = 0; i < newsArticles.size() ; i++) {

            if (newsArticles.get(i).getId().equals(id)){
                newsArticles.remove(i);
                return true;
            }

        }
        return false;

    }

    public boolean publishNewsArticle(String id) {
        if (newsArticles.isEmpty()){
            return false;

        }
        for (int i = 0; i < newsArticles.size() ; i++) {
            if (newsArticles.get(i).getId().equals(id)){
                newsArticles.get(i).setPublished(true);
                newsArticles.get(i).setPublishDate(LocalDate.now());
                return true;
            }
        }
        return false;


    }

    public ArrayList<NewsArticle> getNewsArticlesByCategory(String cat) {
        ArrayList<NewsArticle> newsArticlesCat = new ArrayList<>();
        for (NewsArticle newsArticle : newsArticles) {
            if (newsArticle.getCategory().equals(cat)){
                newsArticlesCat.add(newsArticle);
            }
        }
        return newsArticlesCat;
    }








}
