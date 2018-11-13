package com.example.a1.zhattyqu2.fragments;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.a1.zhattyqu2.DBHelper;
import com.example.a1.zhattyqu2.R;
import com.example.a1.zhattyqu2.task_recycler.Task;
import com.example.a1.zhattyqu2.task_recycler.TasksAdapter;

import java.util.ArrayList;
import java.util.List;

public class CompletedTasks extends Fragment {

    RecyclerView rv;
    List<Task> real_tasks;
    DBHelper db;
    SQLiteDatabase database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_completed_tasks, container, false);
        db = new DBHelper(CompletedTasks.this.getContext());


        rv = rootView.findViewById(R.id.completedTasksRecycler);

        real_tasks = new ArrayList<Task>();

        getDATA();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rv.setLayoutManager(layoutManager);
        rv.addItemDecoration(new DividerItemDecoration(rv.getContext(), DividerItemDecoration.VERTICAL));


        getDATA();
        TasksAdapter ta = new TasksAdapter(CompletedTasks.this, real_tasks);

        rv.setAdapter(ta);

        db.close();
        return rootView;
    }

    private void getDATA() {
        database = db.getWritableDatabase();

        real_tasks.clear();

        Cursor cursor = database.query(db.DATABASE_TASKS, null, null, null, null, null, null);

        if(cursor.moveToFirst() &&  real_tasks.isEmpty()){

            int idIndex = cursor.getColumnIndex("_id");
            int titleIndex = cursor.getColumnIndex("title");
            int placeIndex = cursor.getColumnIndex("place");
            int noteIndex = cursor.getColumnIndex("note");
            int statusIndex = cursor.getColumnIndex("status");
            int priorityIndex = cursor.getColumnIndex("priority");
            int start_yearIndex = cursor.getColumnIndex("start_year");
            int start_monthIndex = cursor.getColumnIndex("start_month");
            int start_month_dayIndex = cursor.getColumnIndex("start_month_day");
            int start_time_hourIndex = cursor.getColumnIndex("start_time_hour");
            int start_time_minuteIndex = cursor.getColumnIndex("start_time_minute");
            int end_yearIndex = cursor.getColumnIndex("end_year");
            int end_monthIndex = cursor.getColumnIndex("end_month");
            int end_month_dayIndex = cursor.getColumnIndex("end_month_day");
            int end_time_hourIndex = cursor.getColumnIndex("end_time_hour");
            int end_time_minuteIndex = cursor.getColumnIndex("end_time_minute");

            Task special_task;
            do{

                special_task = new Task();

                if(cursor.getString(statusIndex).toString().equals("completed")){
                    special_task.setId(cursor.getInt(idIndex));
                    special_task.setTitle(cursor.getString(titleIndex));
                    special_task.setPlace(cursor.getString(placeIndex));
                    special_task.setStatus(cursor.getString(statusIndex));
                    special_task.setNote(cursor.getString(noteIndex));
                    special_task.setPriority(cursor.getInt(priorityIndex));
                    special_task.setStart_year(cursor.getInt(start_yearIndex));
                    special_task.setStart_month(cursor.getInt(start_monthIndex));
                    special_task.setStart_month_day(cursor.getInt(start_month_dayIndex));
                    special_task.setStart_time_hour(cursor.getInt(start_time_hourIndex));
                    special_task.setStart_time_minute(cursor.getInt(start_time_minuteIndex));
                    special_task.setEnd_year(cursor.getInt(end_yearIndex));
                    special_task.setEnd_month(cursor.getInt(end_monthIndex));
                    special_task.setEnd_month_day(cursor.getInt(end_month_dayIndex));
                    special_task.setEnd_time_hour(cursor.getInt(end_time_hourIndex));
                    special_task.setEnd_time_minute(cursor.getInt(end_time_minuteIndex));
                    real_tasks.add(special_task);
                    Log.i("idTask", String.valueOf(special_task.getId()));}
            }while(cursor.moveToNext());


        }

    }
}
