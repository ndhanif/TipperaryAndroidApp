package ienquire.ie.libfff.dao;


import android.app.*;
import android.content.*;

import com.google.gson.*;

import java.util.*;

import ienquire.ie.libfff.model.*;

/**
 * @author diogo
 */
public class ClipDAO {

    private static final String TAG = "fff";

    public ArrayList<Clip> getAll(Activity context) {

        ArrayList<Clip> clips = new ArrayList<Clip>();

        try {
            SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);

            Gson gson = new Gson();
            //Log.i(TAG,"clips json = " + prefs.getString("clips",""));
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(prefs.getString("clips", "")).getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                clips.add(gson.fromJson(array.get(i), Clip.class));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clips;
    }

    public boolean insert(Clip clip, Context context) {

        try {
            SharedPreferences prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
            ArrayList<Clip> clips = new ArrayList<Clip>();
            Gson gson = new Gson();

            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(prefs.getString("clips", "")).getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                clips.add(gson.fromJson(array.get(i), Clip.class));
            }


            clips.add(clip);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("clips", gson.toJson(clips));
            return editor.commit();

        } catch (Exception e) {
            return false;
        }

    }

    public boolean delete(Activity context, Clip clip) {

        ArrayList<Clip> clips = new ArrayList<Clip>();
        int removeIndex = 0;

        try {
            SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(prefs.getString("clips", "")).getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                clips.add(gson.fromJson(array.get(i), Clip.class));
                if (clips.get(i).getName().equals(clip.getName()) && clips.get(i).getUrl().equals(clip.getUrl()))
                    removeIndex = i;
            }

            clips.remove(removeIndex);

            SharedPreferences.Editor editor = prefs.edit();
            editor.putString("clips", gson.toJson(clips));
            editor.apply();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }




    }

    public Clip getClip(Activity context, String clip) {

        ArrayList<Clip> clips = new ArrayList<Clip>();
        int clipIndex = 0;

        try {
            SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);

            Gson gson = new Gson();
            JsonParser parser = new JsonParser();
            JsonArray array = parser.parse(prefs.getString("clips", "")).getAsJsonArray();
            for (int i = 0; i < array.size(); i++) {
                clips.add(gson.fromJson(array.get(i), Clip.class));
                if (clips.get(i).getName().equals(clip)) clipIndex = i;
            }

            return clips.get(clipIndex);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }

    public List<Clip> getAll(Activity context, String category){

        List<Clip> list = getAll(context);
        List<Clip> newList = new ArrayList<>();

        for(Clip clip: list){
            if(clip.getCategory().equals(category))
                newList.add(clip);
        }

        return newList;
    }



}