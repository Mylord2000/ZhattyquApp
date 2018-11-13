package com.example.a1.zhattyqu2.task_recycler;

import android.os.Parcel;
import android.os.Parcelable;

public class Task implements Parcelable{

    int id;
    boolean selected;

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    String title;
    String note;
    String place;
    String type;
    String status;
    int priority;
    int start_year;
    int start_month;
    int start_month_day;
    int start_time_hour;
    int start_time_minute;
    int end_year;
    int end_month;
    int end_month_day;
    int end_time_hour;
    int end_time_minute;

    public Task(int id, String title, String note, String place, String type, String status, int priority, int start_year, int start_month, int start_month_day, int start_time_hour, int start_time_minute, int end_year, int end_month, int end_month_day, int end_time_hour, int end_time_minute) {
        this.id = id;
        this.title = title;
        this.note = note;
        this.place = place;
        this.type = type;
        this.status = status;
        this.priority = priority;
        this.start_year = start_year;
        this.start_month = start_month;
        this.start_month_day = start_month_day;
        this.start_time_hour = start_time_hour;
        this.start_time_minute = start_time_minute;
        this.end_year = end_year;
        this.end_month = end_month;
        this.end_month_day = end_month_day;
        this.end_time_hour = end_time_hour;
        this.end_time_minute = end_time_minute;
    }

    public Task(){};

    protected Task(Parcel in) {
        id = in.readInt();
        title = in.readString();
        note = in.readString();
        place = in.readString();
        type = in.readString();
        status = in.readString();
        priority = in.readInt();
        start_year = in.readInt();
        start_month = in.readInt();
        start_month_day = in.readInt();
        start_time_hour = in.readInt();
        start_time_minute = in.readInt();
        end_year = in.readInt();
        end_month = in.readInt();
        end_month_day = in.readInt();
        end_time_hour = in.readInt();
        end_time_minute = in.readInt();
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getStart_year() {
        return start_year;
    }

    public void setStart_year(int start_year) {
        this.start_year = start_year;
    }

    public int getStart_month() {
        return start_month;
    }

    public void setStart_month(int start_month) {
        this.start_month = start_month;
    }

    public int getStart_month_day() {
        return start_month_day;
    }

    public void setStart_month_day(int start_month_day) {
        this.start_month_day = start_month_day;
    }

    public int getStart_time_hour() {
        return start_time_hour;
    }

    public void setStart_time_hour(int start_time_hour) {
        this.start_time_hour = start_time_hour;
    }

    public int getStart_time_minute() {
        return start_time_minute;
    }

    public void setStart_time_minute(int start_time_minute) {
        this.start_time_minute = start_time_minute;
    }

    public int getEnd_year() {
        return end_year;
    }

    public void setEnd_year(int end_year) {
        this.end_year = end_year;
    }

    public int getEnd_month() {
        return end_month;
    }

    public void setEnd_month(int end_month) {
        this.end_month = end_month;
    }

    public int getEnd_month_day() {
        return end_month_day;
    }

    public void setEnd_month_day(int end_month_day) {
        this.end_month_day = end_month_day;
    }

    public int getEnd_time_hour() {
        return end_time_hour;
    }

    public void setEnd_time_hour(int end_time_hour) {
        this.end_time_hour = end_time_hour;
    }

    public int getEnd_time_minute() {
        return end_time_minute;
    }

    public void setEnd_time_minute(int end_time_minute) {
        this.end_time_minute = end_time_minute;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(title);
        dest.writeString(note);
        dest.writeString(place);
        dest.writeString(type);
        dest.writeString(status);
        dest.writeInt(priority);
        dest.writeInt(start_year);
        dest.writeInt(start_month);
        dest.writeInt(start_month_day);
        dest.writeInt(start_time_hour);
        dest.writeInt(start_time_minute);
        dest.writeInt(end_year);
        dest.writeInt(end_month);
        dest.writeInt(end_month_day);
        dest.writeInt(end_time_hour);
        dest.writeInt(end_time_minute);
    }
}
