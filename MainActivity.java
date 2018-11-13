package com.example.a1.zhattyqu2;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.example.a1.zhattyqu2.fragments.StartPage;
import com.example.a1.zhattyqu2.fragments.TasksDetails;
import com.example.a1.zhattyqu2.task_recycler.Task;
import com.example.a1.zhattyqu2.task_recycler.TasksAdapter;


public class MainActivity extends AppCompatActivity implements TasksAdapter.OnSeleceted {

    FragmentManager fm;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        StartPage startPage = new StartPage();

        fm = getSupportFragmentManager();

        fm.beginTransaction().replace(R.id.container, startPage, null).commit();

    }

    @Override
    public void send(Task task) {
        TasksDetails tasksDetails = new TasksDetails();
        Bundle bundle = new Bundle();
        bundle.putParcelable("Task",task);
        tasksDetails.setArguments(bundle);
        fm.beginTransaction().replace(R.id.container, tasksDetails, null).addToBackStack(null).commit();
    }
}