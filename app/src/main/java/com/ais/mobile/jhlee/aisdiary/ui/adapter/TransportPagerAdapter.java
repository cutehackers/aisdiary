package com.ais.mobile.jhlee.aisdiary.ui.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Transport;

import java.util.List;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class TransportPagerAdapter extends PagerAdapter {

    private List<Transport> data;

    public TransportPagerAdapter(List<Transport> data) {
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
                .inflate(R.layout.transport_item_layout, container, false);

        Transport transport = getItem(position);

        TextView transportTypeView = view.findViewById(R.id.transportTypeView);
        transportTypeView.setText(transport.getType());

        TextView titleView = view.findViewById(R.id.titleView);
        titleView.setText(transport.getTitle());

        TextView locationView = view.findViewById(R.id.locationView);
        locationView.setText(transport.getAddress());

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

    public Transport getItem(int position) {
        return data.get(position);
    }
}
