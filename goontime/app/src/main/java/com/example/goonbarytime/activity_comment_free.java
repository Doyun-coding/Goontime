package com.example.goonbarytime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.SetOptions;
import com.google.firebase.firestore.auth.User;
import com.squareup.okhttp.internal.DiskLruCache;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import adapters.CommentAdapter_free;

public class activity_comment_free extends AppCompatActivity {

    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    private FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    private FirebaseDatabase mDatabase = FirebaseDatabase.getInstance();

    private ArrayList<Post> mDatas = new ArrayList<>();
    private ArrayList<Comment> mComment = new ArrayList<>();

    private TextView comment_title_free, comment_contents_free;
    private EditText post_title_free;
    private ImageView comment_send_free;
    private ImageView button_back_comment_free;
    private EditText comment_write_free;
    private TextView comment_nickname_free;

    private String nickname;
    private CommentAdapter_free mAdapter;
    private RecyclerView recyclerview_comment_free;

    private int index;
    private String time;
    private ArrayList<Chat> chatArrayList =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment_free);

        comment_title_free = findViewById(R.id.comment_title_free);
        comment_contents_free = findViewById(R.id.comment_contents_free);
        post_title_free = findViewById(R.id.post_title_free);
        comment_nickname_free = findViewById(R.id.comment_nickname_free);

        String title = "";
        String contents = "";
        String mIndex = "";

        Bundle extras = getIntent().getExtras();

        if (extras != null) {
            title = extras.getString("title");
            contents = extras.getString("contents");
            mIndex = extras.getString("index");
        }

        comment_title_free.setText(title);
        comment_contents_free.setText(contents);

        // 댓글

        comment_write_free = findViewById(R.id.comment_write_free);

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


        recyclerview_comment_free = findViewById(R.id.recyclerview_comment_free);
        mAdapter = new CommentAdapter_free(chatArrayList, mAuth.getCurrentUser().getEmail());

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                Chat chat = dataSnapshot.getValue(Chat.class);
                String commentKey = dataSnapshot.getKey();
                String id = chat.getId();
                String contents = chat.getContents();
                String nickname = chat.getNickname();

                chatArrayList.add(chat);
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String commentKey = dataSnapshot.getKey();

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String previousChildName) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(activity_comment_free.this, "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        String finalMIndex = mIndex;
        DatabaseReference ref = mDatabase.getReference("comment_free");// .child(finalMIndex);
        ref.addChildEventListener(childEventListener);

        comment_send_free = findViewById(R.id.comment_send_free);
        comment_send_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Chat chat = new Chat();
                DatabaseReference myRef = mDatabase.getReference("comment_free"); //.child(finalMIndex).child(String.valueOf(FieldValue.serverTimestamp()));

                chat.setId(mAuth.getCurrentUser().getEmail());
                chat.setContents(comment_write_free.getText().toString());
                chat.setNickname(nickname);

                myRef.setValue(chat);
            }
            /*@Override
            public void onClick(View v) {
                // FirebaseFirestore 이용
                if (mAuth.getCurrentUser() != null) {
                    String postId = mStore.collection(FirebaseID.comment_free).document().getId();
                    Map<String, Object> data = new HashMap<>();
                    data.put(FirebaseID.documentId, mAuth.getCurrentUser().getUid());
                    data.put(FirebaseID.nickname, nickname);
                    data.put(FirebaseID.contents , comment_write_free.getText().toString());
                    mStore.collection(FirebaseID.comment_free).document(postId).set(data, SetOptions.merge());

                    mStore.collection(FirebaseID.comment_free).orderBy(FirebaseID.timestamp, Query.Direction.ASCENDING)
                            .addSnapshotListener(new EventListener<QuerySnapshot>() {
                                @Override
                                public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                    if (value != null) {
                                        if (value.getDocuments() != null) {
                                            // ok
                                            Comment mData = new Comment(nickname, String.valueOf(data.get(FirebaseID.contents)));
                                            mComment.add(mData);
                                            mAdapter = new CommentAdapter_free(this, mComment);
                                            recyclerview_comment_free.setAdapter(mAdapter);
                                            mAdapter.notifyDataSetChanged();
                                        }
                                    }
                                }
                            });
                }
            }*/
            // FirebaseDatabase 이용

        });

        button_back_comment_free = findViewById(R.id.button_back_comment_free);
        button_back_comment_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_comment_free.this, activity_free.class);
                startActivity(intent);
            }
        });
        // 게시물 닉네임
        String userId = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = mStore.collection("user").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                comment_nickname_free.setText(value.getString("nickname"));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        mStore.collection(FirebaseID.comment_free)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Map<String, Object> shot = document.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                String nickname = String.valueOf(shot.get(FirebaseID.nickname));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Comment commentData = new Comment(documentId, nickname, contents);
                                mComment.add(commentData);
                            }
                            /*mAdapter = new CommentAdapter_free(this, mComment);
                            recyclerview_comment_free.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();*/
                        }
                    }
                });
        /*mStore.collection(FirebaseID.comment_free).orderBy(FirebaseID.timestamp, Query.Direction.ASCENDING)
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                        if(value != null) {
                            Log.v("Test", "plz");
                            mComment.clear();
                            for (DocumentSnapshot snap : value.getDocuments()) {
                                Log.v("Test", "second");
                                Map<String, Object> shot = snap.getData();
                                String documentId = String.valueOf(shot.get(FirebaseID.documentId));
                                String nickname = String.valueOf(shot.get(FirebaseID.nickname));
                                String contents = String.valueOf(shot.get(FirebaseID.contents));
                                Comment commentData = new Comment(documentId, nickname, contents);
                                mComment.add(commentData);
                            }
                            mAdapter = new CommentAdapter_free(this, mComment);
                            recyclerview_comment_free.setAdapter(mAdapter);
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                });*/
    }
}