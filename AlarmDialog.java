package com.example.a1.zhattyqu2.Alarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.a1.zhattyqu2.R;

import java.util.Calendar;

import static android.support.v4.content.ContextCompat.getSystemService;

public class AlarmDialog extends DialogFragment{

    TimePicker timePicker;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.layout_timepicker_dialog, container, false);

        timePicker = rootView.findViewById(R.id.timePicker);

        rootView.findViewById(R.id.setAlarm).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("onClickcc", "work");
                Calendar calendar = Calendar.getInstance();

                if(Build.VERSION.SDK_INT >= 23){

                    Log.i("SdkINtt", "work");

                    calendar.set(
                            calendar.get(calendar.YEAR),
                            calendar.get(calendar.MONTH),
                            calendar.get(calendar.DAY_OF_MONTH),
                            timePicker.getHour(),
                            timePicker.getMinute(),0
                    );

                }else{
                    calendar.set(
                            calendar.get(calendar.YEAR),
                            calendar.get(calendar.MONTH),
                            calendar.get(calendar.DAY_OF_MONTH),
                            timePicker.getCurrentHour(),
                            timePicker.getCurrentMinute(),0
                    );
                }

                setAlarm(calendar.getTimeInMillis());

            }
        });

     return rootView;
    }

    private void setAlarm(long timeInMillis){

        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), MyAlarm.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getContext(), 0, intent,0);

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, timeInMillis, AlarmManager.INTERVAL_DAY, pendingIntent);

        Toast.makeText(getContext(),"alarm is set", Toast.LENGTH_LONG).show();

    }
}
