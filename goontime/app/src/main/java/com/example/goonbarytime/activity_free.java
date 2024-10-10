package com.example.goonbarytime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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

import adapters.PostAdapter;
import adapters.PostAdapter_free;
import adapters.PostAdapter_hot;

public class activity_free extends AppCompatActivity implements View.OnClickListener, PostAdapter_free.OnNoteListener {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private RecyclerView mPostRecyclerView;

    private PostAdapter_free mAdapter;
    private List<Post> mDatas = new ArrayList<>();

    Button button_post_free;
    ImageView button_search_free;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free);

        mPostRecyclerView = findViewById(R.id.recyclerview_free);

        button_search_free = findViewById(R.id.button_search_free);
        button_search_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_free.this, activity_search_free.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.button_post_free).setOnClickListener(this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        mStore.collection(FirebaseID.post_free)
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
                            mAdapter = new PostAdapter_free(mDatas,activity_free.this::onNoteClick);
                            mPostRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(activity_free.this, activity_post_free.class);
        startActivity(intent);
    }


    @Override
    public void onNoteClick(View v, int position) {
        Intent intent = new Intent(getApplicationContext(), activity_comment_free.class);
        intent.putExtra("title", mDatas.get(position).getTitle());
        intent.putExtra("contents", mDatas.get(position).getContent());
        intent.putExtra("index", mDatas.get(position).getIndex());
        startActivity(intent);
    }
}

