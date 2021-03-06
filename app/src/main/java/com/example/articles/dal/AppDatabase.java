package com.example.articles.dal;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.articles.bo.Article;

/**
 * Cette classe représente la base de données de l'application
 */

@Database(entities = {Article.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase  INSTANCE;

    public abstract ArticleDAO getArticleDAO();

    public static AppDatabase getInstance(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context, AppDatabase.class, "article.db").build();
        }
        return INSTANCE;
    }
}
