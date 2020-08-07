package com.example.articles.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.articles.R;
import com.example.articles.bo.Article;
import com.example.articles.view_model.ArticleViewModel;

public class UpdateArticleActivity extends AppCompatActivity {

    private Article article = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_article);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(article == null){
            Intent intent = getIntent();
            Article articleAModifier = intent.getParcelableExtra("article");
            if (articleAModifier == null){
                redirectToHome();
            }else{
                article = articleAModifier;
            }
        }
        EditText etNom = findViewById(R.id.et_nom);
        EditText etPrix = findViewById(R.id.et_prix);
        EditText etDesc = findViewById(R.id.et_desc);
        EditText etSiteWeb = findViewById(R.id.et_siteWeb);
        RatingBar rbNote = findViewById(R.id.rb_note);
        ToggleButton tbIsAchete = findViewById(R.id.tb_is_achete);

        etNom.setText(article.getNom());
        etPrix.setText(String.valueOf(article.getPrix()));
        etDesc.setText(article.getDescription());
        etSiteWeb.setText(article.getSiteWeb());
        rbNote.setRating((int)article.getNote());
        tbIsAchete.setChecked(article.isAchete());

    }

    @Override
    protected void onPause() {
        super.onPause();
        changeArticle();
    }



    /**
     * méthode permettant de mettre à jour l'attribut article de la classe
     * pouvant être utilisée dans différentes méthodes de cette même classe
     */
    private void changeArticle() {
        //récupère les champs du formulaire
        EditText etNom = findViewById(R.id.et_nom);
        EditText etDescription = findViewById(R.id.et_desc);
        EditText etSiteWeb = findViewById(R.id.et_siteWeb);
        EditText etPrix= findViewById(R.id.et_prix);
        RatingBar etNote= findViewById(R.id.rb_note);
        ToggleButton tbIsAchete= findViewById(R.id.tb_is_achete);

        //les transforme en variables exploitable par le constructeur d'article
        String nom = etNom.getText().toString();
        String description = etDescription.getText().toString();
        String siteWeb = etSiteWeb.getText().toString();
        double prix = Double.valueOf(etPrix.getText().toString());
        int note = (int)etNote.getRating();
        boolean isAchete = tbIsAchete.isChecked();

        Log.i("wololo",article.toString());
        article.setNom(nom);
        article.setDescription(description);
        article.setSiteWeb(siteWeb);
        article.setPrix(prix);
        article.setNote(note);
        article.setAchete(isAchete);
    }

    public void onClickUpdate(View view) {
        changeArticle();

        // le met à jour dans la base de données
        ArticleViewModel avm = ViewModelProviders.of(this).get(ArticleViewModel.class);
        avm.update(article);
        Log.i("wololol", article.toString());
        article = null;

        // rediriger vers la page d'accueil
        redirectToHome();
    }

    private void redirectToHome() {
        Intent redir = new Intent(this, ListeArticleActivity.class);
        startActivity(redir);
    }
}