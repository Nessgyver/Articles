package com.example.articles.dal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.articles.bo.Article;

import java.util.List;

/**
 * Cette classe permet de donner les consignes nécessaires à Room
 * pour qu'il puisse créer la dao pour la table article
 */
@Dao
public interface ArticleDAO
{
    @Insert
    void insert(Article article);

    @Insert
    void insert(Article ... article);

    @Update
    void update(Article article);

    @Delete
    void delete(Article article);

    @Query("DELETE FROM Article")
    void delete();

    @Query("SELECT * FROM Article")
    LiveData<List<Article>> get();

    @Query("SELECT * FROM Article WHERE id = :id")
    LiveData<Article> get(int id);
}
