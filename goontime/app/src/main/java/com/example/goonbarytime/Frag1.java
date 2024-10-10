package com.example.goonbarytime;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.Source;
import com.google.firebase.firestore.auth.User;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executor;

import static com.example.goonbarytime.FirebaseID.title;


public class Frag1 extends Fragment {
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    FirebaseFirestore mStore = FirebaseFirestore.getInstance();

    private View view;
    Button board_favorite;
    Button button_free;
    Button button_angry;
    Button button_boast;
    Button button_done;
    Button button_four;
    Button button_info;
    Button button_one;
    Button button_secret;
    Button button_three;
    Button button_two;
    Button button_hot;
    Button button_mma;
    Button button_mnd;
    Button button_mil;

    ImageView button_user;

    private TextView free_title;

    String userId;
    private int index;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag1, container, false);

        userId = mAuth.getCurrentUser().getUid();

        free_title = view.findViewById(R.id.free_title);
        mStore.collection("post_free").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    int count = 0;
                    for (DocumentSnapshot document : task.getResult()) {
                        count++;
                    }
                    index = count;
                }
            }
        });

        // DocumentReference documentReference = mStore.collection("post_free").document(String.valueOf(index));
        // String postFree = mStore.collection("post_free").document().getId();
        // DocumentReference documentReference = mStore.collection("post_free").document();
        /*documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                Log.v("확인", "완료");
                free_title.setText(documentSnapshot.getString("title"));
            }
        });*/

        button_user = view.findViewById(R.id.button_user);
        button_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_user.class);
                startActivity(intent);
            }
        });

        button_mil = view.findViewById(R.id.button_mil);
        button_mil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_hyperlink_mil.class);
                startActivity(intent);
            }
        });

        button_mnd = view.findViewById(R.id.button_mnd);
        button_mnd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_hyperlink_mnd.class);
                startActivity(intent);
            }
        });

        button_mma = view.findViewById(R.id.button_mma);
        button_mma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_hyperlink_mma.class);
                startActivity(intent);
            }
        });

        button_hot = view.findViewById(R.id.button_hot);
        button_hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_hot.class);
                startActivity(intent);
            }
        });

        board_favorite = view.findViewById(R.id.board_favorite);
        board_favorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainPage) getActivity()).setFrag(2);
            }
        });

        button_free = view.findViewById(R.id.button_free);
        button_free.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_free.class);
                startActivity(intent);
            }
        });
        button_angry = view.findViewById(R.id.button_angry);
        button_angry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_angry.class);
                startActivity(intent);
            }
        });
        button_boast = view.findViewById(R.id.button_boast);
        button_boast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_boast.class);
                startActivity(intent);
            }
        });
        button_done = view.findViewById(R.id.button_done);
        button_done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_done.class);
                startActivity(intent);
            }
        });
        button_four = view.findViewById(R.id.button_four);
        button_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_four.class);
                startActivity(intent);
            }
        });
        button_info = view.findViewById(R.id.button_info);
        button_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_info.class);
                startActivity(intent);
            }
        });
        button_secret = view.findViewById(R.id.button_secret);
        button_secret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_secret.class);
                startActivity(intent);
            }
        });
        button_three = view.findViewById(R.id.button_three);
        button_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_three.class);
                startActivity(intent);
            }
        });
        button_two = view.findViewById(R.id.button_two);
        button_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_two.class);
                startActivity(intent);
            }
        });
        button_one = view.findViewById(R.id.button_one);
        button_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_one.class);
                startActivity(intent);
            }
        });


        return view;
    }

}
