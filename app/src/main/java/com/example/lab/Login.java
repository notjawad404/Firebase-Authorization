package com.example.lab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    TextView lemail, lpass;
    Button loginl, signupl;

    FirebaseAuth lauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        lemail = findViewById(R.id.lemail1);
        lpass = findViewById(R.id.lpass1);
        loginl = findViewById(R.id.loginl1);
        signupl = findViewById(R.id.signupl1);

        lauth = FirebaseAuth.getInstance();

        loginl.setOnClickListener(view ->{
            loginUser();
        });
        signupl.setOnClickListener(view ->{
            startActivity(new Intent(Login.this, signup.class));
        });
    }

    private void loginUser() {
        String email = lemail.getText().toString();
        String password = lpass.getText().toString();

        if(TextUtils.isEmpty(email)){
            lemail.setError("Email cannot be empty");
            lemail.requestFocus();
        }
        else if (TextUtils.isEmpty(password)){
            lpass.setError("Password cannot be empty");
            lpass.requestFocus();
        }
        else{
           lauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
               @Override
               public void onComplete(@NonNull Task<AuthResult> task) {
                   if(task.isSuccessful()){
                       Toast.makeText(Login.this,"Login up successfully", Toast.LENGTH_SHORT).show();
                       startActivity(new Intent(Login.this,MainActivity.class));
                   }
                   else{
                       Toast.makeText(Login.this, "Login failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                   }
               }
           });
        }
    }
}