package com.example.kishimoto.tekitou;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

public class CardRecyclerView extends RecyclerView {
    public CardRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Realm.init(getContext());
        Realm realm = Realm.getDefaultInstance();
        setRecyclerAdapter(context,realm);
    }

    public void setRecyclerAdapter(Context context,Realm realm){
        setLayoutManager(new LinearLayoutManager(context));
        setLayoutManager(new GridLayoutManager(context,2));

        ArrayList<String> taskList = new ArrayList<String>();

        RealmResults<TaskText> Task = realm.where(TaskText.class).findAll();
        for(int i = 0;i <= Task.size();i++){
            TaskText taskmemo = Task.where().equalTo("id",i).findFirst();
            try {
                taskList.add(taskmemo.getTaskText());
            } catch (NullPointerException e){

            }
        }

        String[] array = taskList.toArray(new String[Task.size()]);

        setAdapter(new CardRecyclerAdapter(context,array));
    }
}
