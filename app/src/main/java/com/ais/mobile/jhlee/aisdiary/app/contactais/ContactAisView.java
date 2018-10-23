package com.ais.mobile.jhlee.aisdiary.app.contactais;

import com.ais.mobile.jhlee.aisdiary.mvp.MvpView;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.CampusPagerAdapter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.TransportPagerAdapter;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public interface ContactAisView extends MvpView {

    void setCampusPagerAdapter(CampusPagerAdapter adapter);

    void setTransportPagerAdapter(TransportPagerAdapter adapter);

    int getCurrentCampusChild();

    int getCurrentTransportChild();

    void addMarker(LatLng location, String title);

}
