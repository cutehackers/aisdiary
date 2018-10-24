package com.ais.mobile.jhlee.aisdiary.app.diary;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.diary.domain.model.Task;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpActivityView;

/**
 * Created: 24/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class EditTaskActivityView extends MvpActivityView<EditTaskView, EditTaskPresenter<EditTaskView>> implements
        EditTaskView {

    public static final String ARGS_TASK = "args_task";

    private Task task;

    private EditText contentView;


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resolveViewComponents();

        setContentView(R.layout.activity_edit_task);

        setUpViews();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        save();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_task_option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                onBackPressed();
              return true;
            }

            case R.id.action_delete: {
                presenter.delete(task);
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
    protected EditTaskPresenter<EditTaskView> onCreatePresenter() {
        return new EditTaskPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: EditTaskView

    @Override
    public void finishView(int msgId) {
        finish();

        Toast.makeText(AndroidContext.instance().getApplication(), msgId, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setSuccess() {
        setResult(Activity.RESULT_OK);
    }

    //----------------------------------------------------------------------------------------------
    // methods

    private void resolveViewComponents() {
        Intent intent = getIntent();
        if (intent == null) {
            Toast.makeText(AndroidContext.instance().getApplication(),
                    R.string.msg_invalid_task_data, Toast.LENGTH_LONG).show();
            finish();
        }

        Task task = intent.getParcelableExtra(ARGS_TASK);
        if (task.getId() < 0) {
            Toast.makeText(AndroidContext.instance().getApplication(),
                    R.string.msg_invalid_task_data, Toast.LENGTH_LONG).show();
            finish();
        }

        this.task = task;
    }

    private void setUpViews() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        contentView = findViewById(R.id.contentView);
        contentView.setText(task.getContent());
    }

    private void save() {
        String content = contentView.getText().toString();
        task.setContent(content.trim());
        presenter.update(task);
    }
}
