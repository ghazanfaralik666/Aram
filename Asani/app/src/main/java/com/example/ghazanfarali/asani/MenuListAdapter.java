package com.example.ghazanfarali.asani;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by ghazanfarali on 15/08/2016.
 */
public class MenuListAdapter extends RecyclerView.Adapter<MenuListAdapter.MyViewHolder> {

    private List<Menus> moviesList;
    private Activity c;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView menu_name,menu_name2, image_name2;
        ImageView image_name;
        public MyViewHolder(View view) {
            super(view);
            menu_name = (TextView) view.findViewById(R.id.menu_name);
            image_name = (ImageView) view.findViewById(R.id.image_name);

            //menu_name2 = (TextView) view.findViewById(R.id.menu_name2);
            //image_name2 = (TextView) view.findViewById(R.id.image_name2);
        }
    }


    public MenuListAdapter(List<Menus> moviesList,Activity c) {
        this.moviesList = moviesList;
        this.c = c;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.menu_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Menus movie = moviesList.get(position);

      //  int d = c.getResources().getIdentifier(movie.getImage_name() , "drawable", c.getPackageName());;
        int resourceId = c.getResources().getIdentifier(movie.getImage_name(), "drawable", "com.example.ghazanfarali.asani");
        holder.menu_name.setText(movie.getMenu_name());
        holder.image_name.setImageResource(resourceId);
       // holder.menu_name2.setText(movie.getMenu_name2());
       // holder.image_name2.setText(movie.getImage_name2());

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }
}
