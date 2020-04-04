package com.example.moviesapp.model;

public class AddWatchListRequestBody {
    String media_type;
    int media_id;
    boolean watchlist;

    public AddWatchListRequestBody(String media_type, int media_id, boolean watchlist){
        this.media_type=media_type;
        this.media_id=media_id;
        this.watchlist=watchlist;
    }
}
