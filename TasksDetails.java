package com.example.a1.zhattyqu2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.a1.zhattyqu2.Alarm.AlarmDialog;
import com.example.a1.zhattyqu2.R;
import com.example.a1.zhattyqu2.task_recycler.Task;

import java.util.Locale;

public class TasksDetails extends Fragment implements View.OnClickListener{

    TextView titlem, placem, startt, endt, prioritym, statusm, notem, timer, alarmTextView;
    Button minus, plus, startb, stopb, alarmButton;

    CountDownTimer countDownTimer;
    boolean timerRunnig;
    long timeLeftMillis = 1500000;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_tasks_details, container, false);

        alarmButton = rootView.findViewById(R.id.alarm);
        alarmTextView = rootView.findViewById(R.id.alarmTextView);
        titlem = rootView.findViewById(R.id.title);
        placem = rootView.findViewById(R.id.place);
        startt = rootView.findViewById(R.id.start);
        endt = rootView.findViewById(R.id.end);
        prioritym = rootView.findViewById(R.id.priority);
        statusm = rootView.findViewById(R.id.status);
        notem = rootView.findViewById(R.id.note);
        timer = rootView.findViewById(R.id.timer);

        minus = rootView.findViewById(R.id.minus);
        plus = rootView.findViewById(R.id.plus);
        startb= rootView.findViewById(R.id.startTimer);
        stopb= rootView.findViewById(R.id.resetTimer);

        alarmButton.setOnClickListener(this);
        startb.setOnClickListener(this);
        stopb.setOnClickListener(this);
        minus.setOnClickListener(this);
        plus.setOnClickListener(this);

        Task task = getArguments().getParcelable("Task");

        titlem.setText(task.getTitle());
        placem.setText(task.getPlace());
        startt.setText(task.getStart_month_day() + "." + task.getStart_month() + "." + task.getStart_year() + ", " + task.getStart_time_hour() + ":" + task.getStart_time_minute());
        endt.setText(task.getEnd_month_day() + "." + task.getEnd_month() + "." + task.getEnd_year() + ", " + task.getEnd_time_hour() + ":" + task.getEnd_time_minute());
        prioritym.setText(task.getPriority() + "");
        if(task.getStatus().toString().equals("uncompleted")){statusm.setText("Не выполнен");}
        else{statusm.setText("Bыполнен");}
        notem.setText("Примечаине: " + task.getNote());

        return  rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.plus:
                if(!timerRunnig){
                    timeLeftMillis = timeLeftMillis + 60000;
                updateCountDownText();}
                break;
            case R.id.minus:
                if(!timerRunnig){
                    timeLeftMillis = timeLeftMillis - 60000;
                updateCountDownText();}
                break;
            case R.id.startTimer:
                if(timerRunnig){pauseTimer();}
                else{startTimer();}
                break;
            case R.id.resetTimer:
                resetTimer();
                updateCountDownText();
                break;
            case R.id.alarm:
                AlarmDialog alarmDialog = new AlarmDialog();
                alarmDialog.show(getFragmentManager(), "please work");
                break;
        }
    }

    public void pauseTimer(){

        countDownTimer.cancel();
        timerRunnig = false;
        startb.setText("Start");

    }

    public void startTimer(){

        countDownTimer = new CountDownTimer(timeLeftMillis,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                startb.setText("Start");
                timerRunnig = true;

            }
        }.start();

        startb.setText("Pause");
        timerRunnig = true;

    }

    public void resetTimer(){


        timeLeftMillis = 1500000;
        updateCountDownText();

    }

    public void updateCountDownText(){

        int minute = (int) (timeLeftMillis/1000)/60;
        int second = (int) (timeLeftMillis/1000)%60;

        String time = String.format(Locale.getDefault(),"%02d:%02d",minute,second);
        timer.setText(time);
    }
}
