package com.example.durgesh.goverment_jobs;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Date;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Created by Durgesh on 7/6/2018.
 */

public class letestpostAdapter extends RecyclerView.Adapter<letestpostAdapter.MyHolder> {

    private Context con;
//    ArrayList<String> pname=new ArrayList<>();
//    ArrayList<String> pdate=new ArrayList<>();

    ArrayList<JobDataType> listofdata = new ArrayList<>();
    ArrayList<JobDataType> bookdata = new ArrayList<>();

    public letestpostAdapter(FragmentActivity context, ArrayList<JobDataType> datalist) {
        con = context;
        listofdata = datalist;
    }


//    public letestpostAdapter(ArrayList<String> post_name, ArrayList<String> post_date, Context context) {
//
//        con=context;
//        pname=post_name;
//        pdate=post_date;
//    }


    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(con);
        View myView = layoutInflater.inflate(R.layout.card_main_postpage, parent, false);
        return new MyHolder(myView);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {

//        holder.pn.setText(pname.get(position));
//        holder.pd.setText(pdate.get(position));
        holder.pn.setText(listofdata.get(position).getPostname());
        holder.pd.setText(listofdata.get(position).getPostdate());

        if (bookdata != null) {
            boolean flag=false;

            for (int i = 0; i < bookdata.size(); i++) {
                if (bookdata.get(i).getPostname().equalsIgnoreCase(listofdata.get(position).getPostname())) {
                    flag=true;
                    break;
                }
            }

            if(flag==true)
                holder.bookmark.setChecked(true);
            else
                holder.bookmark.setChecked(false);

            Log.d("ddddddddddddd",flag+"");
        }


//        holder.bookmark.setOnCheckedChangeListener(null);
    }


    @Override
    public int getItemCount() {

        return (listofdata.size());
    }

    public class MyHolder extends RecyclerView.ViewHolder {

        public TextView pn, pd;
        Button share, more;
        //    Button bookmark;
        CheckBox bookmark;

        public MyHolder(final View itemView) {
            super(itemView);
            pn = (TextView) itemView.findViewById(R.id.pname);
            pd = (TextView) itemView.findViewById(R.id.pdate);

            SharedPreference sharedPreference = new SharedPreference();
            bookdata = sharedPreference.getFavorites(con);

//            bookmark= (Button) itemView.findViewById(R.id.book);
            bookmark = (CheckBox) itemView.findViewById(R.id.book);
            share = (Button) itemView.findViewById(R.id.share);
            more = (Button) itemView.findViewById(R.id.more);
//
//            bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//                @Override
//                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
//
//                    if (bookmark.isChecked()==true){
//                        Toast.makeText(con, "Add", Toast.LENGTH_SHORT).show();
//                        SharedPreference sharedPreference=new SharedPreference();
//                        sharedPreference.addFavorite(con,listofdata.get(getAdapterPosition()));
//                    }else{
//                        Toast.makeText(con, "Remove", Toast.LENGTH_SHORT).show();
//                        SharedPreference sharedPreference=new SharedPreference();
//                        sharedPreference.removeFavorite(con,listofdata.get(getAdapterPosition()));
//                    }
//                }
//            });

//            bookmark.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
////                    Toast.makeText(con, "Called", Toast.LENGTH_SHORT).show();
//
////                    SharedPreference sharedPreference=new SharedPreference();
////                    sharedPreference.addFavorite(con,listofdata.get(getAdapterPosition()));
//                    if (bookmark.isChecked()==true){
//                        Toast.makeText(con, "Called", Toast.LENGTH_SHORT).show();
//                        SharedPreference sharedPreference=new SharedPreference();
//                        sharedPreference.addFavorite(con,listofdata.get(getAdapterPosition()));
//                    }else{
//                        SharedPreference sharedPreference=new SharedPreference();
//                        sharedPreference.removeFavorite(con,listofdata.get(getAdapterPosition()));
//                    }
//
//                }
//            });


            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent in = new Intent(con, FullPostDetailPage.class);
//        in.putExtra("PostName",pname.get(getAdapterPosition()));
//        in.putExtra("PostDate",pdate.get(getAdapterPosition()));
                    in.putExtra("PostName", listofdata.get(getAdapterPosition()).getPostname());
                    in.putExtra("PostDate", listofdata.get(getAdapterPosition()).getPostdate());
                    in.putExtra("PostLink",listofdata.get(getAdapterPosition()).getPostlink());

                    con.startActivity(in);

                }
            });


            share.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
//        sendIntent.putExtra(Intent.EXTRA_TEXT, pname.get(getAdapterPosition()) + " " + pdate.get(getAdapterPosition()));
                    sendIntent.putExtra(Intent.EXTRA_TEXT, listofdata.get(getAdapterPosition()).getPostname() + " " + listofdata.get(getAdapterPosition()).getPostdate());
                    sendIntent.setType("text/plain");

                    con.startActivity(Intent.createChooser(sendIntent, "Share post"));


                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    Toast.makeText(con, pname.get(getAdapterPosition()), Toast.LENGTH_SHORT).show();

                    Intent in = new Intent(con, FullPostDetailPage.class);
//                        in.putExtra("PostName",pname.get(getAdapterPosition()));
//                        in.putExtra("PostDate",pdate.get(getAdapterPosition()));

                    in.putExtra("PostName", listofdata.get(getAdapterPosition()).getPostname());
                    in.putExtra("PostDate", listofdata.get(getAdapterPosition()).getPostdate());
                    in.putExtra("PostLink",listofdata.get(getAdapterPosition()).getPostlink());
                    con.startActivity(in);

                }
            });


            bookmark.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                    if (bookmark.isChecked() == true) {
//                    Toast.makeText(con, "Add", Toast.LENGTH_SHORT).show();
                        SharedPreference sharedPreference = new SharedPreference();
                        sharedPreference.addFavorite(con, listofdata.get(getAdapterPosition()));
                    } else {
//                    Toast.makeText(con, "Remove", Toast.LENGTH_SHORT).show();
                        SharedPreference sharedPreference = new SharedPreference();
                        sharedPreference.removeFavorite(con, listofdata.get(getAdapterPosition()));
                    }
                    SharedPreference sharedPreference = new SharedPreference();
                    bookdata = sharedPreference.getFavorites(con);

                }
            });

        }
    }

}
