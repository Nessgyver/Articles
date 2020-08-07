package com.example.articles.repository;

import androidx.lifecycle.LiveData;

import com.example.articles.bo.Article;

import java.util.List;

/**
 * Interface permettant de mettre en place le design pattern DAO
 */
public interface IArticleRepository
{
    void insert(Article article);
    void insert(Article ... article);
    void update(Article article);
    void delete(Article article);
    void delete();
    LiveData<List<Article>> get();
    LiveData<Article> get(int id);
}
