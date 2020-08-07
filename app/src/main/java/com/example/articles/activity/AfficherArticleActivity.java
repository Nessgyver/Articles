package com.example.articles.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.articles.R;
import com.example.articles.activity.adapter.ArticleAdapter;
import com.example.articles.bo.Article;

import java.util.ArrayList;
import java.util.List;

public class AfficherArticleActivity extends AppCompatActivity {

    private ListView listeArticle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afficher_article);

        listeArticle = findViewById(R.id.list_article_unitaire);
    }

    @Override
    protected void onResume() {
        super.onResume();

        Intent intent = getIntent();
        Article article = intent.getParcelableExtra("article");
        List<Article> articles = new ArrayList<Article>();
        articles.add(article);
        ArticleAdapter adapArticle = new ArticleAdapter(AfficherArticleActivity.this, R.layout.style_ligne_article, articles);
        listeArticle.setAdapter(adapArticle);

        Button btnSiteWeb = findViewById(R.id.btn_site_web);
        ToggleButton tbIsAchete = findViewById(R.id.tb_achete);

        tbIsAchete.setChecked(article.isAchete());
    }
}