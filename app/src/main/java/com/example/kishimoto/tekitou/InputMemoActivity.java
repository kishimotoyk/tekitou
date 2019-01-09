package com.example.kishimoto.tekitou;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.EditText;

import org.xmlpull.v1.XmlSerializer;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import io.realm.Realm;

public class InputMemoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_memo);
    }
    public void onClick(View v){
        EditText editText = (EditText)findViewById(R.id.editText);
        String memoText = editText.getText().toString();


    }

    public void writeXml(@Nullable int id, String memoText) {
        final Realm realm = Realm.getDefaultInstance();
        if (id == null) {
            id = makeId();
        }
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipe.setName(recipeName);
        recipe.setCookingTime(cookingTime);

        mRealm.beginTransaction();

        mRealm.copyToRealmOrUpdate(recipe);

        mRealm.commitTransaction();
    }
}
