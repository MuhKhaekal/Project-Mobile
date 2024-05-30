package com.example.makpakde.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.makpakde.Model.Recipe;
import com.example.makpakde.Database.DatabaseHelper;
import com.example.makpakde.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.ViewHolder> {
    public List<Recipe> recipeList;

    public BookmarkAdapter(List<Recipe> recipeList) {
        this.recipeList = recipeList;
    }
    @NonNull
    @Override
    public BookmarkAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_bookmark, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookmarkAdapter.ViewHolder holder, int position) {
        Recipe recipe = recipeList.get(position);
        holder.setData(recipe);
    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView li_bookmark_iv_image;
        TextView li_bookmark_tv_label;
        ImageButton li_bookmark_ib_bookmark;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            li_bookmark_iv_image = itemView.findViewById(R.id.li_bookmark_iv_image);
            li_bookmark_tv_label = itemView.findViewById(R.id.li_bookmark_tv_label);
            li_bookmark_ib_bookmark = itemView.findViewById(R.id.li_bookmark_ib_bookmark);

            li_bookmark_ib_bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION){
                        DatabaseHelper databaseHelper = new DatabaseHelper(itemView.getContext());
                        Context context = itemView.getContext();

                        SharedPreferences preferencesUsername = context.getSharedPreferences("preferencesUsername", MODE_PRIVATE);
                        String usernameLogin = preferencesUsername.getString("usernameLogin", "");

                        Recipe clickedRecipe =recipeList.get(position);
                        int userId = databaseHelper.getIdLoginUser(usernameLogin);
                        databaseHelper.deleteBookmark(clickedRecipe.getUri(), userId);
                        recipeList.remove(position);
                        notifyDataSetChanged();

                    }
                }
            });
        }

        public void setData(Recipe recipe) {
            Picasso.get().load(recipe.getImage()).into(li_bookmark_iv_image);
            li_bookmark_tv_label.setText(recipe.getLabel());
        }
    }
}
