package com.ais.mobile.jhlee.aisdiary.ui.view;

import android.content.Context;
import android.graphics.Paint;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

/**
 * Create: 24/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class StrikeThruTextView extends AppCompatTextView {

    public StrikeThruTextView(Context context) {
        super(context);
    }

    public StrikeThruTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StrikeThruTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void showAsStrikeThruText() {
        setPaintFlags(getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public void clearStrikeThruText() {
        setPaintFlags(getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
    }
}
