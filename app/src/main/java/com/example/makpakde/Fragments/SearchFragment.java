package com.example.makpakde.Fragments;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.appcompat.widget.SearchView;

import com.example.makpakde.Adapter.IngredientTypeAdapter;
import com.example.makpakde.Adapter.RecentRecipeAdapter;
import com.example.makpakde.Adapter.SearchAdapter;
import com.example.makpakde.EdamamAPI.ApiService;
import com.example.makpakde.EdamamAPI.Recipe;
import com.example.makpakde.EdamamAPI.RecipeResponse;
import com.example.makpakde.EdamamAPI.RetrofitClient;
import com.example.makpakde.EdamamAPI.SingleRecipeResponse;
import com.example.makpakde.Model.DatabaseHelper;
import com.example.makpakde.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragment extends Fragment {
    public static final String APP_TYPE = "public";
    private static final String APP_ID = "f22371a7";
    private static final String APP_KEY = "06294766abfa75c4602e3bd8c2b35875";
    ApiService apiService;
    List<Recipe> recipeList;
    List<Recipe> filteredList;
    SearchAdapter searchAdapter;
    SearchView fs_sv;
    RecyclerView fs_rv;
    LinearLayout search_ll_connect;
    LinearLayout search_ll_disconnect;
    Button search_btn_load;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fs_sv = view.findViewById(R.id.fs_sv);
        fs_rv = view.findViewById(R.id.fs_rv);

        search_ll_connect = view.findViewById(R.id.search_ll_connect);
        search_ll_disconnect = view.findViewById(R.id.search_ll_disconnect);
        search_btn_load = view.findViewById(R.id.search_btn_load);

        search_ll_disconnect.setVisibility(View.VISIBLE);
        search_ll_connect.setVisibility(View.GONE);

        if (isConnected()){
            search_ll_disconnect.setVisibility(View.GONE);
            search_ll_connect.setVisibility(View.VISIBLE);
            firstCall();
        } else {
            search_ll_disconnect.setVisibility(View.VISIBLE);
            search_ll_connect.setVisibility(View.GONE);
        }

        search_btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    search_ll_disconnect.setVisibility(View.GONE);
                    search_ll_connect.setVisibility(View.VISIBLE);
                    firstCall();
                }
            }
        });


    }

    private void firstCall(){
        recipeList = new ArrayList<>();
        filteredList = new ArrayList<>();

        filteredList.addAll(filteredList);
        searchAdapter = new SearchAdapter(filteredList, getActivity());
        fs_rv.setAdapter(searchAdapter);

        fs_sv.clearFocus();
        fs_sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                loadIngredientType(newText);
                return true;
            }
        });
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                return activeNetwork.isConnected() || activeNetwork.isConnectedOrConnecting();
            }
        }
        return false;
    }
    public void loadIngredientType(String query){
        ApiService apiService = RetrofitClient.getClient();
        Call<RecipeResponse> call = apiService.getRecipes(APP_TYPE, query, APP_ID, APP_KEY);
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()){
                    List<RecipeResponse.Hit> hits = response.body().getHits();
                    for (RecipeResponse.Hit hit : hits){
                        recipeList.add(hit.getRecipe());
                    }
//                    ingredientTypeAdapter = new IngredientTypeAdapter(ingredientList);
//                    fh_rv_it.setAdapter(ingredientTypeAdapter);
//                    ingredientTypeAdapter.notifyDataSetChanged();
                    filteredList.clear();
                    for (Recipe recipe : recipeList){
                        if (recipe.getLabel().toLowerCase().contains(query.toLowerCase())){
                            filteredList.add(recipe);
                        }
                    }
                    searchAdapter.notifyDataSetChanged();
                    Log.d("Response", "Data successfully fetched from API. Number of hits: " + hits.size());
                } else {
                    Log.e("Response", "Failed to fetch data. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                Log.e("Response", "Failed to fetch data. Error: " + t.getMessage());
            }
        });
    }

}