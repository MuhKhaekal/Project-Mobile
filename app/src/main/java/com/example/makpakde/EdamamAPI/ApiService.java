package com.example.makpakde.EdamamAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("v2")
    Call<RecipeResponse> getRecipes(
            @Query("type") String type,
            @Query("q") String query,
            @Query("app_id") String appId,
            @Query("app_key") String appKey
    );

    @GET("v2/{id}")
    Call<SingleRecipeResponse> getSingleRecipe(
            @Path("id") String id,
            @Query("type") String type,
            @Query("q") String query,
            @Query("app_id") String appId,
            @Query("app_key") String appKey
    );
}
