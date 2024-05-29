package com.example.makpakde.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.makpakde.Adapter.BookmarkAdapter;
import com.example.makpakde.EdamamAPI.ApiService;
import com.example.makpakde.EdamamAPI.Recipe;
import com.example.makpakde.EdamamAPI.RetrofitClient;
import com.example.makpakde.EdamamAPI.SingleRecipeResponse;
import com.example.makpakde.Model.DatabaseHelper;
import com.example.makpakde.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class  BookmarkFragment extends Fragment {
    private static final String APP_ID = "f22371a7";
    private static final String APP_KEY = "06294766abfa75c4602e3bd8c2b35875";
    public static final String APP_TYPE = "public";

    RecyclerView fb_rv;
    BookmarkAdapter bookmarkAdapter;
    private List<Recipe> bookmarkList;
    DatabaseHelper databaseHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_bookmark, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        fb_rv = view.findViewById(R.id.fb_rv);

        bookmarkList = new ArrayList<>();
        bookmarkAdapter = new BookmarkAdapter(bookmarkList);
        fb_rv.setAdapter(bookmarkAdapter);

        databaseHelper = new DatabaseHelper(getActivity());
        loadBookmark();
    }


    public void loadBookmark() {
        SharedPreferences preferencesUsername = getActivity().getSharedPreferences("preferencesUsername", MODE_PRIVATE);
        String usernameLogin = preferencesUsername.getString("usernameLogin", "");
        int userId = databaseHelper.loginUser(usernameLogin);

        bookmarkList.clear();
        ApiService apiService = RetrofitClient.getClient();
        for (String recipeId : databaseHelper.getRecipeIdsByUserIdBookmark(userId)) {
            Call<SingleRecipeResponse> call = apiService.getSingleRecipe(recipeId, APP_TYPE, "", APP_ID, APP_KEY);
            call.enqueue(new Callback<SingleRecipeResponse>() {
                @Override
                public void onResponse(Call<SingleRecipeResponse> call, Response<SingleRecipeResponse> response) {
                    if (response.isSuccessful()) {
                        Recipe recipeResponse = response.body().getSingleRecipe();
                        bookmarkList.add(0, recipeResponse);
                        bookmarkAdapter.notifyDataSetChanged();
                    }
                }

                @Override
                public void onFailure(Call<SingleRecipeResponse> call, Throwable t) {
                    // Handle the error appropriately
                }
            });
        }
    }
}
