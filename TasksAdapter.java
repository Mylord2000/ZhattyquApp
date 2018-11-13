package com.example.a1.zhattyqu2.task_recycler;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.a1.zhattyqu2.R;
import com.example.a1.zhattyqu2.fragments.TasksDetails;
import com.example.a1.zhattyqu2.fragments.TasksForToday;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.ExTask> {

    List<Task> mtasks;
    Fragment fragment;
    Context context;
    OnSeleceted onSeleceted;
    OnInputSeleceted onInputSeleceted;

    public interface OnInputSeleceted{

        void sendInput(int input);

    }


    public interface OnSeleceted{

        void send(Task task);

    }

    public TasksAdapter(Fragment fragment,List<Task> tasks) {
        this.mtasks = tasks;
        this.fragment = fragment;
        onSeleceted = (OnSeleceted)fragment.getActivity();

    }

    @NonNull
    @Override
    public ExTask onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        final int t = i;
        context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_every_task,viewGroup,false);



        ExTask task = new ExTask(view, mtasks);



        return task;

    }

    @Override
    public void onBindViewHolder(@NonNull final TasksAdapter.ExTask task, int i) {
        task.bind(i);


            // holder.checkBox.setTag(R.integer.btnplusview, convertView);
            task.checkBox.setTag(i);
            task.checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Integer pos = (Integer) task.checkBox.getTag();



                    notifyDataSetChanged();
                    if (mtasks.get(pos).getSelected()) {

                        Toast.makeText(context, mtasks.size()+"", Toast.LENGTH_SHORT).show();
                        mtasks.get(pos).setSelected(false);
                    } else {

                        try {

                            Log.i("send adapter", "work");
                            onInputSeleceted =(OnInputSeleceted)fragment;
                            onInputSeleceted.sendInput(mtasks.get(pos).getId());
                        }catch(Exception e){
                            Log.i("ERROrR","ERROR" + e);}

                        Toast.makeText(context, "Congratulation!!!", Toast.LENGTH_SHORT).show();
                    }
                }
            });





    }

    @Override
    public int getItemCount() {
        return mtasks.size();
    }




    public class ExTask extends RecyclerView.ViewHolder implements View.OnClickListener{


        TextView title;
        TextView time;
        TextView comment;
        CheckBox checkBox;

        public ExTask(@NonNull final View itemView, final List<Task> tasks) {
            super(itemView);
            itemView.setOnClickListener(this);
            checkBox = itemView.findViewById(R.id.everyTasksCheckbox);
            title = itemView.findViewById(R.id.everyTasksTitle);
            time = itemView.findViewById(R.id.everyTasksData);
            comment = itemView.findViewById(R.id.everyTasksComment);




        }



        public void  bind(int position){



            title.setText(mtasks.get(position).title);

            time.setText("с " + mtasks.get(position).start_month_day + "." + mtasks.get(position).start_month + "." +
                    mtasks.get(position).start_year + ", " + mtasks.get(position).start_time_hour + ":" +mtasks.get(position).start_time_minute + " до " + mtasks.get(position).end_month_day +
                    "." + mtasks.get(position).end_month + "."  + mtasks.get(position).end_year + ", " + mtasks.get(position).end_time_hour + ":" +mtasks.get(position).end_time_minute );
            comment.setText(mtasks.get(position).place);

        }





        @Override
        public void onClick(View v) {




            if (onSeleceted != null){
                Log.i("onSendData", "work in forecast adapter");
                onSeleceted.send(mtasks.get(getAdapterPosition()));}
            else{Log.i("onSendData", " dont work");}

        }


    }
}
