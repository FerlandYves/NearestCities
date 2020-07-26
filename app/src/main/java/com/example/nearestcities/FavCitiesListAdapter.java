package com.example.nearestcities;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class FavCitiesListAdapter extends ArrayAdapter<String> {
    Context mContext;
    int mResource;
    ViewHolder viewHolder;


    public static class ViewHolder {
        TextView txtCityName;
        ImageView imgDeleteFav;

    }

    public FavCitiesListAdapter(@NonNull Context context, int resource, @NonNull List<String> cities) {
        super(context, resource, cities);
        mContext = context;
        mResource = resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.fav_cities_list_item, parent, false);

            viewHolder = new FavCitiesListAdapter.ViewHolder();
            viewHolder.txtCityName = (TextView) convertView.findViewById(R.id.txt_city_name);
            viewHolder.imgDeleteFav = (ImageView) convertView.findViewById(R.id.img_delete_fav);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (FavCitiesListAdapter.ViewHolder) convertView.getTag();
        }
        viewHolder.txtCityName.setText(getItem(position));

        viewHolder.imgDeleteFav.setImageResource(R.drawable.ic_delete_forever_24);

        viewHolder.txtCityName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,FavCitiesDetailActivity.class);
                String item = getItem(position);
                intent.putExtra("cityName",item);
                mContext.startActivity(intent);
            }
        });

        viewHolder.imgDeleteFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = getItem(position);
                FavCityEntity cityEntity = new FavCityEntity();
                cityEntity.setCity(city);
                MainActivity.appDatabase.dao().delete(cityEntity);
                FavCitiesListAdapter.this.remove(city);
                FavCitiesListAdapter.this.notifyDataSetChanged();

                Toast.makeText(mContext, "Deleted", Toast.LENGTH_SHORT).show();


            }
        });


        return convertView;


    }
}

