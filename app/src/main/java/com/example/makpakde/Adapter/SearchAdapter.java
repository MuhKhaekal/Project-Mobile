package com.example.makpakde.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makpakde.Activities.DetailActivity;
import com.example.makpakde.Model.Recipe;
import com.example.makpakde.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    public List<Recipe> recipeList;
    public Context context;

    public SearchAdapter(List<Recipe> recipeList, Context context) {
        this.recipeList = recipeList;
        this.context = context;
    }

    @NonNull
    @Override
    public SearchAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.setData(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView li_search_iv_image;
        ImageButton li_search_ib_bookmark;
        TextView li_search_tv_label;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            li_search_iv_image = itemView.findViewById(R.id.li_search_iv_image);
            li_search_ib_bookmark = itemView.findViewById(R.id.li_search_ib_bookmark);
            li_search_tv_label = itemView.findViewById(R.id.li_search_tv_label);
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
        }

        public void setData(Recipe recipe) {
            Picasso.get().load(recipe.getImage()).into(li_search_iv_image);
            li_search_tv_label.setText(recipe.getLabel());
        }
    }

    public void setFilteredList (List<Recipe> filteredList){
        this.recipeList = filteredList;
        notifyDataSetChanged();
    }
}
