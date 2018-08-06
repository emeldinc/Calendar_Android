package com.example.monster.calendar_android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddAppointment extends AppCompatActivity {
    Button create;
    EditText text1,text2,text3,text4,text5;
    String title,comment,date,start_time,end_time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addappointment);
        Intent intent = getIntent();

        text1 = (EditText) findViewById(R.id.title);
        text2 = (EditText) findViewById(R.id.comment);
        text3 = (EditText) findViewById(R.id.date);
        text4 = (EditText) findViewById(R.id.start_time);
        text5 = (EditText) findViewById(R.id.end_time);
        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage(view);
            }
        });

    }
    public void sendMessage(View view){
        title=text1.getText().toString();
        comment=text2.getText().toString();
        date=text3.getText().toString();
        start_time = text4.getText().toString();
        end_time=text5.getText().toString();
        String app = title+" "+comment+" "+date + " " +start_time+" "+end_time+" "+end_time;
        TextView tv = (TextView) findViewById( R.id.tv );
        tv.setText(app);
       // System.out.println(title+" "+comment+" "+date + " " +start_time+" "+end_time+" "+end_time);

    }
    public void backMain(View view){

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

}

