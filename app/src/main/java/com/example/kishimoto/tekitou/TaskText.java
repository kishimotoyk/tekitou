package com.example.kishimoto.tekitou;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

public class TaskText extends RealmObject {
    @PrimaryKey
    private int id;
    @Required
    private String taskText;

    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return id;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getTaskText() {
        return taskText;
    }
}
