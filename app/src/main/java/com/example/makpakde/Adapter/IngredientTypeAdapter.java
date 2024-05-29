package com.example.makpakde.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makpakde.DetailActivity;
import com.example.makpakde.EdamamAPI.Recipe;
import com.example.makpakde.Model.DatabaseHelper;
import com.example.makpakde.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientTypeAdapter extends RecyclerView.Adapter<IngredientTypeAdapter.ViewHolder> {
    public List<Recipe> recipeList;

    public IngredientTypeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public IngredientTypeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_ingredienttype, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientTypeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.setData(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView li_it_iv_image;
        TextView li_it_tv_label;
        TextView li_it_tv_dietLabels;
        TextView li_it_tv_cuisineType;
        ImageButton li_it_ib_bookmark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            li_it_iv_image = itemView.findViewById(R.id.li_it_iv_image);
            li_it_tv_label = itemView.findViewById(R.id.li_it_tv_label);
            li_it_tv_dietLabels = itemView.findViewById(R.id.li_it_tv_dietLabels);
            li_it_tv_cuisineType = itemView.findViewById(R.id.li_it_tv_cuisineType);
            li_it_ib_bookmark = itemView.findViewById(R.id.li_it_btn_bookmark);

            li_it_ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        DatabaseHelper databaseHelper = new DatabaseHelper(itemView.getContext());
                        Context context = itemView.getContext();

                        SharedPreferences preferencesUsername = context.getSharedPreferences("preferencesUsername", MODE_PRIVATE);
                        String usernameLogin = preferencesUsername.getString("usernameLogin", "");

                        Recipe clickedRecipe =recipeList.get(position);
                        int userId = databaseHelper.loginUser(usernameLogin);
                        databaseHelper.insertBookmarkRecipe(clickedRecipe.getUri(), userId);

                    }
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        DatabaseHelper databaseHelper = new DatabaseHelper(itemView.getContext());
                        Context context = itemView.getContext();
                        SharedPreferences preferencesUsername = context.getSharedPreferences("preferencesUsername", MODE_PRIVATE);
                        String usernameLogin = preferencesUsername.getString("usernameLogin", "");

                        Recipe clickedRecipe = recipeList.get(position);
                        int userId = databaseHelper.loginUser(usernameLogin);
                        databaseHelper.insertRecentRecipe(clickedRecipe.getUri(), userId);


                        Intent toDetail = new Intent(context, DetailActivity.class);
                        toDetail.putExtra("recipe", clickedRecipe.getUri());
                        toDetail.putExtra("label", clickedRecipe.getLabel());
                        context.startActivity(toDetail);
                    }
                }
            });



        }

        public void setData(Recipe recipe) {
            List<String> dietLabels = recipe.getDietLabels();
            List<String> cuisineType = recipe.getCuisineType();


            Picasso.get().load(recipe.getImage()).into(li_it_iv_image);
            li_it_tv_label.setText(recipe.getLabel());
            li_it_tv_dietLabels.setText(formatListToString(dietLabels));
            li_it_tv_cuisineType.setText(formatListToString(cuisineType));


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
}
