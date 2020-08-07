package com.example.articles.repository;

import android.content.Context;

public abstract class ArticleRepositoryFactory
{

    public static IArticleRepository getArticleRepository(Context context) {
        return new ArticleBddRepository(context);
    }
}
