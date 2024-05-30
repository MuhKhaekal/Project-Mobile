package com.example.makpakde.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.makpakde.Database.DatabaseHelper;
import com.example.makpakde.R;

public class LoginActivity extends AppCompatActivity {

    EditText login_et_username;
    EditText login_et_password;
    Button login_btn_login;
    TextView login_tv_toSignin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        login_et_username = findViewById(R.id.login_et_username);
        login_et_password = findViewById(R.id.login_et_password);
        login_btn_login = findViewById(R.id.login_btn_login);
        login_tv_toSignin = findViewById(R.id.login_tv_toSignin);

        SharedPreferences preferences = getSharedPreferences("preferencesStart", MODE_PRIVATE);
        boolean checkStart = preferences.getBoolean("checkStart", false);

        if (!checkStart){
            Intent toStart = new Intent(LoginActivity.this, GetStartedActivity.class);
            startActivity(toStart);
        }

        login_btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(LoginActivity.this);
                String username = login_et_username.getText().toString();
                String password = login_et_password.getText().toString();

                SharedPreferences preferencesUsername = LoginActivity.this.getSharedPreferences("preferencesUsername", MODE_PRIVATE);
                SharedPreferences.Editor editorUsername = preferencesUsername.edit();
                editorUsername.putString("usernameLogin", username);
                editorUsername.apply();

                if (databaseHelper.checkUsernamePassword(username, password)){
                    SharedPreferences preferences = LoginActivity.this.getSharedPreferences("preferencesLogin", MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("checkLogin", true);
                    editor.apply();
                    Intent toMain = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(toMain);
                } else {
                    Toast.makeText(LoginActivity.this, "Username Not Found", Toast.LENGTH_SHORT).show();
                }

            }
        });

        login_tv_toSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent toSignIn = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(toSignIn);
            }
        });
    }
}