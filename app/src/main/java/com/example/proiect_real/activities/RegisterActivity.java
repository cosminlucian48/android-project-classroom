package com.example.proiect_real.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.proiect_real.R;
import com.example.proiect_real.dao.UserDao;
import com.example.proiect_real.entity.UserEntity;
import com.example.proiect_real.view_model.StudentViewModel;
import com.example.proiect_real.view_model.UserViewModel;

import org.apache.commons.lang3.math.NumberUtils;

public class RegisterActivity extends AppCompatActivity {

    private final String TAG = RegisterActivity.class.getSimpleName();
    EditText editTextUsername, editTextEmail, editTextPassword, editTextCnfPassword;
    Spinner accountTypeSpinner;
    Button buttonRegister;

    TextView textViewLogin;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        editTextUsername = findViewById(R.id.editTextUsername);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextCnfPassword = findViewById(R.id.editTextCnfPassword);
        buttonRegister = findViewById(R.id.buttonRegister);
        accountTypeSpinner = findViewById(R.id.accountTypeSpinner);


        textViewLogin = findViewById(R.id.textViewLogin);
        textViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LogInActivity.class));
            }
        });

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(editTextUsername.getText()) ||
                        TextUtils.isEmpty(editTextEmail.getText()) ||
                        TextUtils.isEmpty(editTextPassword.getText()) ||
                        TextUtils.isEmpty(editTextCnfPassword.getText())) {
                    Toast.makeText(getApplicationContext(),"There are empty fields.",Toast.LENGTH_LONG).show();
                } else {

                    String userName = editTextUsername.getText().toString().trim();
                    String email = editTextEmail.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();
                    String passwordConf = editTextCnfPassword.getText().toString().trim();
                    String accountType = accountTypeSpinner.getSelectedItem().toString();


                    if (password.equals(passwordConf)) {
                        UserEntity userEntity = new UserEntity(userName, password, email, accountType);
                        userViewModel.insert(userEntity);
                        Intent moveToLogin = new Intent(RegisterActivity.this, LogInActivity.class);
                        startActivity(moveToLogin);

                    } else {
                        Toast.makeText(RegisterActivity.this, "Password is not matching", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }
}
