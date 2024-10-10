package com.example.goonbarytime;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class activity_user extends AppCompatActivity {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    FirebaseDatabase mData = FirebaseDatabase.getInstance();
    FirebaseUser user = mAuth.getCurrentUser();

    private TextView button_logout;
    private TextView profile_id;
    private TextView profile_name;
    private TextView profile_nickname;
    private ImageView profile;

    String userId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        button_logout = findViewById(R.id.button_logout);
        button_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(activity_user.this, MainActivity.class);
                startActivity(intent);
            }
        });

        profile_id = findViewById(R.id.profile_id);
        profile_id.setText(mAuth.getCurrentUser().getEmail());

        profile_name = findViewById(R.id.profile_name);
        profile_nickname = findViewById(R.id.profile_nickname);

        userId = mAuth.getCurrentUser().getUid();

        DocumentReference documentReference = mStore.collection("user").document(userId);
        documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                profile_name.setText(value.getString("name"));
                profile_nickname.setText(value.getString("nickname"));
            }
        });

    }
}