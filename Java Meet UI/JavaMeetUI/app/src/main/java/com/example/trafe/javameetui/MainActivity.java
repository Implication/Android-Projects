package com.example.trafe.javameetui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
    implements View.OnClickListener{
    private int value = 0;

    private Button
        btnAdd,
        btnTake,
        btnGrow,
        btnShrink,
        btnReset,
        btnHide;

    private TextView txtValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnAdd = (Button) findViewById(R.id.btnAdd);
        btnGrow = (Button) findViewById(R.id.btnGrow);
        btnHide  = (Button) findViewById(R.id.btnHide);
        btnTake = (Button) findViewById(R.id.btnTake);
        btnReset = (Button) findViewById(R.id.btnReset);
        btnShrink = (Button) findViewById(R.id.btnShrink);
        txtValue = (TextView) findViewById(R.id.txtValue);
        btnAdd.setOnClickListener(this);
        btnShrink.setOnClickListener(this);
        btnReset.setOnClickListener(this);
        btnTake.setOnClickListener(this);
        btnHide.setOnClickListener(this);
        btnGrow.setOnClickListener(this);
        txtValue.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        float size;

        switch(v.getId()){
            case R.id.btnAdd:
                value++;
                txtValue.setText("" + value);
                break;
            case R.id.btnTake:
                value--;
                txtValue.setText("" + value);
                break;
            case R.id.btnReset:
                value = 0;
                txtValue.setText("" + value);
                break;
            case R.id.btnGrow:
                size = txtValue.getTextScaleX();
                txtValue.setTextScaleX(size + 1);
                break;
            case R.id.btnShrink:
                size = txtValue.getTextScaleX();
                txtValue.setTextScaleX(size - 1);
                break;
            case R.id.btnHide:
                if(txtValue.getVisibility() == View.VISIBLE){
                    txtValue.setVisibility(View.INVISIBLE);

                    //Chnage the text on the button after hiding it
                    btnHide.setText("SHOW");
                }else{
                    txtValue.setVisibility(View.VISIBLE);
                    btnHide.setText("HIDE");
                }

                break;

        }
    }
}
