package com.example.makpakde;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
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
    LinearLayout confirm_ll_connect;
    LinearLayout confirm_ll_disconnect;
    Button confirm_btn_load;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_password);

        confirm_et_password = findViewById(R.id.confirm_et_password);
        confirm_btn_next = findViewById(R.id.confirm_btn_next);
        confirm_ll_connect = findViewById(R.id.confirm_ll_connect);
        confirm_ll_disconnect = findViewById(R.id.confirm_ll_disconnect);
        confirm_btn_load = findViewById(R.id.confirm_btn_load);





        confirm_btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()){
                    confirm_ll_disconnect.setVisibility(View.GONE);
                    confirm_ll_connect.setVisibility(View.VISIBLE);
                    firstCall();
                } else {
                    confirm_ll_disconnect.setVisibility(View.VISIBLE);
                    confirm_ll_connect.setVisibility(View.GONE);
                }
            }
        });


        confirm_btn_load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isConnected()){
                    confirm_ll_disconnect.setVisibility(View.GONE);
                    confirm_ll_connect.setVisibility(View.VISIBLE);
                    firstCall();
                }
            }
        });
    }

    private void firstCall(){
        DatabaseHelper databaseHelper = new DatabaseHelper(ConfirmPasswordActivity.this);

        SharedPreferences preferencesUsername = ConfirmPasswordActivity.this.getSharedPreferences("preferencesUsername", MODE_PRIVATE);
        String usernameLogin = preferencesUsername.getString("usernameLogin", "");
        int userId = databaseHelper.getIdLoginUser(usernameLogin);

        String password = confirm_et_password.getText().toString().trim();
        Boolean cek = databaseHelper.checkPassword(userId, password); // Gunakan password yang benar
        if (!cek && !password.isEmpty()){
            Toast.makeText(ConfirmPasswordActivity.this, "Password tidak valid", Toast.LENGTH_SHORT).show();
        } else if (cek) {
            Intent toChangePasswordActivity = new Intent(ConfirmPasswordActivity.this, ChangePasswordActivity.class);
            startActivity(toChangePasswordActivity);
        }
    }

    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetwork = connectivityManager.getActiveNetworkInfo();
            if (activeNetwork != null) {
                return activeNetwork.isConnected() || activeNetwork.isConnectedOrConnecting();
            }
        }
        return false;
    }
}