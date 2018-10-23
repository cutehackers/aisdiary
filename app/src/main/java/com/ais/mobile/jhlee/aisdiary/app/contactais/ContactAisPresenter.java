package com.ais.mobile.jhlee.aisdiary.app.contactais;

import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.ContactAisDataSource;
import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Campus;
import com.ais.mobile.jhlee.aisdiary.app.contactais.domain.model.Transport;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpPresenter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.CampusPagerAdapter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.TransportPagerAdapter;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created: 21/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class ContactAisPresenter<VIEW extends ContactAisView> extends MvpPresenter<VIEW> {

    private CampusPagerAdapter campusPagerAdapter;
    private TransportPagerAdapter transportPagerAdapter;

    ContactAisPresenter(VIEW view) {
        super(view);
    }

    public void setUpModels() {
        List<Campus> campuses = ContactAisDataSource.instance().getCampusList();
        campusPagerAdapter = new CampusPagerAdapter(campuses);

        List<Transport> transports = ContactAisDataSource.instance().getTransportList();
        transportPagerAdapter = new TransportPagerAdapter(transports);

        getView().setCampusPagerAdapter(campusPagerAdapter);
        getView().setTransportPagerAdapter(transportPagerAdapter);
    }

    public void markCampus() {
        int current = getView().getCurrentCampusChild();
        markCampus(current);
    }

    public void markCampus(int position) {
        if (position > -1) {
            Campus item = campusPagerAdapter.getItem(position);

            LatLng location = new LatLng(item.getLatitude(), item.getLongitude());
            getView().addMarker(location, item.getName());
        }
    }

    public void markTransport() {
        int current = getView().getCurrentTransportChild();
        markTransport(current);
    }

    public void markTransport(int position) {
        if (position > -1) {
            Transport item = transportPagerAdapter.getItem(position);

            LatLng location = new LatLng(item.getLatitude(), item.getLongitude());
            getView().addMarker(location, item.getTitle());
        }
    }

}
