package com.example.durgesh.goverment_jobs;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainCardAdapter extends RecyclerView.Adapter<MainCardAdapter.MyHolder> {

    PostlistPage activity;

    ArrayList<JobDataType> listofdata = new ArrayList<>();
    ArrayList<JobDataType> bookdata = new ArrayList<>();

    public MainCardAdapter(ArrayList<JobDataType> datalist, PostlistPage postlistPage) {
        activity = postlistPage;
        listofdata = datalist;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View myView = layoutInflater.inflate(R.layout.card_main_postpage, parent, false);
        return new MyHolder(myView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        holder.pn.setText(listofdata.get(position).getPostname());
        holder.pd.setText(listofdata.get(position).getPostdate());
        if (bookdata != null) {
            boolean flag = false;

            for (int i = 0; i < bookdata.size(); i++) {
                if (bookdata.get(i).getPostname().equalsIgnoreCase(listofdata.get(position).getPostname())) {
                    flag = true;
                }
            }

            if (flag == true)
                holder.bookmark.setChecked(true);
            else
                holder.bookmark.setChecked(false);
        }

        holder.bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (holder.bookmark.isChecked() == true) {
                    SharedPreference sharedPreference = new SharedPreference();
                    sharedPreference.addFavorite(activity, listofdata.get(holder.getAdapterPosition()));
                } else {
                    SharedPreference sharedPreference = new SharedPreference();
                    sharedPreference.removeFavorite(activity, listofdata.get(holder.getAdapterPosition()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return (listofdata.size());
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView pn, pd;
        Button share, more;
        CheckBox bookmark;

        public MyHolder(final View itemView) {
            super(itemView);
            pn = (TextView) itemView.findViewById(R.id.pname);
            pd = (TextView) itemView.findViewById(R.id.pdate);
            SharedPreference sharedPreference = new SharedPreference();
            bookdata = sharedPreference.getFavorites(activity);

            bookmark = (CheckBox) itemView.findViewById(R.id.book);
            share = (Button) itemView.findViewById(R.id.share);
            more = (Button) itemView.findViewById(R.id.more);

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent in = new Intent(activity, FullPostDetailPage.class);

                    in.putExtra("PostName", listofdata.get(getAdapterPosition()).getPostname());
                    in.putExtra("PostDate", listofdata.get(getAdapterPosition()).getPostdate());
                    in.putExtra("PostLink",listofdata.get(getAdapterPosition()).getPostlink());

                    activity.startActivity(in);
                }
            });


            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, listofdata.get(getAdapterPosition()).getPostname() + " " + listofdata.get(getAdapterPosition()).getPostdate());

                    sendIntent.setType("text/plain");

                    activity.startActivity(Intent.createChooser(sendIntent, "Share post"));
                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent in = new Intent(activity, FullPostDetailPage.class);
                    in.putExtra("PostName", listofdata.get(getAdapterPosition()).getPostname());
                    in.putExtra("PostDate", listofdata.get(getAdapterPosition()).getPostdate());
                    in.putExtra("PostLink",listofdata.get(getAdapterPosition()).getPostlink());

                    activity.startActivity(in);
                }
            });
        }
    }
}
