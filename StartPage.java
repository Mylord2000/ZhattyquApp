package com.example.a1.zhattyqu2.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.a1.zhattyqu2.R;

public class StartPage extends Fragment {

    Button buttonTasksToday, completedTasksToday;


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_start_page, container, false);

        buttonTasksToday = rootView.findViewById(R.id.realTasksTodayButton);
        completedTasksToday = rootView.findViewById(R.id.completedTasksTodayButton);

        buttonTasksToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TasksForToday tasksForToday = new TasksForToday();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, tasksForToday,null).addToBackStack(null).commit();

            }
        });


        completedTasksToday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CompletedTasks tasksForToday = new CompletedTasks();

                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, tasksForToday,null).addToBackStack(null).commit();

            }
        });


        return rootView;
    }
}
