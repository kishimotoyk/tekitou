package com.example.kishimoto.tekitou;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RelativeLayout relativeLayout
                        = (RelativeLayout) findViewById(R.id.relativeLayout);
                Snackbar
                        .make(relativeLayout, "Hello, Snackbar!", Snackbar.LENGTH_SHORT)
                        .show();
                Intent intent = new Intent(MainActivity.this, InputMemoActivity.class);
                startActivity(intent);
            }
        });
    }
}
