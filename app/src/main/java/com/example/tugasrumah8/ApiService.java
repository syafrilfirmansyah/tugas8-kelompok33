package com.example.tugasrumah8;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("discover/movie")
    Call<MovieList> getMovieList(@Query("api_key") String apiKey);

    @GET("search/movie")
    Call<MovieList> searchMovies(@Query("api_key") String apiKey,
                                 @Query("query") String query);

    @GET("discover/movie")
    Call<MovieList> getReleasedMovie(@Query("api_key") String apiKey,
                                     @Query("primary_release_date.gte") String dateGte,
                                     @Query("primary_release_date.lte") String dateLte);
}

