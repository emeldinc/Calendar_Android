package com.example.monster.calendar_android;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    CalendarView calendarView;
    TextView  myDate;
    RequestQueue requestQueue;
    Appointment appointment;
    List<Appointment> appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = (CalendarView) findViewById(R.id.calendarView);
        myDate = (TextView) findViewById(R.id.myDate);

        requestQueue = Volley.newRequestQueue(this);
        appointments = new ArrayList<Appointment>();

        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 +"/" + i;
                myDate.setText(date);
            }
        });
        String URL = "https://beck-calendar4.herokuapp.com/api/v1/appointments";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("deneme", "onresponse");
                try {
                    JSONArray json = new JSONArray(response);
                    for (int i = 0; i < json.length(); i++) {
                        appointment = new Appointment();
                        JSONObject obj = json.getJSONObject(i);
                        appointment.id = obj.getInt("id");
                        appointment.title = obj.getString("title");
                        appointment.comment = obj.getString("comment");
                        appointment.date = obj.getString("date");
                        appointment.start_time = obj.getString("start_time");
                        appointment.end_time = obj.getString("end_time");
                        appointment.created_at = obj.getString("created_at");
                        appointment.updated_at = obj.getString("updated_at");
                        Log.d("title", appointment.title);
                        appointments.add(appointment);
                    }
                    for(int j = 0; j < appointments.size(); j++) {
                        Log.d("title" + j,appointments.get(j).title + "\n");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "error");
            }
        });
        requestQueue.add(stringRequest);





    }


}
