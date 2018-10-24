package com.ais.mobile.jhlee.aisdiary.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.contactais.ContactAisActivityView;
import com.ais.mobile.jhlee.aisdiary.app.diary.DiaryActivityView;
import com.ais.mobile.jhlee.aisdiary.app.diary.EditEventActivityView;
import com.ais.mobile.jhlee.aisdiary.app.diary.NewEventActivityView;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Event;
import com.ais.mobile.jhlee.aisdiary.app.home.HomeActivityView;

/**
 * Created: 20/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class Navigator {

    public static final int RC_HANDLE_NEW_EVENT = 0X8601;
    public static final int RC_HANDLE_EDIT_EVENT = 0X8602;

    public void navigateToHomeActivityView(Context context) {
        context.startActivity(new Intent(context, HomeActivityView.class));
    }

    public void navigateToDiaryActivityView(Context context) {
        context.startActivity(new Intent(context, DiaryActivityView.class));
    }

    public void requestToNewEventActivityView(Activity activity, int requestCode) {
        activity.startActivityForResult(new Intent(activity, NewEventActivityView.class), requestCode);
    }

    public void requestToEditEventActivityView(Activity activity, Event event, int requestCode) {
        Intent intent = new Intent(activity, EditEventActivityView.class);
        intent.putExtra(EditEventActivityView.ARGS_EVENT, event);

        activity.startActivityForResult(intent, requestCode);
    }

    public void navigateToContactUsActivityView(Context context) {
        context.startActivity(new Intent(context, ContactAisActivityView.class));
    }

    public void navigateToMailToActivityView(Context context) {
        String recipients = AndroidContext.instance().getString(R.string.mailto_contact_ais);

        Intent email = new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:" + recipients));

        // Create intent to show chooser
        Intent chooser = Intent.createChooser(email, AndroidContext.instance().getString(R.string.mailto_title));

        // Verify the intent will resolve to at least one activity
        if (email.resolveActivity(context.getPackageManager()) != null) {
            context.startActivity(chooser);

        } else {

            Toast.makeText(context.getApplicationContext(),
                    R.string.msg_doesnt_support_mail_service,
                    Toast.LENGTH_SHORT)
                    .show();
        }
    }
}
