package com.example.articles.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.articles.bo.Article;
import com.example.articles.dal.AppDatabase;
import com.example.articles.dal.ArticleDAO;

import java.util.List;

public class ArticleBddRepository implements IArticleRepository
{
    private List<Article> resultat = null;
    private ArticleDAO daoArticle;

    public ArticleBddRepository(Context context)
    {
        //Instance de ma Bdd
        AppDatabase maBdd = AppDatabase.getInstance(context);

        //Instance de la DAO Article
        daoArticle = maBdd.getArticleDAO();
    }

    @Override
    public void insert(Article article) {
        new AsyncTask<Article, Void, Void>()
        {
            @Override
            protected Void doInBackground(Article... articles) {
                daoArticle.insert(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void insert(Article... article) {
        new AsyncTask<Article, Void, Void>()
        {
            @Override
            protected Void doInBackground(Article... articles) {
                daoArticle.insert(articles);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void update(Article article) {
        new AsyncTask<Article, Void, Void>()
        {
            @Override
            protected Void doInBackground(Article... articles) {
                daoArticle.update(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void delete(Article article) {
        new AsyncTask<Article, Void, Void>()
        {
            @Override
            protected Void doInBackground(Article... articles) {
                daoArticle.delete(articles[0]);
                return null;
            }
        }.execute(article);
    }

    @Override
    public void delete() {
        new AsyncTask<Void, Void, Void>()
        {
            @Override
            protected Void doInBackground(Void... voids) {
                daoArticle.insert();
                return null;
            }
        }.execute();
    }

    @Override
    public LiveData<List<Article>> get() {
        return daoArticle.get();
    }

    @Override
    public LiveData<Article> get(int id) {
        return daoArticle.get(id);
    }
}
