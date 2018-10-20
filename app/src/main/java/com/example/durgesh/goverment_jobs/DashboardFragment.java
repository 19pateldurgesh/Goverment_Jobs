package com.example.durgesh.goverment_jobs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

public class DashboardFragment extends Fragment {

    GridView grid;
    String[] web = {"SSC", "UPSC", "Bank", "Railway", "Teachers", "Defence", "PSU", "Police", "Army",
            "Navy", "Air Force", "Engineeing", "10th Pass", "12th Pass"};

    int[] imageId = {R.drawable.a, R.drawable.upsc, R.drawable.bank, R.drawable.railway,
            R.drawable.teacher, R.drawable.darmy, R.drawable.psu, R.drawable.police,
            R.drawable.army, R.drawable.naval, R.drawable.air, R.drawable.eng,
            R.drawable.boy, R.drawable.download};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dashboard, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CustomGrid adapter = new CustomGrid(getActivity(), web, imageId);
        grid = (GridView) getActivity().findViewById(R.id.grid);
        grid.setAdapter(adapter);

        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int i, long id) {
                if (i == 0) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 1);
                    startActivity(intent);
                }
                if (i == 1) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 2);
                    startActivity(intent);
                }
                if (i == 2) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 3);
                    startActivity(intent);
                }
                if (i == 3) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 4);
                    startActivity(intent);
                }
                if (i == 4) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 5);
                    startActivity(intent);
                }
                if (i == 5) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 6);
                    startActivity(intent);
                }
                if (i == 6) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 7);
                    startActivity(intent);
                }
                if (i == 7) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 8);
                    startActivity(intent);
                }
                if (i == 8) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 9);
                    startActivity(intent);
                }
                if (i == 9) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 10);
                    startActivity(intent);
                }
                if (i == 10) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 11);
                    startActivity(intent);
                }
                if (i == 11) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 12);
                    startActivity(intent);
                }
                if (i == 12) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 13);
                    startActivity(intent);
                }
                if (i == 13) {
                    Intent intent = new Intent(getActivity(), PostlistPage.class);
                    intent.putExtra("category", 14);
                    startActivity(intent);
                }
            }
        });
    }
}
