package com.example.ghazanfarali.asani;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
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
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    CoordinatorLayout main_content;

    private List<Menus> movieList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MenuListAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    GridLayoutAdapter Adapter;
    CollapsingToolbarLayout collapsingToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.MyToolbar);
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);\
        imageFlipper();
        main_content = (CoordinatorLayout)findViewById(R.id.main_content);
        collapsingToolbar =
                (CollapsingToolbarLayout) findViewById(R.id.collapse_toolbar);
        collapsingToolbar.setTitle("Asani");

        collapsingToolbar.setCollapsedTitleTextColor(getResources().getColor(R.color.colorAccent));
        collapsingToolbar.setExpandedTitleColor(getResources().getColor(R.color.colorPrimary));

        collapsingToolbar.setContentScrimColor(getResources().getColor(R.color.colorGreen));

        collapsingToolbar.setExpandedTitleTextAppearance(R.style.expandedappbar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.collapsedappbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        mLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(mLayoutManager);
        Adapter = new GridLayoutAdapter(this, movieList);
        recyclerView.setAdapter(Adapter);

        prepareMenusData();
//        mAdapter = new MenuListAdapter(movieList,MainActivity.this);
//        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
//        recyclerView.setLayoutManager(mLayoutManager);
//        recyclerView.setItemAnimator(new DefaultItemAnimator());
//        recyclerView.setAdapter(mAdapter);
//

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Menus movie = movieList.get(position);
                Toast.makeText(getApplicationContext(), movie.getMenu_name() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this,RequestForService.class);
                startActivity(i);
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        startService(new Intent(MainActivity.this,TokenService.class));
        startService(new Intent(MainActivity.this,FCMMessageReceiverService.class));
//        CardView cv = (CardView)findViewById(R.id.first);
//        cv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(MainActivity.this,RequestForService.class);
//                startActivity(i);
//            }
//        });



    }

    private final int SPLASH_DISPLAY_LENGTH = 3000;
    int count = 0;
    Handler handler;
    public void imageFlipper()
    {
        handler = new Handler();
       Runnable runnable = new Runnable() {
            @Override
            public void run() {


                ViewFlipper viewFilpper = (ViewFlipper)collapsingToolbar.findViewById(R.id.viewFilpper);
                viewFilpper.setInAnimation(MainActivity.this, R.anim.slide_in_from_left);

                viewFilpper.setOutAnimation(MainActivity.this, R.anim.slide_out_to_right);

                viewFilpper.showNext();

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
        Menus movie = new Menus("mason", "Mason","", "");
        movieList.add(movie);

        movie = new Menus("electrician", "Electrictian","", "");
        movieList.add(movie);

        movie = new Menus("carpainter", "Carpainter","", "");
        movieList.add(movie);

        movie = new Menus("plumber", "Plumber","", "");
        movieList.add(movie);

        movie = new Menus("acrepairman", "Ac RepairMen","", "");
        movieList.add(movie);
        Adapter.notifyDataSetChanged();
    }

    public interface ClickListener {
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
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {

            View bottomSheet = main_content.findViewById(R.id.bottomSheetLayout);
            final BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);

            behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
            behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    // React to state change
                  //  Toast.makeText(MainActivity.this,"data",Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                    // React to dragging events
                    //Toast.makeText(MainActivity.this,"datad",Toast.LENGTH_SHORT).show();
                }
            });

            Button about_us = (Button) bottomSheet.findViewById(R.id.about_us);
            about_us.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                    Intent i = new Intent(MainActivity.this,AboutUs.class);
                    startActivity(i);
                }
            });
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
