package com.example.goonbarytime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Hashtable;
import java.util.List;

import adapters.ChatAdapter;

public class activity_message extends AppCompatActivity {
    private static final String TAG = "activity_message";
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mStore = FirebaseFirestore.getInstance();
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private RecyclerView recyclerview_chat;
    private ChatAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    EditText chat_write;

    ImageView chat_send;

    ArrayList<Chat> chatArrayList =new ArrayList<>();
    Hashtable<String, String> numbers = new Hashtable<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        // id = getIntent().getStringExtra("login_id");
        chat_write = findViewById(R.id.chat_write);
        chat_send = findViewById(R.id.chat_send);
        recyclerview_chat = findViewById(R.id.recyclerview_chat);
        recyclerview_chat.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerview_chat.setLayoutManager(layoutManager);

        mAdapter = new ChatAdapter(chatArrayList, mAuth.getCurrentUser().getEmail());
        recyclerview_chat.setAdapter(mAdapter);

        ChildEventListener childEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String previousChildName) {

                Chat chat = dataSnapshot.getValue(Chat.class);
                String commentKey = dataSnapshot.getKey();
                String id = chat.getId();
                String contents = chat.getContents();
                Log.d(TAG, "id : " + chat.getId());
                Log.d(TAG, "contents : " + chat.getContents());

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
                Toast.makeText(activity_message.this, "Failed to load comments.",
                        Toast.LENGTH_SHORT).show();
            }
        };
        DatabaseReference ref = database.getReference("message");
        Log.d(TAG, "ref");
        ref.addChildEventListener(childEventListener);


        chat_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String contents = chat_write.getText().toString();
                String id = mAuth.getCurrentUser().getEmail();
                Chat chat = new Chat();

                Calendar c = Calendar.getInstance();
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
                String datetime = dateFormat.format(c.getTime());
                System.out.println(datetime);

                DatabaseReference myRef = database.getReference("message").child(datetime);

                chat.setId(id);
                chat.setContents(contents);

                myRef.setValue(chat);
                Log.v("setValue(chat)", "setValue(chat)");

                // numbers.put("email", id);
                // numbers.put("contents", contents);
                // myRef.setValue(numbers);
                // Log.v("setValue(numbers)", "setValue(numbers)");
            }
        });
    }
}