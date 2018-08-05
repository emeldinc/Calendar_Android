package com.example.monster.calendar_android;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.widget.CalendarView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


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
    TableLayout showAppointmentsTable;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = getApplicationContext();

        calendarView = findViewById(R.id.calendarView);
        //myDate = findViewById(R.id.myDate);
        //postData = (Button) findViewById(R.id.data);
        showAppointmentsTable = findViewById(R.id.appointments);

        requestQueue = Volley.newRequestQueue(this);
        appointments = new ArrayList<Appointment>();

        /*calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                String date = (i1 + 1) + "/" + i2 +"/" + i;
                myDate.setText(date);
            }
        });*/
        final String URL = "https://beck-calendar4.herokuapp.com/api/v1/appointments";
        StringRequest stringGETRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
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
                        appointment.start_time = appointment.start_time.substring(11, 16);
                        appointment.end_time = obj.getString("end_time");
                        appointment.end_time = appointment.end_time.substring(11, 16);
                        appointment.created_at = obj.getString("created_at");
                        appointment.updated_at = obj.getString("updated_at");
                        appointments.add(appointment);
                    }
                    for(int j = 0; j < appointments.size(); j++) {
                        Log.d("title" + j,appointments.get(j).title + "\n");
                        Appointment app = appointments.get(j);
                        TableRow row = new TableRow(context);
                        TextView tv_title = new TextView(context);
                        TextView tv_comment = new TextView(context);
                        TextView tv_date = new TextView(context);
                        TextView tv_start = new TextView(context);
                        TextView tv_end = new TextView(context);
                        tv_title.setText(app.title);
                        tv_comment.setText(app.comment);
                        tv_date.setText(app.date);
                        tv_start.setText(app.start_time);
                        tv_end.setText(app.end_time);
                        tv_title.setPadding(1, 1, 1, 1);
                        tv_comment.setPadding(1, 1, 1, 1);
                        tv_date.setPadding(1, 1, 1, 1);
                        tv_start.setPadding(1, 1, 1, 1);
                        tv_end.setPadding(1, 1, 1, 1);
                        tv_title.setGravity(Gravity.LEFT);
                        tv_comment.setGravity(Gravity.CENTER);
                        tv_date.setGravity(Gravity.CENTER);
                        tv_start.setGravity(Gravity.CENTER);
                        tv_end.setGravity(Gravity.RIGHT);
                        row.addView(tv_title);
                        row.addView(tv_comment);
                        row.addView(tv_date);
                        row.addView(tv_start);
                        row.addView(tv_end);
                        showAppointmentsTable.addView(row);

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
        requestQueue.add(stringGETRequest);


        // TODO Post appointment
        /*
        final Appointment postDeneme = new Appointment();
        postDeneme.title = "andPostdeneme";
        postDeneme.start_time = "2000-01-01T12:00:00.000Z";
        postDeneme.end_time = "2000-01-01T13:00:00.000Z";
        postDeneme.comment = "andpostcomment";
        postDeneme.created_at = "2018-08-04T13:20:29.383Z";
        postDeneme.updated_at = "2018-08-04T13:20:29.383Z";
        postData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest stringPOSTRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("success", "success");

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d("error", "error");
                    }
                }) {
                @Override
                protected Map<String,String> getParams(){
                    Map<String,String> params = new HashMap<String, String>();
                    params.put("title",postDeneme.title);
                    params.put("start_time",postDeneme.start_time);
                    params.put("end_time", postDeneme.end_time);
                    params.put("created_at", postDeneme.created_at);
                    params.put("updated_at", postDeneme.updated_at);
                    params.put("comment",postDeneme.comment);
                    params.put("id", "19");

                    return params;
                }

                };
                requestQueue.add(stringPOSTRequest);
            }
        });*/







    }


}
