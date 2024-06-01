package com.example.makpakde.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.makpakde.EdamamAPI.ApiService;
import com.example.makpakde.Model.Ingredient;
import com.example.makpakde.Model.Nutrient;
import com.example.makpakde.Model.Recipe;
import com.example.makpakde.EdamamAPI.RetrofitClient;
import com.example.makpakde.EdamamAPI.SingleRecipeResponse;
import com.example.makpakde.Model.TotalNutrients;
import com.example.makpakde.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class DetailActivity extends AppCompatActivity {

    public static final String APP_TYPE = "public";
    private static final String APP_ID = "f22371a7";
    private static final String APP_KEY = "06294766abfa75c4602e3bd8c2b35875";


    ApiService apiService;
    ImageView ad_iv_back;
    TextView ad_tv_label;
    ImageView ad_iv_image;
    TextView ad_tv_source;
    ImageView ad_ib_url;
    TextView ad_tv_cuisineType;
    TextView ad_tv_dietLabels;
    TextView ad_tv_totalTime;
    TextView ad_tv_totalWeight;
    TextView ad_tv_countItems;
    LinearLayout ingredientLayout;
    LinearLayout totalNutrient;
    LinearLayout detail_ll_connect;
    LinearLayout detail_ll_disconnect;
    LinearLayout detail_ll_thread;
    Button detail_btn_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ad_tv_label = findViewById(R.id.ad_tv_label);
        ad_iv_back = findViewById(R.id.ad_iv_back);
        ad_iv_image = findViewById(R.id.ad_iv_image);
        ad_tv_source = findViewById(R.id.ad_tv_source);
        ad_ib_url = findViewById(R.id.ad_ib_url);
        ad_tv_cuisineType = findViewById(R.id.ad_tv_cuisineType);
        ad_tv_dietLabels = findViewById(R.id.ad_tv_dietLabels);
        ad_tv_totalTime = findViewById(R.id.ad_tv_totalTime);
        ad_tv_totalWeight = findViewById(R.id.ad_tv_totalWeight);
        ad_tv_countItems = findViewById(R.id.ad_tv_countItems);
        ingredientLayout = findViewById(R.id.ingredientLayout);
        totalNutrient = findViewById(R.id.totalnutrient);
        detail_ll_connect = findViewById(R.id.detail_ll_connect);
        detail_ll_disconnect = findViewById(R.id.detail_ll_disconnect);
        detail_btn_load = findViewById(R.id.detail_btn_load);

        detail_ll_thread = findViewById(R.id.detail_ll_thread);

        detail_ll_thread.setVisibility(View.VISIBLE);
        detail_ll_disconnect.setVisibility(View.GONE);
        detail_ll_connect.setVisibility(View.GONE);


        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() ->{
            try {
                Thread.sleep(2500);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }

            runOnUiThread(()->{
                if (isConnected()){
                    detail_ll_thread.setVisibility(View.GONE);
                    detail_ll_disconnect.setVisibility(View.GONE);
                    detail_ll_connect.setVisibility(View.VISIBLE);

                    firstCall();

                } else {
                    detail_ll_disconnect.setVisibility(View.VISIBLE);
                    detail_ll_connect.setVisibility(View.GONE);
                }

                detail_btn_load.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isConnected()){
                            detail_ll_thread.setVisibility(View.GONE);
                            detail_ll_disconnect.setVisibility(View.GONE);
                            detail_ll_connect.setVisibility(View.VISIBLE);

                            firstCall();

                        }
                    }
                });


            });

        });
        SharedPreferences preferencesTheme = getSharedPreferences("theme", MODE_PRIVATE);
        Boolean dark_mode = preferencesTheme.getBoolean("darkmode", false);
        if (dark_mode){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }



    }

    private  void firstCall(){
        ad_iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String url = getIntent().getStringExtra("recipe");
        String label = getIntent().getStringExtra("label");


        int hashIndex = url.indexOf('#');
        int underscoreIndex = url.indexOf('_', hashIndex);
        String recipeId = url.substring(underscoreIndex + 1);


        List<String> keywords = new ArrayList<>();
        keywords.add("chicken");
        keywords.add("beef");
        keywords.add("fish");
        keywords.add("tofu");
        keywords.add("vegetables");
        keywords.add("egg");
        keywords.add("shrimp");
        keywords.add("dairy");
        keywords.add("flour");
        keywords.add("fruits");
        keywords.add("seeds");
        keywords.add("sausage");
        keywords.add("noodle");
        keywords.add("bread");
        keywords.add("octopus");
        keywords.add("savory");
        keywords.add("spicy");
        keywords.add("oil");
        keywords.add("fried");
        keywords.add("roast");
        keywords.add("sweet");
        keywords.add("almond");
        keywords.add("steam");
        keywords.add("burn");
        keywords.add("boil");

        String query = checkKeywords(keywords, label);
        apiService = RetrofitClient.getClient();

        Call<SingleRecipeResponse>call = apiService.getSingleRecipe(recipeId,APP_TYPE, query, APP_ID, APP_KEY);
        call.enqueue(new Callback<SingleRecipeResponse>() {
            @Override
            public void onResponse(Call<SingleRecipeResponse> call, Response<SingleRecipeResponse> response) {
                if (response.isSuccessful()){
                    Recipe recipeResponse = response.body().getSingleRecipe();
                    List<String> dietLabels = recipeResponse.getDietLabels();
                    List<String> cuisineType = recipeResponse.getCuisineType();
                    String formatTolalWeight = String.format("%.2f",recipeResponse.getTotalWeight());
                    String formatTolalTime = String.format("%.0f",recipeResponse.getTotalTime());

                    ad_tv_label.setText(recipeResponse.getLabel());
                    Picasso.get().load(recipeResponse.getImage()).into(ad_iv_image);
                    ad_tv_source.setText(" " + recipeResponse.getSource());
                    ad_tv_cuisineType.setText(formatListToString(cuisineType));
                    ad_tv_dietLabels.setText(formatListToString(dietLabels));
                    ad_tv_totalWeight.setText(":" + formatTolalWeight + " g");
                    ad_tv_totalTime.setText(":" + formatTolalTime+ " minute");

                    ad_ib_url.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent toSource = new Intent(Intent.ACTION_VIEW, Uri.parse(recipeResponse.getUrl()));
                            startActivity(toSource);
                        }
                    });



                    List<Ingredient> ingredients = recipeResponse.getIngredients();
                    for (Ingredient ingredient : ingredients){
                        LinearLayout ingredientItem = (LinearLayout) getLayoutInflater().inflate(R.layout.ingredient_item, null);

                        TextView ingredientText = ingredientItem.findViewById(R.id.ad_tv_ingredientText);
                        ImageView ingredientImage = ingredientItem.findViewById(R.id.ad_iv_ingredientImage);

                        ingredientText.setText(ingredient.getText());
                        if (ingredient.getImage() != null && !ingredient.getImage().isEmpty()){
                            Picasso.get().load(ingredient.getImage()).into(ingredientImage);
                        } else {
                            ingredientImage.setImageResource(R.drawable.ic_launcher_background);
                        }

                        ingredientLayout.addView(ingredientItem);
                    }
                    TotalNutrients totalNutrients = recipeResponse.getTotalNutrients();
                    List<Nutrient> nutrientsList = totalNutrients.getAllNutrients();
                    for (Nutrient nutrient : nutrientsList) {
                        if (nutrient != null) { // Periksa apakah nutrient tidak null
                            LinearLayout totalNutrientLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.total_nutrient, null);
                            TextView nutrientTextView = totalNutrientLayout.findViewById(R.id.ad_tv_totalNutritionsLabel);
                            TextView nutrientQuantity = totalNutrientLayout.findViewById(R.id.ad_tv_totalNutritionsQuantity);

                            String stringQuantity = String.format("%.0f", nutrient.getQuantity());

                            // Pastikan nutrient tidak null sebelum mengakses metode atau properti pada objek tersebut
                            nutrientTextView.setText(nutrient.getLabel());
                            nutrientQuantity.setText(": " + stringQuantity + " " + nutrient.getUnit());

                            totalNutrient.addView(totalNutrientLayout);
                        }
                    }

                    ad_tv_countItems.setText(ingredients.size() + " items");
                }
            }

            @Override
            public void onFailure(Call<SingleRecipeResponse> call, Throwable t) {

            }
        });
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                return activeNetwork.isConnected() || activeNetwork.isConnectedOrConnecting();
            }
        }
        return false;
    }

    public static String checkKeywords(List<String> keywords, String nama) {
        String query = "";
        String lowerCaseNama = nama.toLowerCase();

        for (String keyword : keywords) {
            if (lowerCaseNama.contains(keyword.toLowerCase())) {
                query = keyword;
            }
        }

        return query;
    }

    private String formatListToString(List<String> list) {
        if (list == null || list.isEmpty()) {
            return "";
        }
        StringBuilder formattedString = new StringBuilder();
        for (String item : list) {
            formattedString.append(item).append(", ");
        }
        if (formattedString.length() > 0) {
            formattedString.setLength(formattedString.length() - 2);
        }
        return formattedString.toString();
    }



}