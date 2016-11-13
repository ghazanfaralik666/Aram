package com.example.ghazanfarali.asani;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ghazanfarali on 22/08/2016.
 */
public class GridLayoutAdapter extends RecyclerView.Adapter<GridLayoutAdapter.MyViewHolder> {
    private Activity activity;
    private List<Menus> moviesList;
    private int screenWidth;
    public GridLayoutAdapter(Activity activity, List<Menus> moviesList) {
        this.activity = activity;
        this.moviesList = moviesList;
        WindowManager wm = (WindowManager) activity.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
    }
    Context c;
    OnItemClickListener onItemClickListener;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView title, year, genre;
        //public ToggleButton toggleButton;
        private ImageView image_name;
        private TextView menu_name;
        public MyViewHolder(View view) {
            super(view);
          /*  title = (TextView) view.findViewById(R.id.title);
            genre = (TextView) view.findViewById(R.id.genre);
            year = (TextView) view.findViewById(R.id.year);*/
            //toggleButton = (ToggleButton)view.findViewById(R.id.toggleButton);
            image_name = (ImageView) view.findViewById(R.id.image_name);
            menu_name = (TextView) view.findViewById(R.id.menu_name);
            view.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(view, getPosition());
            }
        }


    }
    public interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener mItemClickListener) {
        this.onItemClickListener = mItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity)
                .inflate(R.layout.menu_list_row, parent, false);
       // Holder dataObjectHolder = new Holder(view);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Menus movie = moviesList.get(position);
        final MyViewHolder myHolder = (MyViewHolder) holder;
        //  int d = c.getResources().getIdentifier(movie.getImage_name() , "drawable", c.getPackageName());;
        int resourceId = activity.getResources().getIdentifier(movie.getImage_name(), "drawable", "com.example.ghazanfarali.asani");
        myHolder.menu_name.setText(movie.getMenu_name());
        myHolder.image_name.setImageResource(resourceId);
    }

    /*@Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Menus movie = moviesList.get(position);
        final Holder myHolder = (Holder) holder;
        //  int d = c.getResources().getIdentifier(movie.getImage_name() , "drawable", c.getPackageName());;
        int resourceId = activity.getResources().getIdentifier(movie.getImage_name(), "drawable", "com.example.ghazanfarali.asani");
        myHolder.menu_name.setText(movie.getMenu_name());
        myHolder.image_name.setImageResource(resourceId);


    }
*/

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
    /*public class Holder extends CustomRecycleViewHolder {
        private ImageView image_name;
        private TextView menu_name;
        public Holder(View itemView) {
            super(itemView);
            image_name = (ImageView) itemView.findViewById(R.id.image_name);
            menu_name = (TextView) itemView.findViewById(R.id.menu_name);
        }
    }*/


}

