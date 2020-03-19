package com.example.moviesapp.util;

import com.example.moviesapp.model.LoginRequestBody;
import com.example.moviesapp.model.MovieInfoModel;
import com.example.moviesapp.model.MovieListModel;
import com.example.moviesapp.model.ProfileInfoModel;
import com.example.moviesapp.model.SessionRequestBody;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class ServiceHelper {

    public interface ApiService{

        @GET("authentication/token/new")
        Observable<ProfileInfoModel> getRequestToken(@Query("api_key") String apiKey);

        @POST("authentication/token/validate_with_login")
        Observable<ProfileInfoModel> getLoginValidate(@Query("api_key") String apiKey,
                                                      @Body LoginRequestBody loginRequestBody);

        @POST("authentication/session/new")
        Observable<ProfileInfoModel> getSession(@Query("api_key") String apiKey,
                                                @Body SessionRequestBody sessionRequestBody);

        @GET("account")
        Observable<ProfileInfoModel> getAccountDetail(@Query("api_key") String apiKey,
                                                      @Query("session_id") String sessionId);

        @GET("movie/{movie_id}")
        Observable<MovieInfoModel> getMovieDetail(@Path("movie_id") int movieId,
                                                  @Query("api_key") String apiKey,
                                                  @Query("language") String language);

        @GET("movie/{movie_id}/similar")
        Observable<MovieListModel> getSimilarMovies(@Path("movie_id") int movieId,
                                                    @Query("api_key") String apiKey,
                                                    @Query("language") String language,
                                                    @Query("page") int page);

        @GET("account/{account_id}/rated/movies")
        Observable<ProfileInfoModel> getRatedMovies(@Path("account_id") int accountId,
                                                    @Query("api_key") String apiKey,
                                                    @Query("language") String language,
                                                    @Query("session_id") String sessionId);

        @GET("account/{account_id}/watchlist/movies")
        Observable<ProfileInfoModel> getWatchListMovies(@Path("account_id") int accountId,
                                                        @Query("api_key") String apiKey,
                                                        @Query("language") String language,
                                                        @Query("session_id") String sessionId);

        @POST("account/{account_id}/watchlist")
        Observable<ProfileInfoModel> getAddToWatchList(@Path("account_id") int accountId,
                                                       @
                                                       )
    }
}
