package com.example.durgesh.goverment_jobs;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.durgesh.goverment_jobs.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Letest_Post extends Fragment {

    letestpostAdapter lpa;
    RecyclerView rv;
    String URL_Job_List = "http://androidapi.w3blogs.com/goverment_jobs/Latest_joblist.php";

    ArrayList<String> post_name = new ArrayList<>();
    ArrayList<String> post_date = new ArrayList<>();

    ArrayList<JobDataType> datalist = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_letest_post, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        rv = (RecyclerView) getActivity().findViewById(R.id.recycleview1);

        jobpostpage();

    }

    private void jobpostpage() {
        post_name.clear();
        post_date.clear();
        datalist.clear();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Job_List, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("Job");
                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        JobDataType ob = new JobDataType();

                        ob.setPostname(jsonObject.getString("title"));
                        ob.setPostdate(jsonObject.getString("publish_date"));
                        ob.setPostlink(jsonObject.getString("link"));

                        datalist.add(ob);
                    }

                    RecyclerView.LayoutManager lml = new LinearLayoutManager(getActivity());
                    rv.setLayoutManager(lml);

                    lpa = new letestpostAdapter((FragmentActivity) getContext(), datalist);
                    rv.setAdapter(lpa);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }


        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(stringRequest);

    }

//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if (isVisibleToUser && isResumed()) {
//
//            this.onActivityCreated(null);
//
//        }
//    }
}
