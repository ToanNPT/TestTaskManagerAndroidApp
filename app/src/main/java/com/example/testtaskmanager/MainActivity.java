package com.example.testtaskmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listView);

        setData();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        setData();
    }

    public void setData( ){
        Cursor cursor = getContentResolver().query(Uri.parse("content://com.example.taskmanager.TaskProvider/tasks"), null, null, null, null);
        System.out.println(cursor.getCount());
        if(cursor.moveToFirst()) {
            ArrayList<String> tasks = new ArrayList<String>();

            while (!cursor.isAfterLast()) {
                tasks.add(cursor.getString(Integer.valueOf(cursor.getColumnIndex("NAME"))) +" - " + cursor.getString(Integer.valueOf(cursor.getColumnIndex("CONTENT"))));
                cursor.moveToNext();
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tasks);
            listView.setAdapter(adapter);
        }
        else {
            listView.setAdapter(null);
        }
    }


}