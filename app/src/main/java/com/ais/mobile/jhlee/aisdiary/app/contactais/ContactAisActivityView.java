package com.ais.mobile.jhlee.aisdiary.app.contactais;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;
import android.widget.ViewAnimator;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpActivityView;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.CampusPagerAdapter;
import com.ais.mobile.jhlee.aisdiary.ui.adapter.TransportPagerAdapter;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 *
 * refs: google map sample codes
 *  https://github.com/googlemaps/android-samples/tree/master/ApiDemos/java/app/src/main/java/com/example/mapdemo
 */
public class ContactAisActivityView extends MvpActivityView<ContactAisView, ContactAisPresenter<ContactAisView>> implements
        ContactAisView,
        OnMapReadyCallback {

    private static final int PAGER_INDEX_CAMPUS = 0;
    private static final int PAGER_INDEX_TRANSPORT = 1;

    private GoogleMap map;
    private Handler handler = new Handler();

    private ViewGroup rootContainer;
    private ViewAnimator viewPagerAnimator;
    private ViewPager campusViewPager;
    private ViewPager transportViewPager;


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_contact_ais);

        setUpViews();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // load data from sqlite database
        presenter.setUpModels();
    }

    //----------------------------------------------------------------------------------------------
    // implements: MvpActivityView

    @Override
    protected ContactAisPresenter<ContactAisView> onCreatePresenter() {
        return new ContactAisPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: ContactAisView

    @Override
    public void setCampusPagerAdapter(CampusPagerAdapter adapter) {
        campusViewPager.setAdapter(adapter);
    }

    @Override
    public void setTransportPagerAdapter(TransportPagerAdapter adapter) {
        transportViewPager.setAdapter(adapter);
    }

    @Override
    public int getCurrentCampusChild() {
        return (campusViewPager != null) ? campusViewPager.getCurrentItem() : -1;
    }

    @Override
    public int getCurrentTransportChild() {
        return (transportViewPager != null) ? transportViewPager.getCurrentItem() : -1;
    }

    @Override
    public void addMarker(LatLng location, String title) {
        map.clear();
        map.addMarker(new MarkerOptions().position(location).title(title));
        map.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(location)
                .zoom(16.5f)
                .bearing(0)
                .build()));

        handler.post(() -> {
            float scrollY = rootContainer.getHeight() / 5;
            map.moveCamera(CameraUpdateFactory.scrollBy(0, scrollY));
        });
    }

    //----------------------------------------------------------------------------------------------
    // implements: OnMapReadyCallback

    @Override
    public void onMapReady(GoogleMap googleMap) {
        map = googleMap;

        presenter.markCampus();
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews() {
        SupportMapFragment fragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (fragment != null) {
            fragment.getMapAsync(this);
        }

        rootContainer = findViewById(R.id.rootContainer);
        viewPagerAnimator = findViewById(R.id.viewPagerAnimator);
        viewPagerAnimator.setDisplayedChild(PAGER_INDEX_CAMPUS);

        campusViewPager = findViewById(R.id.campusViewPager);
        transportViewPager = findViewById(R.id.transportViewPager);

        // contact ais mail click
        findViewById(R.id.fab).setOnClickListener(view -> {
            AndroidContext.instance().navigator().navigateToMailToActivityView(ContactAisActivityView.this,
                    AndroidContext.instance().getString(R.string.mailto_contact_ais));
        });

        findViewById(R.id.campusActionView).setOnClickListener(view -> {
            if (viewPagerAnimator.getDisplayedChild() != PAGER_INDEX_CAMPUS) {
                presenter.markCampus();

                viewPagerAnimator.setDisplayedChild(PAGER_INDEX_CAMPUS);
            }
        });

        findViewById(R.id.transportActionView).setOnClickListener(view -> {
            if (viewPagerAnimator.getDisplayedChild() != PAGER_INDEX_TRANSPORT) {
                presenter.markTransport();

                viewPagerAnimator.setDisplayedChild(PAGER_INDEX_TRANSPORT);
            }
        });

        campusViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                presenter.markCampus(position);
            }
        });

        transportViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                presenter.markTransport(position);
            }
        });

        findViewById(R.id.closeView).setOnClickListener(view -> finish());
    }
}
