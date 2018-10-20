package com.example.durgesh.goverment_jobs;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.durgesh.goverment_jobs.app.AppController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PostlistPage extends AppCompatActivity {

    RelativeLayout relativeLayout;
    RecyclerView rv;
    int data;
    MainCardAdapter mca;
    int array_size;
    int i = 0;

    String URL_Job_List = "http://androidapi.w3blogs.com/goverment_jobs/Job_list.php" +
            "";

    ArrayList<String> post_name = new ArrayList<>();
    ArrayList<String> post_date = new ArrayList<>();
    ArrayList<String> post_link=new ArrayList<>();

    ArrayList<JobDataType> datalist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        relativeLayout = (RelativeLayout) findViewById(R.id.activity_main2);
        rv = (RecyclerView) findViewById(R.id.recycleview1);

        Intent intent1 = getIntent();
        data = intent1.getIntExtra("category", 0);

        array_size = post_name.size();

        RecyclerView.LayoutManager lml = new LinearLayoutManager(PostlistPage.this);
        rv.setLayoutManager(lml);

        jobpostpage();

    }

    private void jobpostpage() {

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_Job_List, new Response.Listener<String>() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject object = new JSONObject(response);
                    JSONArray jsonArray = object.getJSONArray("Job");

                    for (int i = 0; i < jsonArray.length(); i++) {

                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String p_name = jsonObject.getString("title");
                        String p_date = jsonObject.getString("publish_date");
                        String p_link=jsonObject.getString("link");

                        post_name.add(p_name);
                        post_date.add(p_date);
                        post_link.add(p_link);
                    }


                    array_size = post_name.size();
                    nextpage();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> param = new HashMap<>();
                param.put("cid", "" + data);
                return param;
            }
        };
        AppController.getInstance().addToRequestQueue(stringRequest);

    }


    public void next(View view) {
        nextpage();
    }

    public void prev(View view) {
        previouspage();
    }

    private void nextpage() {
        if (array_size != 0) {
            if (i < array_size) {
                datalist.clear();
                for (int t = 0; i < array_size && t < 10; i++, t++) {
                    JobDataType ob = new JobDataType();

                    ob.setPostname(post_name.get(i));
                    ob.setPostdate(post_date.get(i));
                    ob.setPostlink(post_link.get(i));

                    datalist.add(ob);

                }
                mca = new MainCardAdapter(datalist, PostlistPage.this);
                rv.setAdapter(mca);

            } else {
                Toast.makeText(this, "End of the List", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "The List is Empty", Toast.LENGTH_SHORT).show();
        }
    }


    private void previouspage() {
        if (i > 10) {
            datalist.clear();
            Toast.makeText(this, "" + i, Toast.LENGTH_SHORT).show();
            i /= 10;
            i *= 10;
            i -= 10;
            if (array_size != 0) {
                if (i >= 0) {
                    for (int t = 0; i < array_size && t < 10; i++, t++) {
                        JobDataType ob = new JobDataType();

                        ob.setPostname(post_name.get(i));
                        ob.setPostdate(post_date.get(i));
                        ob.setPostlink(post_link.get(i));

                        datalist.add(ob);

                    }
                    mca = new MainCardAdapter(datalist, PostlistPage.this);
                    rv.setAdapter(mca);

                } else {
                    Toast.makeText(this, "End of the List", Toast.LENGTH_SHORT).show();
                }

            } else
                Toast.makeText(this, "The List is Empty", Toast.LENGTH_SHORT).show();
        }
    }
}


