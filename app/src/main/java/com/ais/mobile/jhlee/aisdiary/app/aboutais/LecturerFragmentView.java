package com.ais.mobile.jhlee.aisdiary.app.aboutais;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AlphabetIndexer;
import android.widget.EditText;
import android.widget.SectionIndexer;

import com.ais.mobile.jhlee.aisdiary.R;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.LecturerDao;
import com.ais.mobile.jhlee.aisdiary.app.aboutais.domain.model.Lecturer;
import com.ais.mobile.jhlee.aisdiary.base.AndroidContext;
import com.ais.mobile.jhlee.aisdiary.mvp.MvpFragmentView;
import com.ais.mobile.jhlee.aisdiary.ui.view.LecturerViewHolder;
import com.ais.mobile.jhlee.aisdiary.ui.view.OnLecturerItemClickListener;

import java.util.List;
import java.util.Locale;

/**
 * Created: 25/10/2018
 * Author: Jun Hyoung Lee
 * Email: niceguy0315@hotmail.com
 */
public class LecturerFragmentView extends MvpFragmentView<LecturerView, LecturerPresenter<LecturerView>> implements
        LecturerView,
        LoaderManager.LoaderCallbacks<List<Lecturer>> {

    private static final String ARGS_SEARCH_QUERY = "search_query";
    private static final int READ_QUERY_ID = 1;

    public static final String LECTURER_PROFILE_TAG = "lecturer_profile_tag";

    private String searchQuery;
    private boolean searchQueryChanged = false;

    private View emptyView;
    private RecyclerView lecturersView;
    private EditText searchView;

    private LecturerAdapter adapter;


    public LecturerFragmentView() { }

    public static LecturerFragmentView create() {
        return create("");
    }

    public static LecturerFragmentView create(String searchQuery) {
        LecturerFragmentView fragment = new LecturerFragmentView();

        Bundle args = new Bundle();
        args.putString(ARGS_SEARCH_QUERY, searchQuery);
        fragment.setArguments(args);
        return fragment;
    }


    //----------------------------------------------------------------------------------------------
    // overrides

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState == null) {
            Bundle args = getArguments();
            if (args != null) {
                searchQuery = args.getString(ARGS_SEARCH_QUERY);
            }
        } else {
            searchQuery = savedInstanceState.getString(ARGS_SEARCH_QUERY);
        }
    }

    @Override
    protected int getLayoutResourceId() { return R.layout.fragment_lecturer; }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpViews(view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getLoaderManager().initLoader(READ_QUERY_ID, null, this);
    }

    @Override
    public void onStart() {
        super.onStart();
//        presenter.load();

    }

    @Override
    public void onPause() {
        super.onPause();

        hideSoftInputView();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        if (!TextUtils.isEmpty(searchQuery)) {
            outState.putString(ARGS_SEARCH_QUERY, searchQuery);
        }
    }


    //----------------------------------------------------------------------------------------------
    // implements: MvpFragmentView

    @Override
    protected LecturerPresenter<LecturerView> onCreatePresenter() {
        return new LecturerPresenter<>(this);
    }


    //----------------------------------------------------------------------------------------------
    // implements: LecturerView

    @Override
    public void showEmptyView() {
        emptyView.setVisibility(View.VISIBLE);
        lecturersView.setVisibility(View.GONE);
    }

    @Override
    public void showLecturerView() {
        emptyView.setVisibility(View.GONE);
        lecturersView.setVisibility(View.VISIBLE);
    }

    @Override
    public void update(List<Lecturer> lecturers) {
        adapter.update(lecturers);
    }


    //----------------------------------------------------------------------------------------------
    // implements: LoaderManager.LoaderCallbacks<List<Lecturer>>

    @NonNull
    @Override
    public Loader<List<Lecturer>> onCreateLoader(int id, @Nullable Bundle args) {
        switch (id) {
            case READ_QUERY_ID: {
                return presenter.onCreateLoader(searchQuery);
            }
            default: throw new IllegalArgumentException();
        }
    }

    @Override
    public void onLoadFinished(@NonNull Loader<List<Lecturer>> loader, List<Lecturer> lecturers) {
        if (loader.getId() == READ_QUERY_ID) {
            presenter.onLoadFinished(loader, lecturers);
            searchQueryChanged = false;

            Log.d("DARBY", "Search query result: " + lecturers.size());
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<List<Lecturer>> loader) {
        if (loader.getId() == READ_QUERY_ID) {
            presenter.onLoaderReset(loader);
        }
    }


    //----------------------------------------------------------------------------------------------
    // methods

    private void setUpViews(View container) {
        emptyView = container.findViewById(R.id.emptyView);

        adapter = new LecturerAdapter((holder, lecturer) -> {
            // remove profile view if already exists
            Fragment fragment = getActivity().getSupportFragmentManager().findFragmentByTag(LECTURER_PROFILE_TAG);
            if (fragment != null) {
                getActivity().getSupportFragmentManager().popBackStack();
            }

            // navigate to lecturer detail view
            getActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragment_container, LecturerProfileFragmentView.create(lecturer), LECTURER_PROFILE_TAG)
                    .addToBackStack(null)
                    .commit();
        });

        lecturersView = container.findViewById(R.id.lecturersView);
        lecturersView.setHasFixedSize(true);
        lecturersView.setLayoutManager(new LinearLayoutManager(getContext()));
        lecturersView.setAdapter(adapter);

        searchView = container.findViewById(R.id.searchView);
        searchView.setText(searchQuery);
        searchView.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            @Override
            public void afterTextChanged(Editable s) {
                onQueryTextChange(s);
            }
        });

        container.findViewById(R.id.cancelSearchView).setOnClickListener(this::onCancelSearchClick);
    }

    private void onQueryTextChange(CharSequence newText) {
        String filter = (!TextUtils.isEmpty(newText)) ? newText.toString() : null;

        if (searchQuery == null && filter == null) {
            return;
        }

        if (searchQuery != null && searchQuery.equals(filter)) {
            return;
        }

        searchQuery = filter;
        searchQueryChanged = true;
        Log.d("DARBY", "Search query changed: " + searchQuery);

        getLoaderManager().restartLoader(READ_QUERY_ID, null, this);

    }

    private void onCancelSearchClick(View view) {
        searchView.setText(null);

        hideSoftInputView();
    }

    private void hideSoftInputView() {
        if (searchView.isFocused()) {
            InputMethodManager imm = (InputMethodManager) AndroidContext.instance().getApplication()
                    .getSystemService(Context.INPUT_METHOD_SERVICE);

            imm.hideSoftInputFromWindow(searchView.getWindowToken(), 0);
        }
    }


    private class LecturerAdapter extends RecyclerView.Adapter<LecturerViewHolder> implements SectionIndexer {

        private OnLecturerItemClickListener onItemClickListener;

        private AlphabetIndexer indexer;

        private List<Lecturer> data;

        private TextAppearanceSpan highlightSpan;


        LecturerAdapter(OnLecturerItemClickListener onItemClickListener) {
            this.onItemClickListener = onItemClickListener;

            String alphabet = AndroidContext.instance().getString(R.string.alphabet);
            indexer = new AlphabetIndexer(null, LecturerDao.SORT_COLUMN_INDEX, alphabet);
            highlightSpan = new TextAppearanceSpan(getContext(), R.style.HighlightTextStyle);
        }

        @Override
        public int getItemCount() {
            return (data != null) ? data.size() : 0;
        }

        @NonNull
        @Override
        public LecturerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lecturer_item_layout, parent, false);
            return new LecturerViewHolder(view, onItemClickListener);
        }

        @Override
        public void onBindViewHolder(@NonNull LecturerViewHolder holder, int position) {
            Lecturer lecturer = data.get(position);
            bindNameView(holder, lecturer);

            holder.itemView.setTag(lecturer);
        }

        @Override
        public Object[] getSections() {
            return indexer.getSections();
        }

        @Override
        public int getPositionForSection(int sectionIndex) {
            if (data != null) {
                return indexer.getPositionForSection(sectionIndex);
            }
            return 0;
        }

        @Override
        public int getSectionForPosition(int position) {
            if (data != null) {
                indexer.getSectionForPosition(position);
            }
            return 0;
        }

        public void update(List<Lecturer> lecturers) {
            data = lecturers;
            notifyDataSetChanged();
        }

        private void bindNameView(LecturerViewHolder holder, Lecturer lecturer) {
            int index= indexOfSearchQuery(lecturer.getName());
            if (index == -1) {
                holder.setName(lecturer.getName());

            } else {
                SpannableString highlight = new SpannableString(lecturer.getName());
                highlight.setSpan(highlightSpan, index, index + searchQuery.length(), 0);

                holder.setName(highlight);
            }
        }

        private int indexOfSearchQuery(String name) {
            if (!TextUtils.isEmpty(searchQuery)) {
                return name.toLowerCase(Locale.getDefault()).indexOf(searchQuery.toLowerCase(Locale.getDefault()));
            }
            return -1;
        }
    }

}
