package com.ais.mobile.jhlee.aisdiary.app.aboutais;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ais.mobile.jhlee.aisdiary.R;

/**
 * Created: 25/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class AisFragmentView extends Fragment {


    public AisFragmentView() {
        // Required empty public constructor
    }

    public static AisFragmentView create() {
        return new AisFragmentView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ais, container, false);
    }

}
