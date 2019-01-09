package com.example.kishimoto.tekitou;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class CardRecyclerView extends RecyclerView {
    public CardRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setRecyclerAdapter(context);
    }

    public void setRecyclerAdapter(Context context){
        setLayoutManager(new LinearLayoutManager(context));
        setLayoutManager(new GridLayoutManager(context,2));
        setAdapter(new CardRecyclerAdapter(context,context.getResources().getStringArray(R.array.dummy)));
    }
}
