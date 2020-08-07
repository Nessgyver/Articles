package com.example.articles.activity.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.articles.R;
import com.example.articles.bo.Article;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {
    /**
     * constructeur de la Classe
     * @param context
     * @param resource
     * @param objects
     */
    public ArticleAdapter(@NonNull Context context, int resource, @NonNull List<Article> objects) {
        super(context, resource, objects);
    }

    /**
     * Fonction qui permet de créer chaque ligne d'article pour les insérer dans la vue correspondante
     * @param position
     * @param nouvelleLigne
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View nouvelleLigne, @NonNull ViewGroup parent) {

        if(nouvelleLigne == null){
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            nouvelleLigne = li.inflate(R.layout.style_ligne_article, parent, false);
        }

        TextView tvNom = nouvelleLigne.findViewById(R.id.tv_nom_article);
        TextView tvPrix = nouvelleLigne.findViewById(R.id.tv_prix_article);
        TextView tvDesc = nouvelleLigne.findViewById(R.id.tv_desc_article);
        RatingBar rbNote = nouvelleLigne.findViewById(R.id.rb_note_article);

        tvNom.setText(getItem(position).getNom());
        tvPrix.setText(String.valueOf(getItem(position).getPrix()));
        tvDesc.setText(getItem(position).getDescription());
        rbNote.setNumStars(4);
        rbNote.setRating(getItem(position).getNote());

        return nouvelleLigne;
    }
}
