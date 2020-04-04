package com.example.sampleapp.model;

import com.example.sampleapp.util.ServiceHelper;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import static com.example.sampleapp.util.AppConstant.DEVELOPER_KEY;

public class MovieListModelImpl implements IMovieListModel{

    @Override
    public Observable<MovieListModel> getNowShowingMoviesFromApi(ServiceHelper.ApiService service, int page) {
        return service.getNowShowingMovies(DEVELOPER_KEY, "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieListModel> getLatestMoviesFromApi(ServiceHelper.ApiService service, int page) {
        return service.getLatestMovies(DEVELOPER_KEY,"en-US")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieListModel> getPopularMoviesfromApi(ServiceHelper.ApiService service, int page) {
        return service.getPopularMovies(DEVELOPER_KEY, "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieListModel> getTopRatedMoviesFromApi(ServiceHelper.ApiService service, int page) {
        return service.getTopRatedMovies(DEVELOPER_KEY, "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieListModel> getUpcomingMoviesFromApi(ServiceHelper.ApiService service, int page) {
        return service.getUpcomingMovies(DEVELOPER_KEY, "en-US", page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<MovieListModel> getSimilarMoviesFromApi(int movieId, ServiceHelper.ApiService service, int page) {
        return service.getSimilarMovies(movieId, DEVELOPER_KEY, "en-US", page);
    }

    @Override
    public Observable<MovieListModel> getRatedMoviesFromApi(int accountId, ServiceHelper.ApiService service, String sessionId) {
//        return service.getRatedMovies(accountId, DEVELOPER_KEY, "en-US", sessionId);
        return null;
    }

    @Override
    public Observable<MovieListModel> getWatchListMoviesFromApi(int accountId, ServiceHelper.ApiService service, String sessionId) {
        return null;
    }

}
