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
public class ProgrammeFragmentView extends Fragment {


    public ProgrammeFragmentView() {

    }

    public static ProgrammeFragmentView create() {
        return new ProgrammeFragmentView();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_programme, container, false);
    }

}
