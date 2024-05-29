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

public class RecentRecipeAdapter extends RecyclerView.Adapter<RecentRecipeAdapter.ViewHolder> {
    public List<Recipe> recipeList;

    public RecentRecipeAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
    @NonNull
    @Override
    public RecentRecipeAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_recentrecipe, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecentRecipeAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.setData(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView li_rr_iv_image;
        TextView li_rr_tv_label;
        TextView li_rr_tv_totalTime;
        TextView li_rr_tv_mealType;
        ImageButton li_rr_ib_bookmark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            li_rr_iv_image = itemView.findViewById(R.id.li_rr_iv_image);
            li_rr_tv_label = itemView.findViewById(R.id.li_rr_tv_label);
            li_rr_tv_totalTime = itemView.findViewById(R.id.li_rr_tv_totalTime);
            li_rr_tv_mealType = itemView.findViewById(R.id.li_rr_tv_mealType);
            li_rr_ib_bookmark = itemView.findViewById(R.id.li_rr_btn_bookmark);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        Recipe clickedRecipe =recipeList.get(position);
                        Context context = itemView.getContext();
                        Intent toDetail = new Intent(context, DetailActivity.class);
                        toDetail.putExtra("recipe", clickedRecipe.getUri());
                        toDetail.putExtra("label", clickedRecipe.getLabel());
                        context.startActivity(toDetail);
                    }
                }
            });
            li_rr_ib_bookmark.setOnClickListener(new View.OnClickListener() {
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
        }
        public void setData(Recipe recipe) {
            String formatTolalTime = String.format("%.0f",recipe.getTotalTime());
//
            Picasso.get().load(recipe.getImage()).into(li_rr_iv_image);
            li_rr_tv_label.setText(recipe.getLabel());
            li_rr_tv_totalTime.setText(formatTolalTime + " Minute");
            li_rr_tv_mealType.setText(formatListToString(recipe.getMealType()));
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
