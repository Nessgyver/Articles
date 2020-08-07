package com.example.articles.view_model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.articles.bo.Article;
import com.example.articles.repository.ArticleRepositoryFactory;
import com.example.articles.repository.IArticleRepository;

import java.util.List;

public class ArticleViewModel extends AndroidViewModel
{
    private IArticleRepository repo;

    private LiveData<List<Article>> obsArticles;

    public ArticleViewModel(@NonNull Application application)
    {
        super(application);
        repo = ArticleRepositoryFactory.getArticleRepository(application);
        obsArticles = repo.get();
    }

    public LiveData<List<Article>> get()
    {
        return obsArticles;
    }

    public void insert(Article article)
    {
        repo.insert(article);
    }

    public void insert(Article ... articles)
    {
        repo.insert(articles);
    }

    public LiveData<Article> get(int id)
    {
        return repo.get(id);
    }

    public void update(Article article)
    {
        repo.update(article);
    }

    public void delete(Article article)
    {
        repo.delete(article);
    }

    public void delete()
    {
        repo.delete();
    }
}
