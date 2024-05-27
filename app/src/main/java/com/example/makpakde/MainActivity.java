package com.example.makpakde;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.makpakde.Fragments.HomeFragment;
import com.example.makpakde.Fragments.SearchFragment;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferences;
    MeowBottomNavigation main_bottomNavigation;
    protected final int home = 1;
    protected final int search = 2;
    protected final int recommendation = 3;
    protected final int bookmark = 4;
    protected final int person = 5;
    HomeFragment homeFragment;
    SearchFragment searchFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        preferences = getSharedPreferences("preferencesStart", MODE_PRIVATE);
        boolean checkStart = preferences.getBoolean("checkStart", false);

        preferences = getSharedPreferences("preferencesLogin", MODE_PRIVATE);
        boolean checkLogin = preferences.getBoolean("checkLogin", false);

        if (!checkStart){
            Intent toStart = new Intent(MainActivity.this, GetStartedActivity.class);
            startActivity(toStart);
        }

        if (!checkLogin){
            Intent toLogin = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(toLogin);
        }

        main_bottomNavigation = findViewById(R.id.main_bottomNavigation);
        main_bottomNavigation.add(new MeowBottomNavigation.Model(home, R.drawable.baseline_home_24));
        main_bottomNavigation.add(new MeowBottomNavigation.Model(search, R.drawable.baseline_search_24));
        main_bottomNavigation.add(new MeowBottomNavigation.Model(recommendation, R.drawable.baseline_auto_awesome_24));
        main_bottomNavigation.add(new MeowBottomNavigation.Model(bookmark, R.drawable.baseline_bookmarks_24));
        main_bottomNavigation.add(new MeowBottomNavigation.Model(person, R.drawable.baseline_person_24));

        homeFragment = new HomeFragment();
        searchFragment = new SearchFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();

        main_bottomNavigation.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                return null;
            }
        });

        main_bottomNavigation.setOnShowListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                String name;
                switch (model.getId()){
                    case home:name="home";
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, homeFragment).commit();
                        break;
                    case search:name="search";
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame_layout, searchFragment).commit();
                        break;
                    case recommendation:name="recommendation";
                        Toast.makeText(MainActivity.this, "recomendation diklik", Toast.LENGTH_SHORT).show();
                        break;
                    case bookmark:name="bookmark";
                        Toast.makeText(MainActivity.this, "bookmark diklik", Toast.LENGTH_SHORT).show();
                        break;
                    case person:name="person";
                        Toast.makeText(MainActivity.this, "person diklik", Toast.LENGTH_SHORT).show();
                        break;
                }
                main_bottomNavigation.setCount(bookmark, "9");
                return null;
            }
        });



    }
}