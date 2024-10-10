package com.example.goonbarytime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class activity_post_free extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private EditText post_title_free, post_contents_free;
    private String nickname;
    FieldValue mTimestamp = FieldValue.serverTimestamp();
    private int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_free);

        post_title_free = findViewById(R.id.post_title_free);
        post_contents_free =findViewById(R.id.post_contents_free);

        findViewById(R.id.post_save_free).setOnClickListener(this);

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
        mStore.collection("post_free").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    index = count;
                    Log.v("인덱스", String.valueOf(index));
                }
            }
        });
    }


    @Override
    public void onClick(View v) {
        if (mAuth.getCurrentUser() != null) {
            // String postId = mStore.collection(FirebaseID.post_free).document().getId();
            Map<String, Object> data = new HashMap<>();
            data.put(FirebaseID.documentId, mAuth.getCurrentUser().getUid());
            data.put(FirebaseID.nickname, nickname);
            data.put(FirebaseID.title , post_title_free.getText().toString());
            data.put(FirebaseID.contents , post_contents_free.getText().toString());
            data.put(FirebaseID.timestamp, FieldValue.serverTimestamp());
            data.put(FirebaseID.index, index);
            String postId = mStore.collection(FirebaseID.post_free).document(String.valueOf(index)).getId();
            mStore.collection(FirebaseID.post_free).document(postId).set(data, SetOptions.merge());
            finish();
        }
    }
}