package com.example.goonbarytime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import adapters.PostAdapter_angry;
import adapters.PostAdapter_free;

public class activity_angry extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private PostAdapter_angry mAdapter;
    private List<Post> mDatas;

    Button button_post_angry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_angry);

        mPostRecyclerView = findViewById(R.id.recyclerview_angry);

        findViewById(R.id.button_post_angry).setOnClickListener(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mDatas = new ArrayList<>();
        mStore.collection(FirebaseID.post_angry)
                .orderBy(FirebaseID.timestamp, Query.Direction.DESCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if (value != null) {
                            mDatas.clear();
                            for (DocumentSnapshot snap : value.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                String nickname = String.valueOf(shot.get(FirebaseID.nickname));
                                String title = String.valueOf(snap.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Post data = new Post(documentId, nickname, title, contents);
                                mDatas.add(data);
                            }
                            mAdapter = new PostAdapter_angry(mDatas);
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity_angry.this, activity_post_angry.class);
        startActivity(intent);
    }
}