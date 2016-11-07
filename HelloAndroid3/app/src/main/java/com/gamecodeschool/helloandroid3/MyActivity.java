package com.gamecodeschool.helloandroid3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
//TODO: Testing out the todo thing.
public class MyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Let's take a lost at Toast and Log in action
        Toast.makeText(this, "Can you see me", Toast.LENGTH_SHORT).show();

        Log.i("info", "Done creating the app");
    }

    public void topClick(View v){
        Toast.makeText(this, "Top button clicked", Toast.LENGTH_SHORT).show();
        Log.i("info", "the user clicked the top button");
    }

    public void bottomClick(View v){
        Toast.makeText(this, "Bottom button clicked", Toast.LENGTH_SHORT).show();
        Log.i("info", "the user clicked the bottom button");
    }
}
