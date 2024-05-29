package com.example.makpakde;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.makpakde.EdamamAPI.Recipe;
import com.example.makpakde.Model.DatabaseHelper;

public class ChangePasswordActivity extends AppCompatActivity {

    EditText change_et_password;
    EditText change_et_confirmPassword;
    Button change_btn_changePassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_password);

        change_et_password = findViewById(R.id.change_et_password);
        change_et_confirmPassword = findViewById(R.id.change_et_confirmPassword);
        change_btn_changePassword = findViewById(R.id.change_btn_changePassword);


        change_btn_changePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPassword = change_et_password.getText().toString().trim();
                String confrimNewPassword = change_et_confirmPassword.getText().toString().trim();

                if (newPassword.equalsIgnoreCase(confrimNewPassword)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
                    builder.setTitle("Change Password")
                            .setMessage("Are you sure you want to change your password?")
                            .setCancelable(true)
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    DatabaseHelper databaseHelper = new DatabaseHelper(ChangePasswordActivity.this);

                                    SharedPreferences preferencesUsername = ChangePasswordActivity.this.getSharedPreferences("preferencesUsername", MODE_PRIVATE);
                                    String usernameLogin = preferencesUsername.getString("usernameLogin", "");

                                    int userId = databaseHelper.getIdLoginUser(usernameLogin);
                                    databaseHelper.updateRecordUserPassword(userId, confrimNewPassword);
                                    Toast.makeText(ChangePasswordActivity.this, "Success change password", Toast.LENGTH_SHORT).show();
                                    Intent toMain = new Intent(ChangePasswordActivity.this, MainActivity.class);
                                    startActivity(toMain);
                                }
                            }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            }).show();
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Confirm your password", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}