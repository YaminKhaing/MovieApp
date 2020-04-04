package com.example.moviesapp.model;

import com.example.moviesapp.common.Pageable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MovieListModel implements Serializable, Pageable {

    int page;
    int total_results;
    int total_pages;
    ArrayList<MovieInfoModel> results;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public ArrayList<MovieInfoModel> getResults() {
        return results;
    }

    public void setResults(ArrayList<MovieInfoModel> results) {
        this.results = results;
    }
}
