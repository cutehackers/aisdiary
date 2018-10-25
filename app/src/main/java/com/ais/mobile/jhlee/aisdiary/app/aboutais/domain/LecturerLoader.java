package com.ais.mobile.jhlee.aisdiary.app.aboutais.domain;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.os.OperationCanceledException;

import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;

import java.util.List;

/**
 * Create: 25/10/18
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 *
 * ref: https://developer.android.com/reference/android/content/AsyncTaskLoader
 */
public class LecturerLoader extends AsyncTaskLoader<List<Lecturer>> {

    private ForceLoadContentObserver observer = new ForceLoadContentObserver();

    private String searchQuery = null;

    private List<Lecturer> lecturers;

    public LecturerLoader(@NonNull Context context) {
        super(context);
    }

    public LecturerLoader(@NonNull Context context, String searchQuery) {
        super(context);
        this.searchQuery = searchQuery;
    }

    @Nullable
    @Override
    public List<Lecturer> loadInBackground() {
        synchronized (this) {
            if (isLoadInBackgroundCanceled()) {
                throw new OperationCanceledException();
            }
        }

        List<Lecturer> lecturers = AboutAisDataSource.instance().getLecturerListByName(searchQuery);

//        try {
//            Cursor cursor = getContext().getContentResolver().query(mUri, mProjection, mSelection,
//                    mSelectionArgs, mSortOrder, mCancellationSignal);
//            if (cursor != null) {
//                try {
//                    // Ensure the cursor window is filled.
//                    cursor.getCount();
//                    registerContentObserver(cursor, mObserver);
//                } catch (RuntimeException ex) {
//                    cursor.close();
//                    throw ex;
//                }
//            }
//            return cursor;
//        } finally {
//            synchronized (this) {
//                mCancellationSignal = null;
//            }
//        }

        this.lecturers = lecturers;
        return this.lecturers;
    }

    @Override
    public void cancelLoadInBackground() {
        super.cancelLoadInBackground();
    }

    @Override
    public void deliverResult(List<Lecturer> lecturers) {
        if (isReset()) {
            // An async query came in while the loader is stopped.  We
            // don't need the result.
            if (lecturers != null) {
                onReleaseResources(lecturers);
            }
        }
        List<Lecturer> old = this.lecturers;
        this.lecturers = lecturers;

        if (isStarted()) {
            // If the Loader is currently started, we can immediately
            // deliver its results.
            super.deliverResult(lecturers);
        }

        // At this point we can release the resources associated with
        // 'oldApps' if needed; now that the new result is delivered we
        // know that it is no longer in use.
        if (old != null) {
            onReleaseResources(old);
        }
    }

    /**
     * Handles a request to start the Loader.
     */
    @Override
    protected void onStartLoading() {
        if (lecturers != null) {
            // If we currently have a result available, deliver it
            // immediately.
            deliverResult(lecturers);
        }

        if (takeContentChanged() || lecturers == null) {
            // If the data has changed since the last time it was loaded
            // or is not currently available, start a load.
            forceLoad();
        }
    }

    /**
     * Handles a request to stop the Loader.
     */
    @Override
    protected void onStopLoading() {
        // Attempt to cancel the current load task if possible.
        cancelLoad();
    }

    @Override
    public void onCanceled(List<Lecturer> lecturers) {
        onReleaseResources(lecturers);
    }

    @Override
    protected void onReset() {
        super.onReset();

        // Ensure the loader is stopped
        onStopLoading();

        // At this point we can release the resources associated with 'apps'
        // if needed.
        if (lecturers != null) {
            onReleaseResources(lecturers);
            lecturers = null;
        }
    }

    /**
     * Helper function to take care of releasing resources associated
     * with an actively loaded data set.
     */
    protected void onReleaseResources(List<Lecturer> lecturers) {
        // For a simple List<> there is nothing to do.  For something
        // like a Cursor, we would close it here.
    }
}
