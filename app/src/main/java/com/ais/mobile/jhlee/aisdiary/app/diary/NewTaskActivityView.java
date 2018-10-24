package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpActivityView;

/**
 * Created: 24/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class NewTaskActivityView extends MvpActivityView<NewTaskView, NewTaskPresenter<NewTaskView>> implements
        NewTaskView {

    private EditText contentView;


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_task);

        setUpViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_task_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_done: {
                String content = contentView.getText().toString();
                presenter.newTask(content.trim());
                return true;
            }

            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    //----------------------------------------------------------------------------------------------
    // implements: MvpActivityView

    @Override
    protected NewTaskPresenter<NewTaskView> onCreatePresenter() {
        return new NewTaskPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: NewTaskView

    @Override
    public void finishView() {
        finish();
    }

    @Override
    public void finishViewWithOkResult() {
        setResult(Activity.RESULT_OK);
        finish();
    }

    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews() {
        contentView = findViewById(R.id.contentView);
    }
}
