package com.example.ghazanfarali.asani;

import android.app.Dialog;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class RequestForService extends AppCompatActivity {

    Button submit,date_time_picker;
    TextView date_time_value;
    int year,month,day,hours,minutes,seconds;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_service);
        submit = (Button)findViewById(R.id.submit);
        date_time_picker = (Button)findViewById(R.id.date_time_picker);
        date_time_value = (TextView)findViewById(R.id.date_time_value);
        final Calendar c = Calendar.getInstance();
        year  = c.get(Calendar.YEAR);
        month = c.get(Calendar.MONTH);
        day   = c.get(Calendar.DAY_OF_MONTH);
        hours = c.get(Calendar.HOUR);
        minutes = c.get(Calendar.MINUTE);
        seconds = c.get(Calendar.SECOND);
        date_time_value.setText(new StringBuilder()
                // Month is 0 based, just add 1
                .append(month + 1).append("-").append(day).append("-")
                .append(year).append(" ").append(hours).append(":").append(minutes)/*.append(":").append(seconds)*/
        .append(" "));


        date_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForDateAndTimePicker();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RequestForService.this);
                alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface arg0, int arg1) {
                        Toast.makeText(RequestForService.this,"Thank you for your request our customer service representative will call you shortly", Toast.LENGTH_LONG).show();
                    }
                });

                alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });

                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });
    }

    public void showDialogForDateAndTimePicker()
    {
        final AppCompatDialog date_time_picker = new AppCompatDialog(RequestForService.this);
        date_time_picker.setContentView(R.layout.dialog_time_date);
        final DatePicker datePicker = (DatePicker) date_time_picker.findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) date_time_picker.findViewById(R.id.timePicker);
        Button button = (Button)date_time_picker.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month = datePicker.getMonth();
                year = datePicker.getYear();
                day = datePicker.getDayOfMonth();

                timePicker.getCurrentHour();
                timePicker.getCurrentMinute();
                //timePicker.getC();
                date_time_value.setText(new StringBuilder()
                        // Month is 0 based, just add 1
                        .append(month + 1).append("-").append(day).append("-")
                        .append(year).append(" ").append(hours).append(":").append(minutes)/*.append(":").append(seconds)*/
                        .append(" "));
                date_time_picker.dismiss();
            }
        });
        date_time_picker.show();


    }
}
