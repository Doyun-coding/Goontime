package com.example.goonbarytime;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.io.FileInputStream;

public class Frag2 extends Fragment {
    private View view;
    private TextView enter;
    private CalendarView calendarView;
    private TextView dateSet;
    private EditText note;
    private TextView save;
    private String fname;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.frag2, container, false);

        calendarView = view.findViewById(R.id.calendarView);
        dateSet = view.findViewById(R.id.dateSet);
        note = view.findViewById(R.id.note);
        save = view.findViewById(R.id.save);

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                dateSet.setVisibility(View.VISIBLE);
                note.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                dateSet.setText(String.format("%d / %d / %d", year, month, dayOfMonth));
                note.setText("");
//                checkDay(year, month, dayOfMonth);
            }
        });

        enter = view.findViewById(R.id.enter);
        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), activity_enlistment.class);
                startActivity(intent);
            }
        });

        return view;
    }

//    public void  checkDay(int cYear,int cMonth,int cDay,String userID){
//        fname=""+userID+cYear+"-"+(cMonth+1)+""+"-"+cDay+".txt";//저장할 파일 이름설정
//        FileInputStream fis=null;//FileStream fis 변수
//
//        try{
//            fis=openFileInput(fname);
//
//            byte[] fileData=new byte[fis.available()];
//            fis.read(fileData);
//            fis.close();
//
//            str=new String(fileData);
//
//            contextEditText.setVisibility(View.INVISIBLE);
//            textView2.setVisibility(View.VISIBLE);
//            textView2.setText(str);
//
//            save_Btn.setVisibility(View.INVISIBLE);
//            cha_Btn.setVisibility(View.VISIBLE);
//            del_Btn.setVisibility(View.VISIBLE);
//
//            cha_Btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    contextEditText.setVisibility(View.VISIBLE);
//                    textView2.setVisibility(View.INVISIBLE);
//                    contextEditText.setText(str);
//
//                    save_Btn.setVisibility(View.VISIBLE);
//                    cha_Btn.setVisibility(View.INVISIBLE);
//                    del_Btn.setVisibility(View.INVISIBLE);
//                    textView2.setText(contextEditText.getText());
//                }
//
//            });
//            del_Btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    textView2.setVisibility(View.INVISIBLE);
//                    contextEditText.setText("");
//                    contextEditText.setVisibility(View.VISIBLE);
//                    save_Btn.setVisibility(View.VISIBLE);
//                    cha_Btn.setVisibility(View.INVISIBLE);
//                    del_Btn.setVisibility(View.INVISIBLE);
//                    removeDiary(fname);
//                }
//            });
//            if(textView2.getText()==null){
//                textView2.setVisibility(View.INVISIBLE);
//                diaryTextView.setVisibility(View.VISIBLE);
//                save_Btn.setVisibility(View.VISIBLE);
//                cha_Btn.setVisibility(View.INVISIBLE);
//                del_Btn.setVisibility(View.INVISIBLE);
//                contextEditText.setVisibility(View.VISIBLE);
//            }
//
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
}
