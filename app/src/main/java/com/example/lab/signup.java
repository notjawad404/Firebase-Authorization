package com.example.lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class signup extends AppCompatActivity {

    EditText semail, spass;
    Button signups, logins;

    FirebaseAuth sauth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        semail = findViewById(R.id.semail1);
        spass = findViewById(R.id.spass1);
        signups = findViewById(R.id.signups1);
        logins = findViewById(R.id.logins1);

        sauth = FirebaseAuth.getInstance();

        signups.setOnClickListener(view ->{
            createUser();
        });

        logins.setOnClickListener(view ->{
            loginUser();
        });
    }

    private void loginUser() {
        startActivity(new Intent(signup.this,Login.class));
    }

    private void createUser() {
    String email = semail.getText().toString();
    String password = spass.getText().toString();

    if(TextUtils.isEmpty(email)){
        semail.setError("Email cannot be empty");
        semail.requestFocus();
    }
    else if (TextUtils.isEmpty(password)){
        spass.setError("Password cannot be empty");
        spass.requestFocus();
    }
    else{
        sauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(signup.this,"Sign up successfully", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(signup.this, MainActivity.class));
                }
                else{
                    Toast.makeText(signup.this, "Sign up failed"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    }
}