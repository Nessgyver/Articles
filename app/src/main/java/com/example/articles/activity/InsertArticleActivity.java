package com.example.articles.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import com.example.articles.R;
import com.example.articles.bo.Article;
import com.example.articles.repository.ArticleRepositoryFactory;
import com.example.articles.repository.IArticleRepository;
import com.example.articles.view_model.ArticleViewModel;

import java.util.List;

public class InsertArticleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_article);
    }

    public void onClickAjouter(View view) {
        //récupère les champs du formulaire
        EditText etNom = findViewById(R.id.et_nom);
        EditText etDescription = findViewById(R.id.et_desc);
        EditText etSiteWeb = findViewById(R.id.et_siteWeb);
        EditText etPrix= findViewById(R.id.et_prix);
        RatingBar etNote= findViewById(R.id.et_note);
        ToggleButton tbIsAchete= findViewById(R.id.et_is_achete);

        //les transforme en variables exploitable par le constructeur d'article
        String nom = etNom.getText().toString();
        String description = etDescription.getText().toString();
        String siteWeb = etSiteWeb.getText().toString();
        double prix = Double.valueOf(etPrix.getText().toString());
        int note = (int)etNote.getRating();
        boolean isAchete = tbIsAchete.isChecked();

        //créé un article pour pouvoir l'ajouter à la base de données
        Article article = new Article(0, nom, prix, description, siteWeb, isAchete, note );

        // l'ajoute à la base de données
        IArticleRepository articleRepository = ArticleRepositoryFactory.getArticleRepository(this);
        articleRepository.insert(article);

        //fait apparaitre la liste mise à jour dans les logs
        ArticleViewModel articleViewModel = ViewModelProviders.of(this).get(ArticleViewModel.class);
        LiveData<List<Article>> observateurArticle = articleViewModel.get();

        observateurArticle.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {
                for (Article article : articles)
                {
                    Log.i("ça marche", "article : " + article);
                }
            }
        });
    }
}