package com.example.a1.zhattyqu2.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.a1.zhattyqu2.DBHelper;
import com.example.a1.zhattyqu2.R;

import java.util.Calendar;

public class CretingTask extends Fragment implements View.OnClickListener, DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener{

    DBHelper db;
    SQLiteDatabase database;
    EditText title_e, note_e;
    TextView priorityT,start1,start2,end1,end2;
    ImageView start3,end3;
    Button greatCreator;
    ContentValues contentValues;
    Spinner spinner;
    SeekBar seekBar;

    String timer, fplace;

    String []data = {"В офисе","На учебе","В гараже","В сети","Дома","С утра" , "Когда есть 15 мин"};

    int syear,smonth,sday,shour,sminute, priorityInt;
    int fyear,fmonth,fday,fhour,fminute;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_creting_task, container, false);
        db = new DBHelper(CretingTask.this.getContext());
        database = db.getWritableDatabase();
        contentValues = new ContentValues();

        seekBar = rootView.findViewById(R.id.seekbar);
        priorityT = rootView.findViewById(R.id.priorityTextView);
        greatCreator = rootView.findViewById(R.id.greatCreatorButton);
        title_e = rootView.findViewById(R.id.titleOfTask);
        note_e = rootView.findViewById(R.id.noteOfTask);
        spinner = rootView.findViewById(R.id.placeOfTask);
        start1 = rootView.findViewById(R.id.startTimeOfTaskTextView);
        start2 = rootView.findViewById(R.id.startTimeOfTaskTextView1);
        start3 = rootView.findViewById(R.id.startTimeOfTaskTextView2);
        end1 = rootView.findViewById(R.id.endTimeOfTaskTextView);
        end2 = rootView.findViewById(R.id.endTimeOfTaskTextView1);
        end3 = rootView.findViewById(R.id.endTimeOfTaskTextView2);

        start1.setOnClickListener(this);
        start2.setOnClickListener(this);
        start3.setOnClickListener(this);
        end1.setOnClickListener(this);
        end2.setOnClickListener(this);
        end3.setOnClickListener(this);
        greatCreator.setOnClickListener(this);

        ArrayAdapter<String> LTRadapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, data);
        LTRadapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);

        spinner.setAdapter(LTRadapter);
        spinner.setPrompt("Categorogy");
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                fplace = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Log.i("work","onNothingSelected");
            }


        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                priorityT.setText("приоритет: " + progress);
                priorityInt = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){


            case R.id.startTimeOfTaskTextView:
                startTimer();
                break;
            case R.id.startTimeOfTaskTextView1:
                startTimer();
                break;

            case R.id.startTimeOfTaskTextView2:
                startTimer();
                break;
            case R.id.endTimeOfTaskTextView:
                endTimer();

                break;
            case R.id.endTimeOfTaskTextView1:
                endTimer();
                break;
            case R.id.endTimeOfTaskTextView2:
                endTimer();
                break;
            case R.id.greatCreatorButton:

                contentValues.put("title",title_e.getText().toString());
                contentValues.put("place",fplace);
                contentValues.put("note",note_e.getText().toString());
                contentValues.put("status","uncompleted");
                contentValues.put("priority",priorityInt);
                contentValues.put("start_year",syear);
                contentValues.put("start_month",smonth);
                contentValues.put("start_month_day",sday);
                contentValues.put("start_time_hour",shour);
                contentValues.put("start_time_minute",sminute);
                contentValues.put("end_year",fyear);
                contentValues.put("end_month",fmonth);
                contentValues.put("end_month_day",fday);
                contentValues.put("end_time_hour",fhour);
                contentValues.put("end_time_minute",fminute);

                database.insert(db.DATABASE_TASKS,null,contentValues);
                db.close();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container,new TasksForToday(),null).commit();
                break;


        }
    }

    private void startTimer() {
        Calendar c = Calendar.getInstance();

        DatePickerDialog datePickerDialog;


        timer = "start";

        syear = c.get(Calendar.YEAR);
        smonth = c.get(Calendar.MONTH);
        sday = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(CretingTask.this.getContext(), CretingTask.this,syear,smonth,sday );
        datePickerDialog.show();


    }

    private void endTimer() {
        Calendar c = Calendar.getInstance();

        DatePickerDialog datePickerDialog;


        timer = "end";

        fyear = c.get(Calendar.YEAR);
        fmonth = c.get(Calendar.MONTH);
        fday = c.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = new DatePickerDialog(CretingTask.this.getContext(), CretingTask.this,fyear,fmonth,fday );
        datePickerDialog.show();


    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c = Calendar.getInstance();
        TimePickerDialog timePickerDialog;
        if(timer.equals("start")){

            syear = year;
            smonth = month+1;
            sday = dayOfMonth;


            shour = c.get(Calendar.HOUR_OF_DAY);
            syear = c.get(Calendar.MINUTE);

            timePickerDialog = new TimePickerDialog(CretingTask.this.getContext(), CretingTask.this,shour,sminute, DateFormat.is24HourFormat(this.getContext()));
            timePickerDialog.show();


        }else{
            fyear = year;
            fmonth = month+1;
            fday = dayOfMonth;


            fhour = c.get(Calendar.HOUR_OF_DAY);
            fyear = c.get(Calendar.MINUTE);

            timePickerDialog = new TimePickerDialog(CretingTask.this.getContext(), CretingTask.this,fhour,fminute, DateFormat.is24HourFormat(this.getContext()));
            timePickerDialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        if(timer.equals("start")){

            shour = hourOfDay;
            sminute = minute;

            start2.setText(sday + "." + smonth+ "."+syear + ", " +shour + ":" + sminute);
        }else{


            fhour = hourOfDay;
            fminute = minute;

            end2.setText(fday + "." + fmonth+ "."+fyear + ", " +fhour + ":" + fminute);

        }
    }
}
