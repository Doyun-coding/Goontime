package com.example.goonbarytime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import adapters.PostAdapter_free;

public class activity_search_free extends AppCompatActivity implements PostAdapter_free.OnNoteListener {

    private List<Post> search_title_free = new ArrayList<>();
    private List<Post> search_save_free = new ArrayList<>();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private List<Post> mDatas = new ArrayList<>();
    ImageView search_back_free;
    ImageView refresh_search_free;

    SearchView searchView;
    RecyclerView mRecyclerView;
    PostAdapter_free mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_free);

        search_title_free = new ArrayList<Post>();

        EditText searchView = findViewById(R.id.search_free);
        search_back_free = findViewById(R.id.search_back_free);
        search_back_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_search_free.this, activity_free.class);
                startActivity(intent);
            }
        });

        refresh_search_free = findViewById(R.id.refresh_search_free);
        refresh_search_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                finish();
                startActivity(intent);
            }
        });

        mRecyclerView = findViewById(R.id.recyclerview_search_free);

        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchUser(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
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
                            search_title_free.clear();
                            for (DocumentSnapshot snap : value.getDocuments()) {
                                Map<String, Object> shot = snap.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                String nickname = String.valueOf(shot.get(FirebaseID.nickname));
                                String title = String.valueOf(snap.get(FirebaseID.title));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Post data = new Post(documentId, nickname, title, contents);
                                search_title_free.add(data);
                                search_save_free.add(data);
                                mDatas.add(data);
                            }
                            mAdapter = new PostAdapter_free(search_title_free, activity_search_free.this::onNoteClick);
                            mRecyclerView.setAdapter(mAdapter);
                        }
                    }
                });
    }

    public void searchUser(String search) {
        search_title_free.clear();
        for (int i = 0; i < search_save_free.size(); i++) {
            if (search_save_free.get(i).getTitle().toLowerCase().contains(search.toLowerCase())) {
                search_title_free.add(search_save_free.get(i));
            }
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNoteClick(View v, int position) {
        Intent intent = new Intent(getApplicationContext(), activity_comment_free.class);
        intent.putExtra("title", mDatas.get(position).getTitle());
        intent.putExtra("contents", mDatas.get(position).getContent());
        startActivity(intent);
    }
}