package com.example.articles.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.articles.R;
import com.example.articles.activity.adapter.ArticleAdapter;
import com.example.articles.bo.Article;
import com.example.articles.view_model.ArticleViewModel;

import java.util.List;

public class ListeArticleActivity extends AppCompatActivity {

    private ListView listeUtilisateur = null;
    private ArticleViewModel avm = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_article);

        listeUtilisateur = findViewById(R.id.list_article);
    }

    @Override
    protected void onResume() {
        super.onResume();

        avm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        final LiveData<List<Article>> obsArticle = avm.get();

        obsArticle.observe(this, new Observer<List<Article>>() {
            @Override
            public void onChanged(List<Article> articles) {

                ArticleAdapter adapArticle = new ArticleAdapter(ListeArticleActivity.this, R.layout.style_ligne_article, articles);
                listeUtilisateur.setAdapter(adapArticle);
            }
        });
    }

    public void onClickAfficher(View v){
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Article article = (Article)listeUtilisateur.getItemAtPosition(position);

        //envoyer vers la page d'affichage
        Intent intent = new Intent(this, AfficherArticleActivity.class);
        intent.putExtra("article",article);
        startActivity(intent);
    }

    public void onClickModifier(View v){
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Article article = (Article)listeUtilisateur.getItemAtPosition(position);

        //to do : envoyer vers la page de modification
        Intent intent = new Intent(this, UpdateArticleActivity.class);
        intent.putExtra("article", article);
        startActivity(intent);
    }

    public void onClickSupprimer(View v){
        View parentRow = (View) v.getParent();
        ListView listView = (ListView) parentRow.getParent();
        final int position = listView.getPositionForView(parentRow);
        Article article = (Article)listeUtilisateur.getItemAtPosition(position);
        avm.delete(article);
        Toast.makeText(this, "l'article " + article.getNom() + " a été supprimé", Toast.LENGTH_SHORT).show();
    }
}