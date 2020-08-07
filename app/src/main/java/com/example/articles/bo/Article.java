package com.example.articles.bo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * Classe représentant un article
 */
@Entity
public class Article implements Parcelable
{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nom;
    private double prix;
    private String description;
    private String siteWeb;
    private boolean achete;
    private int note;

    public Article(int id, String nom, double prix, String description, String siteWeb, boolean achete, int note) {
        this.id = id;
        this.nom = nom;
        this.prix = prix;
        this.description = description;
        this.siteWeb = siteWeb;
        this.achete = achete;
        this.note = note;
    }

    protected Article(Parcel in) {
        id = in.readInt();
        nom = in.readString();
        prix = in.readDouble();
        description = in.readString();
        siteWeb = in.readString();
        achete = in.readByte() != 0;
        note = in.readInt();
    }

    public static final Creator<Article> CREATOR = new Creator<Article>() {
        @Override
        public Article createFromParcel(Parcel in) {
            return new Article(in);
        }

        @Override
        public Article[] newArray(int size) {
            return new Article[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSiteWeb() {
        return siteWeb;
    }

    public void setSiteWeb(String siteWeb) {
        this.siteWeb = siteWeb;
    }

    public boolean isAchete() {
        return achete;
    }

    public void setAchete(boolean achete) {
        this.achete = achete;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "Article{" +
                "id="+ id +
                "nom='" + nom + '\'' +
                ", prix=" + prix +
                ", description='" + description + '\'' +
                ", siteWeb='" + siteWeb + '\'' +
                ", achete=" + achete +
                ", note=" + note +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(nom);
        parcel.writeDouble(prix);
        parcel.writeString(description);
        parcel.writeString(siteWeb);
        parcel.writeByte((byte) (achete ? 1 : 0));
        parcel.writeInt(note);
    }
}
