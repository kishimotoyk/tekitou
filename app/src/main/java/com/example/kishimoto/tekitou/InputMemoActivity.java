package com.example.kishimoto.tekitou;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.provider.CalendarContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TimePicker;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import io.realm.Realm;
import io.realm.RealmResults;

public class InputMemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_memo);
    }

    public void onClick(View v){
        EditText editText = (EditText)findViewById(R.id.editText);
        String memoText = editText.getText().toString();

        writeXml(memoText);

        Intent intent = new Intent(InputMemoActivity.this, MainActivity.class);
        startActivity(intent);

    }

    public void onClickBack(View v){
        Intent intent = new Intent(InputMemoActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void onClickReminder(View v){
        final Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(
                this,
                new TimePickerDialog.OnTimeSetListener(){
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        Log.d("test",String.format("%02d:%02d", hourOfDay,minute));
                        Calendar calender = Calendar.getInstance();
                        int hourofnow = calender.get(Calendar.HOUR_OF_DAY);
                        int minuteofnow = calender.get(Calendar.MINUTE);
                        int totalminute = (hourofnow * 60 + minuteofnow) - (hourOfDay * 60 + minute);
                        int hourofalarm = totalminute / 60;
                        int minuteofalarm = totalminute % 60;

                        Calendar alarmCalendar = Calendar.getInstance();
                        alarmCalendar.setTimeInMillis(System.currentTimeMillis());
                        alarmCalendar.add(Calendar.HOUR_OF_DAY, hourofalarm);
                        alarmCalendar.add(Calendar.MINUTE,minuteofalarm);
                        scheduleNotification(String.valueOf(hourofalarm) + "時間" + String.valueOf(minuteofalarm) + "分後にセットしました", alarmCalendar);
                        Log.d("aaa",String.valueOf(hourofalarm) + "時間" + String.valueOf(minuteofalarm) + "分後にセットしました");
                    }
                },
                hour,minute,true);
        dialog.show();
    }

    private void scheduleNotification(String content, Calendar calendar){
        Intent notificationIntent = new Intent(this, AlarmReceiver.class);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmReceiver.NOTIFICATION_CONTENT, content);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    public void writeXml(String memoText) {
        final Realm realm = Realm.getDefaultInstance();
        int id = makeId(realm);
        realm.beginTransaction();
        TaskText taskText = realm.createObject(TaskText.class,id);
        taskText.setTaskText(memoText);
        realm.copyToRealmOrUpdate(taskText);

        realm.commitTransaction();
    }

    public void writeXml(int id, String memoText) {
        final Realm realm = Realm.getDefaultInstance();
        TaskText taskText = realm.createObject(TaskText.class);
        taskText.setId(id);
        taskText.setTaskText(memoText);
        realm.beginTransaction();

        realm.copyToRealmOrUpdate(taskText);

        realm.commitTransaction();
    }

    public int makeId(Realm realm) {
        RealmResults<TaskText> results = realm.where(TaskText.class).findAll();
        int id = results.size();
        return id;
    }


}
