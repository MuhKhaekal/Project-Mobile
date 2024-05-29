package com.example.makpakde.Fragments;

import static android.content.Context.MODE_PRIVATE;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.makpakde.Adapter.BookmarkAdapter;
import com.example.makpakde.ConfirmPasswordActivity;
import com.example.makpakde.EdamamAPI.ApiService;
import com.example.makpakde.EdamamAPI.Recipe;
import com.example.makpakde.EdamamAPI.RetrofitClient;
import com.example.makpakde.EdamamAPI.SingleRecipeResponse;
import com.example.makpakde.LoginActivity;
import com.example.makpakde.Model.DatabaseHelper;
import com.example.makpakde.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends Fragment {
    private static final String APP_ID = "f22371a7";
    private static final String APP_KEY = "06294766abfa75c4602e3bd8c2b35875";
    public static final String APP_TYPE = "public";

    RecyclerView fb_rv;
    BookmarkAdapter bookmarkAdapter;
    private List<Recipe> bookmarkList;
    DatabaseHelper databaseHelper;
    TextView fp_tv_fullname;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fb_rv = view.findViewById(R.id.fb_rv);
        fp_tv_fullname = view.findViewById(R.id.fp_tv_fullname);
        databaseHelper = new DatabaseHelper(getActivity());

        SharedPreferences preferencesUsername = getActivity().getSharedPreferences("preferencesUsername", MODE_PRIVATE);
        String usernameLogin = preferencesUsername.getString("usernameLogin", "");
                String fullName = databaseHelper.getFullNameLoginUser(usernameLogin);
        fp_tv_fullname.setText(fullName);

        bookmarkList = new ArrayList<>();
        bookmarkAdapter = new BookmarkAdapter(bookmarkList);
        fb_rv.setAdapter(bookmarkAdapter);


        loadBookmark();
        Spinner spinner = view.findViewById(R.id.fp_spinner);

        String[] items = new String[]{"Settings", "Change Password","Change Theme", "Logout"};


        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        Intent toConfirmPassword = new Intent(getActivity(), ConfirmPasswordActivity.class);
                        startActivity(toConfirmPassword);
                        break;
                    case 2:
                        Toast.makeText(getActivity(), "Halaman 2 dipilih", Toast.LENGTH_SHORT).show();
                        break;
                    case 3:
                        SharedPreferences sharedPreferences = getContext().getSharedPreferences("preferencesLogin", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putBoolean("checkLogin", false);
                        editor.apply();

                        Intent toLogin = new Intent(getContext(), LoginActivity.class);
                        startActivity(toLogin);
                        getActivity().finish();
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });
    }


    public void loadBookmark() {
        SharedPreferences preferencesUsername = getActivity().getSharedPreferences("preferencesUsername", MODE_PRIVATE);
        String usernameLogin = preferencesUsername.getString("usernameLogin", "");
        int userId = databaseHelper.getIdLoginUser(usernameLogin);

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
