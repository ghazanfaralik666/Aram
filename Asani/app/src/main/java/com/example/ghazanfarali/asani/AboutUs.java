package com.example.ghazanfarali.asani;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    boolean bottom_sheet = false;
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            if(!bottom_sheet)
            {
                LinearLayout bottomSheet = (LinearLayout)findViewById(R.id.bottomSheetLayout);
                bottomSheet.setVisibility(View.VISIBLE);


                LinearLayout contact_us = (LinearLayout) bottomSheet.findViewById(R.id.contact_us);
                contact_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(AboutUs.this,ContactUs.class);
                        startActivity(i);
                    }
                });
                LinearLayout about_us = (LinearLayout) bottomSheet.findViewById(R.id.about_uss);
                about_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        finish();
                        Intent i = new Intent(AboutUs.this,AboutUs.class);
                        startActivity(i);
                    }
                });

                LinearLayout homes = (LinearLayout) bottomSheet.findViewById(R.id.homes);
                homes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        finish();
                        Intent i = new Intent(AboutUs.this,MainActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                        startActivity(i);
                    }
                });
                bottom_sheet = true;
            }else{

                LinearLayout bottomSheet = (LinearLayout)findViewById(R.id.bottomSheetLayout);
                bottomSheet.setVisibility(View.INVISIBLE);
                bottom_sheet = false;
            }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
