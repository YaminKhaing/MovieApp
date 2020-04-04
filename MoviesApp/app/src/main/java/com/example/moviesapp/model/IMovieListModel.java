package com.example.moviesapp.model;

import com.example.moviesapp.util.ServiceHelper;

import io.reactivex.Observable;

public interface IMovieListModel {

    Observable<MovieListModel> getNowShowingMoviesFromApi(ServiceHelper.ApiService service, int page);

    Observable<MovieListModel> getLatestMoviesFromApi(ServiceHelper.ApiService service, int page);

    Observable<MovieListModel> getPopularMoviesfromApi(ServiceHelper.ApiService service, int page);

    Observable<MovieListModel> getTopRatedMoviesFromApi(ServiceHelper.ApiService service, int page);

    Observable<MovieListModel> getUpcomingMoviesFromApi(ServiceHelper.ApiService service, int page);

    Observable<MovieListModel> getSimilarMoviesFromApi(int movieId, ServiceHelper.ApiService service, int page);

    Observable<MovieListModel> getRatedMoviesFromApi(int accountId, ServiceHelper.ApiService service, String sessionId);

    Observable<MovieListModel> getWatchListMoviesFromApi(int accountId, ServiceHelper.ApiService service, String sessionId);

}
