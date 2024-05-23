package com.example.makpakde;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class GetStartedActivity extends AppCompatActivity {

    Button getStarted_btn_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_started);

        getStarted_btn_getStarted = findViewById(R.id.getStarted_btn_getStarted);

        getStarted_btn_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences = GetStartedActivity.this.getSharedPreferences("preferencesStart", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("checkStart", true);
                editor.apply();

                Intent toLogin = new Intent(GetStartedActivity.this, LoginActivity.class);
                startActivity(toLogin);
            }
        });
    }
}