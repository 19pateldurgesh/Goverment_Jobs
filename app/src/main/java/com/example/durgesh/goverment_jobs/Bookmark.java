package com.example.durgesh.goverment_jobs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Durgesh on 2/20/2018.
 */
public class Bookmark extends Fragment {

    RecyclerView recyclerView;
    ArrayList<JobDataType> bookmarklist=null;
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

        Log.d("vvvvvvvvvvvvvvvvvvvvvv", "activity created");
        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycleview2);
        Log.d("v1", "recycle view created");
        SharedPreference sharedPreference = new SharedPreference();
        Log.d("v2", "Shared Praference ob");
        bookmarklist = sharedPreference.getFavorites(getActivity());
        Log.d("v3", "get Favorite");
        RecyclerView.LayoutManager lml = new LinearLayoutManager(getActivity());
        Log.d("v4", "ll Manager");
        recyclerView.setLayoutManager(lml);
        Log.d("v5", "set ll manager");
        if (bookmarklist != null) {
            bla = new BookmarkListAdapter((FragmentActivity) getContext(), bookmarklist);
            Log.d("v6", "BLA");
            recyclerView.setAdapter(bla);
            Log.d("v7", "set Adapter");
        }

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser && isResumed()) {

            loadData();
            Log.d("onresumedcalled","visible");

//            SharedPreference sharedPreference = new SharedPreference();
//            bookmarklist = sharedPreference.getFavorites(getActivity());

//            if (bookmarklist != null) {
//                bla.loadData(bookmarklist);
//            }else{
//                Toast.makeText(getActivity(), "The Bookmark List is Empty", Toast.LENGTH_SHORT).show();
//            }
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
            Toast.makeText(getActivity(), "The Bookmark List is Empty", Toast.LENGTH_SHORT).show();
        }
    }
}
