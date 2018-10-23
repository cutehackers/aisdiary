package com.ais.mobile.jhlee.aisdiary.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;
import android.widget.Switch;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.EventFragmentView;
import com.ais.mobile.jhlee.aisdiary.app.diary.TaskFragmentView;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;

import java.util.HashMap;

/**
 * Created: 23/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class DiaryPagerAdapter extends FragmentStatePagerAdapter {

    private static final int DIARY_CONTENT_SIZE = 2;
    public static final int DIARY_CONTENT_EVENT = 0;
    public static final int DIARY_CONTENT_TASK = 1;

    private SparseArray<Fragment> fragments = new SparseArray<>();


    public DiaryPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return DIARY_CONTENT_SIZE;
    }

    @Override
    public Fragment getItem(int index) {
        Fragment fragment = null;

        switch (index) {
            case DIARY_CONTENT_EVENT: {
                fragment = EventFragmentView.create();
            } break;
            case DIARY_CONTENT_TASK: {
                fragment = TaskFragmentView.create();
            } break;
        }

        if (fragment != null) {
            fragments.put(index, fragment);
        }
        return fragment;
    }

    @Override
    public void startUpdate(@NonNull ViewGroup container) {
        super.startUpdate(container);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        return super.instantiateItem(container, position);
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, position, object);

        fragments.remove(position);
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case DIARY_CONTENT_EVENT:
                return AndroidContext.instance().getString(R.string.diary_event_title);
            case DIARY_CONTENT_TASK:
                return AndroidContext.instance().getString(R.string.diary_task_title);
            default:
                return super.getPageTitle(position);
        }
    }

    /**
     * To get the reference of fragment by position
     * @param position fragment index position
     * @return reference of fragment
     */
    public Fragment getFragment(int position) {
        return fragments.get(position);
    }
}
