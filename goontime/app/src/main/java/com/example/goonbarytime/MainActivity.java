package com.example.goonbarytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();

    private CheckBox login_check;

    private EditText login_id, login_pw;

    private TextView login_login;
    private TextView login_signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);

        findViewById(R.id.login_login).setOnClickListener(this);
        findViewById(R.id.login_signup).setOnClickListener(this);

        login_check = findViewById(R.id.login_check);

        login_login = findViewById(R.id.login_login);
        login_signup = findViewById(R.id.login_signup);


    }

    // 자동로그인
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
                    if (user != null) {
                        // Toast.makeText(this, "자동 로그인 : " + user.getUid(), Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(MainActivity.this, MainPage.class));
                    }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.login_signup:
                startActivity(new Intent(this, activity_signup.class));
                break;

            case R.id.login_login:
                if (login_id.getText().toString().equals("") || login_pw.getText().toString().equals("")) {
                    Toast.makeText(MainActivity.this, "아이디 또는 비밀번호를 입력해주세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {
                    mAuth.signInWithEmailAndPassword(login_id.getText().toString(), login_pw.getText().toString())
                            .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        FirebaseUser user = mAuth.getCurrentUser();
                                        if (user != null) {

                                            Toast.makeText(MainActivity.this, "로그인 성공 : " + user.getUid(), Toast.LENGTH_SHORT).show();
                                            Intent intent = new Intent(MainActivity.this, MainPage.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(MainActivity.this, "아이디와 비밀번호를 모두 입력해주세요!",
                                                    Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(MainActivity.this, "아이디 또는 비밀번호를 잘못 입력했습니다!",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                    break;
                }
        }
    }
}