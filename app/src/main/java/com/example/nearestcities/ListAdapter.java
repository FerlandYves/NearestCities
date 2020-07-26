package com.example.nearestcities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Room;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<City> {
    Context mContext;
    int mResource;
    ViewHolder viewHolder;


    public static class ViewHolder {
        TextView txtCityName;
        ImageView imgFav;

    }

    public ListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<City> cities) {
        super(context, resource, cities);
        mContext = context;
        mResource = resource;


    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            LayoutInflater li = LayoutInflater.from(mContext);
            convertView = li.inflate(R.layout.list_item, parent, false);

            viewHolder = new ViewHolder();
            viewHolder.txtCityName = (TextView) convertView.findViewById(R.id.txt_city_name);
            viewHolder.imgFav = (ImageView) convertView.findViewById(R.id.img_fav);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.txtCityName.setText(getItem(position).getCity());

        viewHolder.imgFav.setImageResource(R.drawable.ic_favorite_border_24);


        viewHolder.imgFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = getItem(position).getCity();
                City item = getItem(position);
                FavouriteCities favouriteCities = new FavouriteCities(item.getCity(), item.getCountry(), item.getRegion(), item.getCurrency_name(),
                        item.getLatitude(), item.getLongitude()
                );
                MainActivity.appDatabase.dao().insert(favouriteCities);

                FavCityEntity cityEntity = new FavCityEntity();
                cityEntity.setCity(city);
                MainActivity.appDatabase.dao().insert(cityEntity);

                Toast.makeText(mContext, "City Added to favourites", Toast.LENGTH_SHORT).show();
            }
        });


        return convertView;


    }
}
