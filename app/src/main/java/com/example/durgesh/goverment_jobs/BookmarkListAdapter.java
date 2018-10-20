package com.example.durgesh.goverment_jobs;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class BookmarkListAdapter extends RecyclerView.Adapter<BookmarkListAdapter.MyHolder> {

    private Context activity;
    ArrayList<JobDataType> booklist = new ArrayList<>();

    Button share, more;

    public BookmarkListAdapter(FragmentActivity activity, ArrayList<JobDataType> bookmarklist) {
        this.activity = activity;
        booklist = bookmarklist;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(activity);
        View myView = layoutInflater.inflate(R.layout.card_main_postpage, parent, false);

        return new MyHolder(myView);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.pn.setText(booklist.get(position).getPostname());
        holder.pd.setText(booklist.get(position).getPostdate());
        holder.bookmark.setChecked(true);
    }

    @Override
    public int getItemCount() {
        return booklist.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView pn, pd;
        CheckBox bookmark;


        public MyHolder(final View itemView) {
            super(itemView);

            pn = (TextView) itemView.findViewById(R.id.pname);
            pd = (TextView) itemView.findViewById(R.id.pdate);

            bookmark = (CheckBox) itemView.findViewById(R.id.book);
            share = (Button) itemView.findViewById(R.id.share);
            more = (Button) itemView.findViewById(R.id.more);

//            if (bookmark.isChecked() != true) {
//                bookmark.setChecked(true);
//            }

            bookmark.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    SharedPreference sharedPreference = new SharedPreference();
                    sharedPreference.removeFavorite(activity, booklist.get(getAdapterPosition()));

                    booklist.remove(booklist.get(getAdapterPosition()));
                    notifyDataSetChanged();
                }
            });


            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent in = new Intent(activity, FullPostDetailPage.class);

                    in.putExtra("PostName", booklist.get(getAdapterPosition()).getPostname());
                    in.putExtra("PostDate", booklist.get(getAdapterPosition()).getPostdate());
                    in.putExtra("PostLink", booklist.get(getAdapterPosition()).getPostlink());

                    activity.startActivity(in);

                }
            });

            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, booklist.get(getAdapterPosition()).getPostname() + " " + booklist.get(getAdapterPosition()).getPostdate());
                    sendIntent.setType("text/plain");

                    activity.startActivity(Intent.createChooser(sendIntent, "Share post"));

                }
            });

        }
    }

    public void loadData(ArrayList<JobDataType> ob) {
        if (booklist != null)
            booklist.clear();

        booklist = ob;
        notifyDataSetChanged();
    }
}

