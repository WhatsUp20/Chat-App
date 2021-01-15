package com.example.chatapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    @BindView(R.id.editTextTextEmaill) EditText editTextTextEmaill;
    @BindView(R.id.editTextTextPasswordd) EditText editTextTextPasswordd;
    @BindView(R.id.textViewHaveAnAccount) TextView textViewHaveAnAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();
        textViewHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentFromLogin = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(intentFromLogin);
            }
        });
    }

    public void onClickRegister(View view) {
        String email = editTextTextEmaill.getText().toString().trim();
        String password = editTextTextPasswordd.getText().toString().trim();
        if (email.isEmpty() || password.isEmpty()) {
            return;
        }
        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(RegisterActivity.this, "Ошибка: "+ task.getException(), Toast.LENGTH_SHORT).show();
                    }
                });
    }
}