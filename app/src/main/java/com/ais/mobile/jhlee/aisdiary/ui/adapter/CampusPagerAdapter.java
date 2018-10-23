package com.ais.mobile.jhlee.aisdiary.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Campus;

import java.util.List;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class CampusPagerAdapter extends PagerAdapter {

    private List<Campus> data;

    public CampusPagerAdapter(List<Campus> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        return (data == null) ? 0 : data.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object o) {
        return view == o;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.campus_item_layout, container, false);

        Campus campus = getItem(position);

        TextView titleView = view.findViewById(R.id.titleView);
        titleView.setText(campus.getName());

        TextView locationView = view.findViewById(R.id.locationView);
        locationView.setText(campus.getAddress());

        TextView phoneView = view.findViewById(R.id.phoneView);
        phoneView.setText(campus.getPhone());

        TextView faxView = view.findViewById(R.id.faxView);
        faxView.setText(campus.getFax());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }

    public Campus getItem(int position) {
        return data.get(position);
    }
}
