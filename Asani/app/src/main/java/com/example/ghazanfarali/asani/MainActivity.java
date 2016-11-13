package com.example.ghazanfarali.asani;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.transition.Slide;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout main_content;

    private List<Menus> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    GridLayoutAdapter Adapter;
    CollapsingToolbarLayout collapsingToolbar;
    GridLayoutAdapter.OnItemClickListener onItemClickListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        AssetManager am = getApplicationContext().getAssets();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        Adapter = new GridLayoutAdapter(this, movieList);
        recyclerView.setAdapter(Adapter);

        prepareMenusData();
        onItemClickListener = new GridLayoutAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Menus movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getMenu_name() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,RequestForService.class);
                i.putExtra("ServiceName",movie.getMenu_name());
                startActivity(i);
            }
        };
        Adapter.setOnItemClickListener(onItemClickListener);
        TextView termsAndCondition = (TextView)findViewById(R.id.termsAndCondition);
        termsAndCondition.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,TermsAndCondition.class);
                startActivity(i);
            }
        });
        /*recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Menus movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getMenu_name() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,RequestForService.class);
                i.putExtra("ServiceName",movie.getMenu_name());
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));*/

        startService(new Intent(MainActivity.this,TokenService.class));
        startService(new Intent(MainActivity.this,FCMMessageReceiverService.class));

    }

    private final int SPLASH_DISPLAY_LENGTH = 10000;
    int count = 0;
    Handler handler;
    public void imageFlipper()
    {
        handler = new Handler();
       Runnable runnable = new Runnable() {
            @Override
            public void run() {

//
//                ViewFlipper viewFilpper = (ViewFlipper)collapsingToolbar.findViewById(R.id.viewFilpper);
//                viewFilpper.setInAnimation(MainActivity.this, R.anim.slide_in_from_left);
//
//                viewFilpper.setOutAnimation(MainActivity.this, R.anim.slide_out_to_right);
//
//                viewFilpper.showNext();

               /* ImageView bgheaders = (ImageView)collapsingToolbar.findViewById(R.id.bgheaders);
                if(count == 0) {
                    bgheaders.setImageResource(R.drawable.mason);
                    count =1;

                }else{
                    bgheaders.setImageResource(R.drawable.carpainter);
                    count = 0;
                }
*/
                handler.postDelayed(this, SPLASH_DISPLAY_LENGTH);
            }
        };

        handler.postDelayed(runnable, SPLASH_DISPLAY_LENGTH);
    }

    private void prepareMenusData() {
        Menus movie = new Menus("ic_mason", "Mason","", "");
        movieList.add(movie);

        movie = new Menus("ic_electrician", "Electrictian","", "");
        movieList.add(movie);

        movie = new Menus("ic_carpenter", "Carpenter","", "");
        movieList.add(movie);

        movie = new Menus("ic_plumber", "Plumber","", "");
        movieList.add(movie);

        movie = new Menus("ic_mechanic", "Mechanic","", "");
        movieList.add(movie);

        movie = new Menus("ic_painter", "Painter","", "");
        movieList.add(movie);

        /*movie = new Menus("ic_painter", "Construction","", "");
        movieList.add(movie);
        movie = new Menus("ic_painter", "Construction","", "");
        movieList.add(movie);
        movie = new Menus("ic_painter", "Construction","", "");
        movieList.add(movie);
        movie = new Menus("ic_painter", "Construction","", "");
        movieList.add(movie);
        movie = new Menus("ic_painter", "Construction","", "");
        movieList.add(movie);*/
        Adapter.notifyDataSetChanged();
    }

    /*public interface ClickListener {
        void onClick(View view, int position);

        void onLongClick(View view, int position);
    }

    public static class RecyclerTouchListener implements RecyclerView.OnItemTouchListener {

        private GestureDetector gestureDetector;
        private MainActivity.ClickListener clickListener;

        public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final MainActivity.ClickListener clickListener) {
            this.clickListener = clickListener;
            gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onSingleTapUp(MotionEvent e) {
                    return true;
                }

                @Override
                public void onLongPress(MotionEvent e) {
                    View child = recyclerView.findChildViewUnder(e.getX(), e.getY());
                    if (child != null && clickListener != null) {
                        clickListener.onLongClick(child, recyclerView.getChildPosition(child));
                    }
                }
            });
        }

        @Override
        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {

            View child = rv.findChildViewUnder(e.getX(), e.getY());
            if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
                clickListener.onClick(child, rv.getChildPosition(child));
            }
            return false;
        }

        @Override
        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

        }
    }*/

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
               Adapter.setOnItemClickListener(null);

               LinearLayout contact_us = (LinearLayout) bottomSheet.findViewById(R.id.contact_us);
               contact_us.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(MainActivity.this,ContactUs.class);
                    startActivity(i);
                }
            });
               LinearLayout about_us = (LinearLayout) bottomSheet.findViewById(R.id.about_uss);
               about_us.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       Intent i = new Intent(MainActivity.this,AboutUs.class);
                       startActivity(i);
                   }
               });

               LinearLayout homes = (LinearLayout) bottomSheet.findViewById(R.id.homes);
               homes.setOnClickListener(new View.OnClickListener() {
                   @Override
                   public void onClick(View view) {

                       finish();
                       Intent i = new Intent(MainActivity.this,MainActivity.class);
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
               Adapter.setOnItemClickListener(onItemClickListener);
                bottom_sheet = false;
           }

            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
