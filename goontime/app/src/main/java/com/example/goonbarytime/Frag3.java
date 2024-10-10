package com.example.goonbarytime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Frag3 extends Fragment {
    private View view;

    Button button_free2;
    Button button_secret2;
    Button button_done2;
    Button button_boast2;
    Button button_two2;
    Button button_one2;
    Button button_three2;
    Button button_four2;
    Button button_info2;
    Button button_angry2;
    Button button_hot2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag3, container, false);

        button_hot2 = view.findViewById(R.id.button_hot2);
        button_hot2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_hot.class);
                startActivity(intent);
            }
        });

        button_free2 = view.findViewById(R.id.button_free2);
        button_free2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_free.class);
                startActivity(intent);
            }
        });
        button_secret2 = view.findViewById(R.id.button_secret2);
        button_secret2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_secret.class);
                startActivity(intent);
            }
        });
        button_done2 = view.findViewById(R.id.button_done2);
        button_done2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_done.class);
                startActivity(intent);
            }
        });
        button_boast2 = view.findViewById(R.id.button_boast2);
        button_boast2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_boast.class);
                startActivity(intent);
            }
        });
        button_two2 = view.findViewById(R.id.button_two2);
        button_two2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_two.class);
                startActivity(intent);
            }
        });
        button_one2 = view.findViewById(R.id.button_one2);
        button_one2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_one.class);
                startActivity(intent);
            }
        });
        button_three2 = view.findViewById(R.id.button_three2);
        button_three2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_three.class);
                startActivity(intent);
            }
        });
        button_four2 = view.findViewById(R.id.button_four2);
        button_four2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_four.class);
                startActivity(intent);
            }
        });
        button_info2 = view.findViewById(R.id.button_info2);
        button_info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_info.class);
                startActivity(intent);
            }
        });
        button_angry2 = view.findViewById(R.id.button_angry2);
        button_angry2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_angry.class);
                startActivity(intent);
            }
        });

        return view;
    }
}
