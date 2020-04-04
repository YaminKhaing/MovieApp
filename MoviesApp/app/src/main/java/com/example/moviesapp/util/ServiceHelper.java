package com.example.moviesapp.util;

import android.content.Context;

import com.example.moviesapp.model.AddWatchListRequestBody;
import com.example.moviesapp.model.LoginRequestBody;
import com.example.moviesapp.model.MovieInfoModel;
import com.example.moviesapp.model.MovieListModel;
import com.example.moviesapp.model.ProfileInfoModel;
import com.example.moviesapp.model.RatingMoviesRequestBody;
import com.example.moviesapp.model.SessionRequestBody;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

import static com.example.moviesapp.util.AppConstant.BASE_URL;

public class ServiceHelper {

    private static ApiService apiService;
    private static Cache cache;

    public static ApiService getClient(final Context context) {

        if (apiService == null) {

            Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Response originalResponse = chain.proceed(chain.request());
                    int maxAge = 300; // read from cache for 5 minute
                    return originalResponse.newBuilder()
                            .header("Cache-Control", "public, max-age=" + maxAge)
                            .build();
                }
            };

            //setup cache
            File httpCacheDirectory = new File(context.getCacheDir(), "responses");
            int cacheSize = 10 * 1024 * 1024; // 10 MiB
            cache = new Cache(httpCacheDirectory, cacheSize);

            final OkHttpClient.Builder okClientBuilder = new OkHttpClient.Builder();
            okClientBuilder.addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);
            // Add other Interceptors
            okClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request request = original.newBuilder()
//                            .header("Authorization", token_type + " " + access_token)
                            .method(original.method(), original.body())
                            .build();


                    Response response =  chain.proceed(request);

                    if (response.code() == 401){
                        // Magic is here ( Handle the error as your way )
                        return response;
                    }
                    return response;
                }
            });
            okClientBuilder.cache(cache);

            OkHttpClient okClient = okClientBuilder.build();

            Retrofit client = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            apiService = client.create(ApiService.class);
        }
        return apiService;
    }

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
                                                       @Header("Content-Type") String contentType,
                                                       @Query("api_key") String apiKey,
                                                       @Query("session_id") String sessionId,
                                                       @Body AddWatchListRequestBody addWatchListRequestBody);

        @POST("movie/{movie_id}/rating")
        Observable<ProfileInfoModel> getAddRatingMovies(@Path("movie_id") int movieId,
                                                        @Header("Content-Type") String contentType,
                                                        @Query("api_key") String apiKey,
                                                        @Body RatingMoviesRequestBody ratingMoviesRequestBody);
        @GET("movie/now_playing")
        Observable<MovieListModel> getNowShowingMovies(@Query("api_key") String apiKey,
                                                       @Query("language") String language,
                                                       @Query("page") int page);

        @GET("movie/latest")
        Observable<MovieListModel> getLatestMovies(@Query("api_key") String apiKey,
                                                   @Query("language") String language);

        @GET("movie/popular")
        Observable<MovieListModel> getPopularMovies(@Query("api_key") String apiKey,
                                                    @Query("language") String language,
                                                    @Query("page") int page);

        @GET("movie/top_rated")
        Observable<MovieListModel> getTopRatedMovies(@Query("api_key") String apiKey,
                                                     @Query("language") String language,
                                                     @Query("page") int page);

        @GET("movie/upcoming")
        Observable<MovieListModel> getUpcomingMovies(@Query("api_key") String apiKey,
                                                     @Query("language") String language,
                                                     @Query("page") int page);
    }
}
