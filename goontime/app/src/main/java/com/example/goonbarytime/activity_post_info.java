package com.example.goonbarytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class activity_post_info extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText post_title_info, post_contents_info;
    private String nickname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_info);

        post_title_info = findViewById(R.id.post_title_info);
        post_contents_info = findViewById(R.id.post_contents_info);

        findViewById(R.id.post_save_info).setOnClickListener(this);

        if (mAuth.getCurrentUser() != null) {
            mStore.collection(FirebaseID.user).document(mAuth.getCurrentUser().getUid())
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if (task.getResult() != null) {
                                nickname = (String) task.getResult().getData().get(FirebaseID.nickname);
                            }
                        }
                    });
        }
    }



    @Override
    public void onClick(View v) {
        if (mAuth.getCurrentUser() != null) {
            String postId = mStore.collection(FirebaseID.post_info).document().getId();
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.documentId, mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.nickname, nickname);
            data.put(FirebaseID.title , post_title_info.getText().toString());
            data.put(FirebaseID.contents , post_contents_info.getText().toString());
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            mStore.collection(FirebaseID.post_info).document(postId).set(data, SetOptions.merge());
            finish();
        }
    }
}