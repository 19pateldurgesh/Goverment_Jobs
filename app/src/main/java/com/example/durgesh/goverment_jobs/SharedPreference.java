package com.example.durgesh.goverment_jobs;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SharedPreference {

    public static final String PREFS_NAME = "GOVJOB";
    public static final String BOOKMARK = "BookMark";

    public SharedPreference() {
        super();
    }

    public void saveFavorites(Context context, List<JobDataType> favorites) {
        SharedPreferences settings;
        SharedPreferences.Editor editor;

        settings = context.getSharedPreferences(PREFS_NAME,
                Context.MODE_PRIVATE);
        editor = settings.edit();

        Gson gson = new Gson();
        String jsonFavorites = gson.toJson(favorites);

        editor.putString(BOOKMARK, jsonFavorites);
        editor.commit();
    }

    public void addFavorite(Context context, JobDataType product) {
        List<JobDataType> favorites = getFavorites(context);

        if (favorites == null)
            favorites = new ArrayList<JobDataType>();
        int f=0;
        for (JobDataType m : favorites) {
            if (m.getPostname().equals(product.getPostname())) {
                f=1;
                break;
            }
        }
        if (f==0){
            favorites.add(product);
        }
        saveFavorites(context, favorites);
    }

    public void removeFavorite(Context context, JobDataType product) {
        ArrayList<JobDataType> favorites = getFavorites(context);
        if (favorites != null) {
            int i = 0;
            for (JobDataType m : favorites)  {
                if (m.getPostname().equals(product.getPostname())) {
                    favorites.remove(i);
                    break;
                }
                i++;
            }
            saveFavorites(context, favorites);
        }
    }

    public ArrayList<JobDataType> getFavorites(Context context) {
        SharedPreferences settings;
        List<JobDataType> favorites;

//        if (context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE) != null ) {
            settings = context.getSharedPreferences(PREFS_NAME,
                    Context.MODE_PRIVATE);

            if (settings.contains(BOOKMARK)) {
                String jsonFavorites = settings.getString(BOOKMARK, null);

                Gson gson = new Gson();
                JobDataType[] favoriteItems = gson.fromJson(jsonFavorites,
                        JobDataType[].class);

                favorites = Arrays.asList(favoriteItems);
                favorites = new ArrayList<JobDataType>(favorites);
            } else
                return null;

            return (ArrayList<JobDataType>) favorites;
//        }
//        return null;
    }




}
