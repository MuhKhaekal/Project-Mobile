package com.example.makpakde.Activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.makpakde.R;
import com.google.android.material.switchmaterial.SwitchMaterial;

public class ThemeActivity extends AppCompatActivity {

    SwitchMaterial theme_sc;
    ImageView theme_ib_back;
    SharedPreferences preferencesTheme;
    SharedPreferences.Editor editor;
    Boolean dark_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_theme);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        theme_sc = findViewById(R.id.theme_sc);
        theme_ib_back = findViewById(R.id.theme_iv_back);

        preferencesTheme = getSharedPreferences("theme", MODE_PRIVATE);
        dark_mode = preferencesTheme.getBoolean("darkmode", false);

        if (dark_mode) {
            theme_sc.setChecked(true);
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        theme_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isDarkMode = theme_sc.isChecked();
                AppCompatDelegate.setDefaultNightMode(
                        isDarkMode ? AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);

                editor = preferencesTheme.edit();
                editor.putBoolean("darkmode", isDarkMode);
                editor.apply();
            }
        });
    }
}
