package com.example.goonbarytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

import static com.example.goonbarytime.FirebaseID.user;

public class activity_signup extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private EditText signup_nickname, signup_id, signup_pw, signup_name, signup_email, signup_pw_ok;
    private ImageView signup_back, signup_x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signup_nickname = findViewById(R.id.signup_nickname);
        signup_id = findViewById(R.id.signup_id);
        signup_pw = findViewById(R.id.signup_pw);
        signup_name = findViewById(R.id.signup_name);
        signup_email = findViewById(R.id.signup_email);
        signup_pw_ok = findViewById(R.id.signup_pw_ok);

        findViewById(R.id.signup_signup).setOnClickListener(this);

        signup_back = findViewById(R.id.signup_back);
        signup_x = findViewById(R.id.signup_x);

        signup_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_signup.this, MainActivity.class);
                startActivity(intent);
            }
        });

        signup_x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_signup.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (signup_id.getText().toString().equals("") || signup_pw.getText().toString().equals("") || signup_pw_ok.getText().toString().equals("")
                || signup_email.getText().toString().equals("") || signup_name.getText().toString().equals("") || signup_nickname.getText().toString().equals("")){
            Toast.makeText(activity_signup.this, "정보를 모두 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        else if (signup_pw != signup_pw_ok) {
            Toast.makeText(activity_signup.this, "비밀번호를 동일하게 입력해주세요", Toast.LENGTH_SHORT).show();
            return;
        }
        else {
            mAuth.createUserWithEmailAndPassword(signup_id.getText().toString(), signup_pw.getText().toString())
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                if (user != null) {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Map<String, Object> userMap = new HashMap<>();
                                    userMap.put(FirebaseID.documentId, user.getUid());
                                    userMap.put(FirebaseID.nickname, signup_nickname.getText().toString());
                                    userMap.put(FirebaseID.email, signup_id.getText().toString());
                                    userMap.put(FirebaseID.password, signup_pw.getText().toString());
                                    userMap.put(FirebaseID.name, signup_name.getText().toString());
                                    userMap.put(FirebaseID.e_mail, signup_email.getText().toString());
                                    mStore.collection(FirebaseID.user).document(user.getUid()).set(userMap, SetOptions.merge());
                                    finish();
                                }
                            } else {
                                Toast.makeText(activity_signup.this, "정보를 정확히 입력하세요!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }
}