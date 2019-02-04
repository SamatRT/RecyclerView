package com.example.user.recyclerview;

public class Song {

    String title, author;
    String music_photo;
    String PrimeCupletSource, PrimePripevSource, LastTextSource;
    private int mImageResource;

    public Song(int ImageResource, String title, String author, String PrimeCupletSource, String PrimePripevSource, String LastTextSource) {
        this.mImageResource = ImageResource;
        this.title = title;
        this.author = author;
        this.PrimeCupletSource = PrimeCupletSource;
        this.PrimePripevSource = PrimePripevSource;
        this.LastTextSource = LastTextSource;
    }

    public int getmImageResource() { return mImageResource;}

    public void setmImageResource(int mImageResource) { this.mImageResource = mImageResource;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }


    public String getPrimeCuplet() {
        return PrimeCupletSource;
    }

    public void setPrimeCuplet(String PrimeCuplet) {this.PrimeCupletSource = PrimeCuplet;}

    public String getPrimePripev() {
        return PrimePripevSource;
    }

    public void setPrimePripev(String PrimePripev) {
        this.PrimePripevSource = PrimePripev;
    }

    public String getLastText() { return LastTextSource;}

    public void setLastText(String LastText) {this.LastTextSource = LastText;}

    public String getmImages() { return LastTextSource;}

    public void setmImages(String LastText) { this.LastTextSource = LastText;}



}
