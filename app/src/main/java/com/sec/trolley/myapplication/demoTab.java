package com.sec.trolley.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class demoTab extends AppCompatActivity implements View.OnClickListener {

    String[] tab1 = {"Rahul", "Pooja", "Rachana"};
    String[] tab2 = {"Rahul", "Pooja", "Rachana", "Ankita"};
    String[] tab3 = {"Rahul", "Pooja", "Rachana", "Mammy", "Papa"};
    String[] tab4 = {"Rahul", "Pooja", "Rachana", "Ankita", "2", "3", "4"};
    String[] tab5 = {"Rahul", "Pooja", "Rachana", "5", "6", "7", "8"};
    String[] tab6 = {"Rahul", "Pooja", "Rachana", "Ankita", "12", "13", "14", "15", "19"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_tab);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        findViewById(R.id.button5).setOnClickListener(this);
        findViewById(R.id.button6).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, HomeActivity.class);
        switch (v.getId()) {
            case R.id.button1:
                intent.putExtra("string-array", tab1);
                break;
            case R.id.button2:
                intent.putExtra("string-array", tab2);
                break;
            case R.id.button3:
                intent.putExtra("string-array", tab3);
                break;
            case R.id.button4:
                intent.putExtra("string-array", tab4);
                break;
            case R.id.button5:
                intent.putExtra("string-array", tab5);
                break;
            case R.id.button6:
                intent.putExtra("string-array", tab6);
                break;
        }
        startActivity(intent);
    }
    //need to check again
}
