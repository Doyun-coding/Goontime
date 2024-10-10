package com.example.goonbarytime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class activity_enlistment extends AppCompatActivity {

    private ImageView button_back_frag2;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private Frag1 frag1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enlistment);

        button_back_frag2 = findViewById(R.id.button_back_frag2);
        button_back_frag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity_enlistment.this, MainPage.class);  // frag1로 이동하게 바꿔야됨
                startActivity(intent);
            }
        });
    }
}