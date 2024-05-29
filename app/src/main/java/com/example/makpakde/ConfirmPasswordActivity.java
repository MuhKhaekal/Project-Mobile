package com.example.makpakde;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.makpakde.EdamamAPI.Recipe;
import com.example.makpakde.Model.DatabaseHelper;

public class ConfirmPasswordActivity extends AppCompatActivity {

    EditText confirm_et_password;
    Button confirm_btn_next;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_password);

        confirm_et_password = findViewById(R.id.confirm_et_password);
        confirm_btn_next = findViewById(R.id.confirm_btn_next);

        confirm_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(ConfirmPasswordActivity.this);

                SharedPreferences preferencesUsername = ConfirmPasswordActivity.this.getSharedPreferences("preferencesUsername", MODE_PRIVATE);
                String usernameLogin = preferencesUsername.getString("usernameLogin", "");
                int userId = databaseHelper.loginUser(usernameLogin);

                String password = confirm_et_password.getText().toString().trim();
                Boolean cek = databaseHelper.checkPassword(userId, password); // Gunakan password yang benar
                if (!cek && !password.isEmpty()){
                    Toast.makeText(ConfirmPasswordActivity.this, "Password tidak valid", Toast.LENGTH_SHORT).show();
                } else if (cek) {
                    Intent toChangePasswordActivity = new Intent(ConfirmPasswordActivity.this, ChangePasswordActivity.class);
                    startActivity(toChangePasswordActivity);
                }
            }
        });



    }
}