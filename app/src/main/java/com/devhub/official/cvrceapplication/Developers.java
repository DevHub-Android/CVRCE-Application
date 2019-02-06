package com.devhub.official.cvrceapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.devhub.official.cvrceapplication.Adapters.Adapter_Developer;

public class Developers extends AppCompatActivity {
    private RecyclerView recyclerView;
    private  RecyclerView.LayoutManager mLayoutManager;
    private  int[] images = {R.drawable.akash,R.drawable.rakesh,R.drawable.sonali,
            R.drawable.jitu};
    private Adapter_Developer devAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developers);
        recyclerView = findViewById(R.id.devRecycler);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(mLayoutManager);
        devAdapter = new Adapter_Developer(images);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.swing_up_left);
        recyclerView.setAnimation(animation);
        recyclerView.setAdapter(devAdapter);


    }
}
