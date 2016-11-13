package com.example.ghazanfarali.asani;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialog;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.ghazanfarali.asani.R.id.tabHost;

public class RequestForService extends AppCompatActivity {

    Button submit,date_time_picker;
    TextView date_time_value;
    int year,month,day,hours,minutes,seconds;
    EditText fullName,email,phone,address;
    Date currentDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_for_service);
        submit = (Button)findViewById(R.id.submit);
        fullName = (EditText)findViewById(R.id.fullName);
        email = (EditText)findViewById(R.id.email);
        phone = (EditText)findViewById(R.id.phone);
        address = (EditText)findViewById(R.id.address);
        date_time_picker = (Button)findViewById(R.id.date_time_picker);
        date_time_value = (TextView)findViewById(R.id.date_time_value);

        Bundle bundle = getIntent().getExtras();
        final String ServiceName = bundle.getString("ServiceName");

        TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
        final String imei = telephonyManager.getDeviceId();

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
        //SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        currentDate = c.getTime(); //df.format(c.getTime());

        date_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialogForDateAndTimePicker();
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(fullName.getText().length() == 0)
                {
                    Toast.makeText(RequestForService.this, "Please Fill the required Fields", Toast.LENGTH_SHORT).show();
                }else if(phone.getText().length() == 0)
                {
                    Toast.makeText(RequestForService.this, "Please Fill the required Fields", Toast.LENGTH_SHORT).show();

                }else if(address.getText().length() == 0)
                {
                    Toast.makeText(RequestForService.this, "Please Fill the required Fields", Toast.LENGTH_SHORT).show();
                }else {

                    final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(RequestForService.this);
                    alertDialogBuilder.setMessage("Are you sure,You wanted to make decision");

                    alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface arg0, int arg1) {
                            Toast.makeText(RequestForService.this, "Thank you for your request our customer service representative will call you shortly", Toast.LENGTH_LONG).show();

                            try {

                            /*if(fullName.getText().length() < 0)
                            {
                                Toast.makeText(RequestForService.this, "Name Can't be empty", Toast.LENGTH_SHORT).show();
                            }else if(phone.getText().length() < 0)
                            {
                                Toast.makeText(RequestForService.this, "Phone # Can't be empty", Toast.LENGTH_SHORT).show();

                            }else if(address.getText().length() < 0)
                            {
                                Toast.makeText(RequestForService.this, "Address Can't be empty", Toast.LENGTH_SHORT).show();
                            }else {*/

                                Gson gson = new GsonBuilder().setLenient().create();

                                Retrofit retrofit = new Retrofit.Builder()
                                        .baseUrl("http://aramservices.comuf.com/")
                                        .addConverterFactory(GsonConverterFactory.create(gson))
                                        .build();


                                ApiInterface apiService =
                                        retrofit.create(ApiInterface.class);

                                ServiceData serviceData = new ServiceData();
                                serviceData.setName(fullName.getText().toString());
                                serviceData.setPhone(phone.getText().toString());
                                serviceData.setAddress(address.getText().toString());
                                serviceData.setAddress(email.getText().toString());
                                serviceData.setImei(imei);
                                serviceData.setServiceName(ServiceName);
                                // email


                                Call<Collection<ServiceData>> calls = apiService.createUser(serviceData);

                                calls.enqueue(new Callback<Collection<ServiceData>>() {
                                    @Override
                                    public void onResponse(Call<Collection<ServiceData>> call, Response<Collection<ServiceData>> response) {
                                        response.body();

                                        Toast.makeText(getApplicationContext(), "1 " + response.body().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Collection<ServiceData>> call, Throwable t) {
                                        Log.e("Aram", t.toString());
                                        Toast.makeText(getApplicationContext(), "1 " + t.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });


                                Call<Collection<ServiceData>> call = apiService.getTopRatedMovies();

                                call.enqueue(new Callback<Collection<ServiceData>>() {
                                    @Override
                                    public void onResponse(Call<Collection<ServiceData>> call, Response<Collection<ServiceData>> response) {
//                                    Type collectionType = new TypeToken<Collection<channelSearchEnum>>(){}.getType();
//                                    Collection<channelSearchEnum> enums = gson.fromJson(yourJson, collectionType);

                                        response.body();
                                        //JsonReader reader = new JsonReader(new StringReader(response.body().toString()));
                                        //reader.setLenient(true);
                                        //List<ServiceResponse> movies = response.body().toString();
                                        String data = response.body().toString();
                                        Log.d("Aram", "Number of movies received: ");
                                        Toast.makeText(getApplicationContext(), "2 " + response.body().toString(), Toast.LENGTH_SHORT).show();
                                    }

                                    @Override
                                    public void onFailure(Call<Collection<ServiceData>> call, Throwable t) {
                                        // Log error here since request failed
                                        Log.e("Aram", t.toString());
                                        Toast.makeText(getApplicationContext(), "2 " + t.toString(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                                //   }

                            } catch (Exception e) {
                                e.getLocalizedMessage();
                            }

                        }
                    });

                    alertDialogBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    });


                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });


    }

    public void showDialogForDateAndTimePicker()
    {
        final AppCompatDialog date_time_picker = new AppCompatDialog(RequestForService.this);
        date_time_picker.setTitle("Select Date and Time");
        date_time_picker.setContentView(R.layout.dialog_time_date);

        final TabHost tabHost = (TabHost) date_time_picker.findViewById(R.id.tabHost);
        final TabWidget tw = (TabWidget)tabHost.findViewById(android.R.id.tabs);
        for (int i = 0; i < tw.getChildCount(); ++i)
        {
            final View tabView = tw.getChildTabViewAt(i);
            final TextView tv = (TextView)tabView.findViewById(android.R.id.title);
            tv.setTypeface(null, Typeface.BOLD);
        }
        tabHost.setup();

        TabHost.TabSpec spec1 = tabHost.newTabSpec(getResources().getString(
                R.string.Tab_Units));
        spec1.setContent(R.id.tab1_Units);
        spec1.setIndicator(" " + getResources().getString(R.string.Tab_Units)
                + " ");

        TabHost.TabSpec spec2 = tabHost.newTabSpec(getResources().getString(
                R.string.Tab_Maps));
        spec2.setContent(R.id.tab2_Map);
        spec2.setIndicator("  " + getResources().getString(R.string.Tab_Maps)
                + " ");


        tabHost.addTab(spec1);
        tabHost.addTab(spec2);


        final DatePicker datePicker = (DatePicker) date_time_picker.findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) date_time_picker.findViewById(R.id.timePicker);
        //timePicker.setIs24HourView(false);
        Button button = (Button)date_time_picker.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                month = datePicker.getMonth();
                year = datePicker.getYear();
                day = datePicker.getDayOfMonth();
                hours =  timePicker.getCurrentHour();
                minutes = timePicker.getCurrentMinute();
                Calendar calendar = Calendar.getInstance();
                calendar.set(year, month, day,hours,minutes);
                Date latestDate = calendar.getTime();
                hours = calendar.get(Calendar.HOUR);

                //timePicker.is24HourView();
                String min;
                String amPm = null;
                if(minutes >-1 && minutes <10 )
                {
                    min = 0 + Integer.toString(minutes);
                }else{
                    min = Integer.toString(minutes);
                }

                if (calendar.get(Calendar.AM_PM) == Calendar.AM)
                    amPm = "AM";
                else if (calendar.get(Calendar.AM_PM) == Calendar.PM)
                    amPm = "PM";

                if (currentDate.compareTo(latestDate)<0)
                {
                    //timePicker.getC();
                    date_time_value.setText(new StringBuilder()
                            // Month is 0 based, just add 1
                            .append(month + 1).append("-").append(day).append("-")
                            .append(year).append(" ").append(hours).append(":").append(min).append(":").append(amPm)
                            .append(" "));
                    date_time_picker.dismiss();
                    System.out.println("latestDate is Greater than my currentDate");
                }else{
                    Toast.makeText(RequestForService.this,"Can't set the date before current date",Toast.LENGTH_SHORT).show();
                    System.out.println("latestDate is Smaller than my currentDate");
                }

            }
        });
        date_time_picker.show();


    }
}
