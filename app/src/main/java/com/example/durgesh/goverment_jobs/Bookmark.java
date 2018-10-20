package com.example.durgesh.goverment_jobs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;

public class Bookmark extends Fragment {

    RecyclerView recyclerView;
    ArrayList<JobDataType> bookmarklist = null;
    BookmarkListAdapter bla;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_bookmark, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycleview2);

        SharedPreference sharedPreference = new SharedPreference();
        bookmarklist = sharedPreference.getFavorites(getActivity());

        RecyclerView.LayoutManager lml = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(lml);

        if (bookmarklist != null) {
            bla = new BookmarkListAdapter((FragmentActivity) getContext(), bookmarklist);
            recyclerView.setAdapter(bla);
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {
            loadData();
        }
    }

    private void loadData() {

        SharedPreference sharedPreference = new SharedPreference();

        bookmarklist = sharedPreference.getFavorites(getActivity());

        if (bookmarklist != null && bookmarklist.size() > 0) {

            if (bla == null) {
                recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycleview2);

                RecyclerView.LayoutManager lml = new LinearLayoutManager(getActivity());
                recyclerView.setLayoutManager(lml);

                if (bookmarklist != null) {
                    bla = new BookmarkListAdapter((FragmentActivity) getContext(), bookmarklist);
                    recyclerView.setAdapter(bla);
                }
            } else {
                bla.loadData(bookmarklist);
            }
        } else {
            bla.loadData(bookmarklist);
            Toast.makeText(getActivity(), "The Bookmark List is Empty", Toast.LENGTH_SHORT).show();
        }
    }
}
