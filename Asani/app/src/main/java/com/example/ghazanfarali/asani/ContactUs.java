package com.example.ghazanfarali.asani;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        ImageView facebook = (ImageView)findViewById(R.id.facebook);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/Aram-211149825980889/"));
                startActivity(facebook);
            }
        });

        ImageView twitter = (ImageView)findViewById(R.id.twitter);
        twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent facebook = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.twitter.com"));
                startActivity(facebook);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

                        Intent i = new Intent(ContactUs.this,ContactUs.class);
                        startActivity(i);
                    }
                });
                LinearLayout about_us = (LinearLayout) bottomSheet.findViewById(R.id.about_uss);
                about_us.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent i = new Intent(ContactUs.this,AboutUs.class);
                        startActivity(i);
                    }
                });

                LinearLayout homes = (LinearLayout) bottomSheet.findViewById(R.id.homes);
                homes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        finish();
                        Intent i = new Intent(ContactUs.this,MainActivity.class);
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
