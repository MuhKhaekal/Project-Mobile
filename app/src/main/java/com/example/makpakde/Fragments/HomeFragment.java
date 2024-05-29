package com.example.makpakde.Fragments;

import static android.content.Context.CONNECTIVITY_SERVICE;
import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.getSystemService;
import static com.example.makpakde.DetailActivity.APP_TYPE;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.makpakde.Adapter.IngredientTypeAdapter;
import com.example.makpakde.Adapter.RecentRecipeAdapter;
import com.example.makpakde.Adapter.TopRecommendationAdapter;
import com.example.makpakde.EdamamAPI.ApiService;
import com.example.makpakde.EdamamAPI.Recipe;
import com.example.makpakde.EdamamAPI.RecipeResponse;
import com.example.makpakde.EdamamAPI.RetrofitClient;
import com.example.makpakde.EdamamAPI.SingleRecipeResponse;
import com.example.makpakde.Model.DatabaseHelper;
import com.example.makpakde.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {
    private static final String APP_ID = "f22371a7";
    private static final String APP_KEY = "06294766abfa75c4602e3bd8c2b35875";
    RecyclerView fh_rv_it;
    RecyclerView fh_rv_tr;
    RecyclerView fh_rv_rr;
    IngredientTypeAdapter ingredientTypeAdapter;
    TopRecommendationAdapter topRecommendationAdapter;
    RecentRecipeAdapter recentRecipeAdapter;
    private List<Recipe> recommendationList = new ArrayList<>();
    private List<Recipe> ingredientList = new ArrayList<>();
    private List<Recipe> recentList = new ArrayList<>();

    protected Button fh_btn_chicken;
    Button fh_btn_beef;
    Button fh_btn_fish;
    Button fh_btn_tofu;
    Button fh_btn_vegetables;
    Button fh_btn_egg;
    Button fh_btn_shrimp;
    Button fh_btn_dairy;
    Button fh_btn_flour;
    Button fh_btn_fruits;
    Button fh_btn_seeds;
    Button fh_btn_sausage;
    Button fh_btn_noodle;
    Button fh_btn_bread;
    Button fh_btn_octopus;

    LinearLayout home_ll_connect;
    LinearLayout home_ll_disconnect;
    Button home_btn_load;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fh_rv_tr = view.findViewById(R.id.fh_rv_tr);
        fh_rv_it = view.findViewById(R.id.fh_rv_it);
        fh_rv_rr = view.findViewById(R.id.fh_rv_rr);

        fh_btn_chicken = view.findViewById(R.id.fh_btn_chicken);
        fh_btn_beef = view.findViewById(R.id.fh_btn_beef);
        fh_btn_fish = view.findViewById(R.id.fh_btn_fish);
        fh_btn_tofu = view.findViewById(R.id.fh_btn_tofu);
        fh_btn_vegetables = view.findViewById(R.id.fh_btn_vegetables);
        fh_btn_egg = view.findViewById(R.id.fh_btn_egg);
        fh_btn_shrimp = view.findViewById(R.id.fh_btn_shrimp);
        fh_btn_dairy = view.findViewById(R.id.fh_btn_dairy);
        fh_btn_flour = view.findViewById(R.id.fh_btn_flour);
        fh_btn_fruits = view.findViewById(R.id.fh_btn_fruits);
        fh_btn_seeds = view.findViewById(R.id.fh_btn_seeds);
        fh_btn_sausage = view.findViewById(R.id.fh_btn_sausage);
        fh_btn_noodle = view.findViewById(R.id.fh_btn_noodle);
        fh_btn_bread = view.findViewById(R.id.fh_btn_bread);
        fh_btn_octopus = view.findViewById(R.id.fh_btn_octopus);
        home_ll_connect = view.findViewById(R.id.home_ll_connect);
        home_ll_disconnect = view.findViewById(R.id.home_ll_disconnect);
        home_btn_load = view.findViewById(R.id.home_btn_load);


        fh_btn_chicken.setTag("chicken");
        fh_btn_beef.setTag("beef");
        fh_btn_fish.setTag("fish");
        fh_btn_tofu.setTag("tofu");
        fh_btn_vegetables.setTag("vegetables");
        fh_btn_egg.setTag("egg");
        fh_btn_shrimp.setTag("shrimp");
        fh_btn_dairy.setTag("dairy");
        fh_btn_flour.setTag("flour");
        fh_btn_fruits.setTag("fruits");
        fh_btn_seeds.setTag("seeds");
        fh_btn_sausage.setTag("sausage");
        fh_btn_noodle.setTag("noodle");
        fh_btn_bread.setTag("bread");
        fh_btn_octopus.setTag("octopus");

        home_ll_disconnect.setVisibility(View.VISIBLE);
        home_ll_connect.setVisibility(View.GONE);


        if (isConnected()){
            home_ll_disconnect.setVisibility(View.GONE);
            home_ll_connect.setVisibility(View.VISIBLE);
            firstCall();
        } else {
            home_ll_disconnect.setVisibility(View.VISIBLE);
            home_ll_connect.setVisibility(View.GONE);
        }

        home_btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()) {
                    home_ll_disconnect.setVisibility(View.GONE);
                    home_ll_connect.setVisibility(View.VISIBLE);
                    firstCall();
                }
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

    private void firstCall(){
        List<String> keyRandom = new ArrayList<>();
        keyRandom.add("savory");
        keyRandom.add("spicy");
        keyRandom.add("oil");
        keyRandom.add("fried");
        keyRandom.add("roast");
        keyRandom.add("sweet");
        keyRandom.add("almond");
        keyRandom.add("steam");
        keyRandom.add("burn");
        keyRandom.add("boil");
        Random random = new Random();
        int randomIndex = random.nextInt(keyRandom.size());
        String randomKeyword = keyRandom.get(randomIndex);
        ApiService apiService = RetrofitClient.getClient();
        Call<RecipeResponse> call = apiService.getRecipes("public", randomKeyword, APP_ID, APP_KEY);
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()){
                    recommendationList.clear();
                    List<RecipeResponse.Hit> hits = response.body().getHits();
                    for (RecipeResponse.Hit hit : hits){
                        recommendationList.add(hit.getRecipe());
                    }
                    topRecommendationAdapter = new TopRecommendationAdapter(recommendationList);
                    fh_rv_tr.setAdapter(topRecommendationAdapter);
                    topRecommendationAdapter.notifyDataSetChanged();

                    Log.d("Response", "Data successfully fetched from API. Number of hits: " + hits.size());
                } else {
                    Log.e("Response", "Failed to fetch data. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<RecipeResponse> call, Throwable t) {
                home_ll_disconnect.setVisibility(View.VISIBLE);
                home_ll_connect.setVisibility(View.GONE);
            }
        });

        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        SharedPreferences preferencesUsername = getActivity().getSharedPreferences("preferencesUsername", MODE_PRIVATE);
        String usernameLogin = preferencesUsername.getString("usernameLogin", "");
        int userId = databaseHelper.getIdLoginUser(usernameLogin);

        loadIngredientType("chicken");
        loadRecipeRecent(userId);
        View.OnClickListener buttonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetButtonColors();
                String ingredient = (String) v.getTag();
                switch (ingredient) {
                    case "chicken":
                        fh_btn_chicken.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("chicken");
                        break;
                    case "beef":
                        fh_btn_beef.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("beef");
                        break;
                    case "fish":
                        fh_btn_fish.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("fish");
                        break;
                    case "tofu":
                        fh_btn_tofu.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("tofu");
                        break;
                    case "vegetables":
                        fh_btn_vegetables.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("vegetables");
                        break;
                    case "egg":
                        fh_btn_egg.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("egg");
                        break;
                    case "shrimp":
                        fh_btn_shrimp.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("shrimp");
                        break;
                    case "dairy":
                        fh_btn_dairy.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("dairy");
                        break;
                    case "flour":
                        fh_btn_flour.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("flour");
                        break;
                    case "fruits":
                        fh_btn_fruits.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("fruits");
                        break;
                    case "seeds":
                        fh_btn_seeds.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("seeds");
                        break;
                    case "sausage":
                        fh_btn_sausage.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("sausage");
                        break;
                    case "noodle":
                        fh_btn_noodle.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("noodle");
                        break;
                    case "bread":
                        fh_btn_bread.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("bread");
                        break;
                    case "octopus":
                        fh_btn_octopus.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.primaryColor));
                        loadIngredientType("octopus");
                        break;

                }
            }
        };

        fh_btn_chicken.setOnClickListener(buttonClickListener);
        fh_btn_beef.setOnClickListener(buttonClickListener);
        fh_btn_fish.setOnClickListener(buttonClickListener);
        fh_btn_tofu.setOnClickListener(buttonClickListener);
        fh_btn_vegetables.setOnClickListener(buttonClickListener);
        fh_btn_egg.setOnClickListener(buttonClickListener);
        fh_btn_shrimp.setOnClickListener(buttonClickListener);
        fh_btn_dairy.setOnClickListener(buttonClickListener);
        fh_btn_flour.setOnClickListener(buttonClickListener);
        fh_btn_fruits.setOnClickListener(buttonClickListener);
        fh_btn_seeds.setOnClickListener(buttonClickListener);
        fh_btn_sausage.setOnClickListener(buttonClickListener);
        fh_btn_noodle.setOnClickListener(buttonClickListener);
        fh_btn_bread.setOnClickListener(buttonClickListener);
        fh_btn_octopus.setOnClickListener(buttonClickListener);
    }

    private void resetButtonColors() {
        fh_btn_chicken.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_beef.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_fish.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_tofu.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_vegetables.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_egg.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_shrimp.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_dairy.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_flour.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_fruits.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_seeds.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_sausage.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_noodle.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_bread.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));
        fh_btn_octopus.setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.transparent));

    }

    public void loadIngredientType(String ingredient){
        ApiService apiService = RetrofitClient.getClient();
        Call<RecipeResponse> call = apiService.getRecipes("public", ingredient, APP_ID, APP_KEY);
        call.enqueue(new Callback<RecipeResponse>() {
            @Override
            public void onResponse(Call<RecipeResponse> call, Response<RecipeResponse> response) {
                if (response.isSuccessful()){
                    ingredientList.clear();
                    List<RecipeResponse.Hit> hits = response.body().getHits();
                    for (RecipeResponse.Hit hit : hits){
                        ingredientList.add(hit.getRecipe());
                    }
                    ingredientTypeAdapter = new IngredientTypeAdapter(ingredientList);
                    fh_rv_it.setAdapter(ingredientTypeAdapter);
                    ingredientTypeAdapter.notifyDataSetChanged();

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

    @Override
    public void onResume() {
        super.onResume();
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        SharedPreferences preferencesUsername =     getActivity().getSharedPreferences("preferencesUsername", MODE_PRIVATE);
        String usernameLogin = preferencesUsername.getString("usernameLogin", "");
        int userId = databaseHelper.getIdLoginUser(usernameLogin);
        loadRecipeRecent(userId);
    }

    public void loadRecipeRecent(int userId){
        recentList.clear();
        ApiService apiService = RetrofitClient.getClient();
        DatabaseHelper databaseHelper = new DatabaseHelper(getActivity());
        for (String recipeId : databaseHelper.getRecipeIdsByUserId(userId)) {
            Call<SingleRecipeResponse>call = apiService.getSingleRecipe(recipeId,"public", "chicken", APP_ID, APP_KEY);
            call.enqueue(new Callback<SingleRecipeResponse>() {
                @Override
                public void onResponse(Call<SingleRecipeResponse> call, Response<SingleRecipeResponse> response) {
                    if (response.isSuccessful()){
                        Recipe recipeResponse = response.body().getSingleRecipe();
                        recentList.add(0, recipeResponse);
                        recentRecipeAdapter = new RecentRecipeAdapter(recentList);
                        fh_rv_rr.setAdapter(recentRecipeAdapter);
                        recentRecipeAdapter.notifyDataSetChanged();
                    }


                }

                @Override
                public void onFailure(Call<SingleRecipeResponse> call, Throwable t) {

                }
            });

        }

    }


}