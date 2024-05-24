package com.example.makpakde.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makpakde.EdamamAPI.Recipe;
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
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            li_it_iv_image = itemView.findViewById(R.id.li_it_iv_image);
            li_it_tv_label = itemView.findViewById(R.id.li_it_tv_label);
            li_it_tv_dietLabels = itemView.findViewById(R.id.li_it_tv_dietLabels);
            li_it_tv_cuisineType = itemView.findViewById(R.id.li_it_tv_cuisineType);
        }

        public void setData(Recipe recipe) {
            Picasso.get().load(recipe.getImage()).into(li_it_iv_image);
            li_it_tv_label.setText(recipe.getLabel());


        }
    }
}
