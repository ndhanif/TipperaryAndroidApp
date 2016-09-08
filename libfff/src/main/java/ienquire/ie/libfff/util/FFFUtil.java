package ienquire.ie.libfff.util;

import android.app.*;
import android.content.*;
import android.content.pm.*;
import android.os.*;
import android.text.*;
import android.util.*;

import com.google.android.gms.common.*;
import com.google.android.gms.gcm.*;
import com.google.gson.*;

import java.io.*;
import java.net.*;
import java.util.*;

import ienquire.ie.libfff.*;
import ienquire.ie.libfff.dao.*;
import ienquire.ie.libfff.model.*;
import ienquire.ie.libfff.ws.*;


/**
 * Created by barryoreilly on 05/10/15.
 * Guinness PRO12
 */
public class FFFUtil {

    private static final String TAG = "fff";
    public static String REG_ID = "regId";
    //public static String WS_URL = "http://fffwebservice-env.elasticbeanstalk.com/rest/";
    //public static String WS_URL = "http://192.168.1.3:8081/fffwebservice/rest/";
    public static String WS_URL = "http://fffwebservice-env.eu-west-1.elasticbeanstalk.com/rest/";
    private static String GOOGLE_PROJECT_ID = "85852907423";
    private static GoogleCloudMessaging gcm;
    private static String REG = "";
    public String app_name;

    public static ArrayList<Clip> returnListOfClips(Activity ctx) {

        ClipDAO clipDAO = new ClipDAO();
        ArrayList<Clip> clips = clipDAO.getAll(ctx);
        return clips;
    }

    public static void enableNotification(Activity ctx, boolean tostop) {
        SharedPreferences prefs = ctx.getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean("enableNotification", tostop);
        editor.apply();
    }

    public static void deleteNotification(Activity ctx, Clip clip) {
        new ClipDAO().delete(ctx, clip);
    }

    public static void saveNotification(Context context, Clip clip) {
        new ClipDAO().insert(clip, context);
    }

    public static Clip watchNotification(Activity ctx, String clip) {
        return new ClipDAO().getClip(ctx, clip);
    }

    public static boolean isEnableNotification(Context ctx) {
        SharedPreferences prefs = ctx.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return prefs.getBoolean("enableNotification", true);
    }

    public static String getClientToken(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(TAG, Context.MODE_PRIVATE);
        return prefs.getString(REG_ID, "");
    }

    private static void registerInBackground(final Activity context, final String app_name) {

        new AsyncTask<Activity, Void, String>() {
            @Override
            protected String doInBackground(Activity... params) {

                try {
                    REG = gcm.register(GOOGLE_PROJECT_ID);
                    Log.i(TAG, "FFF - reg = " + REG);


                    Customer customer = new Customer();
                    customer.setToken(REG);
                    customer.setDevice(Build.MODEL);
                    customer.setApp(app_name + " " + getAppVersion(params[0]));
                    customer.setIp(getIP());
                    customer.setDevice_type("1");

                    if (new CustomerRESTFULL().add(customer)) {
                        Log.i(TAG, "sent customer");

                        //SAMPLE LIST ----------------------------------------------------------------------------------
                        ArrayList<Clip> initialTest = new ArrayList<Clip>();
                        Gson gson = new Gson();
                        initialTest.add(new Clip("That is a sample 1", "Great video", "https://s3-eu-west-1.amazonaws.com/ienquirefff/Goal__messi_1.mp4", "Ienquire","ttt"));
                        SharedPreferences prefs = params[0].getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = prefs.edit();
                        editor.putString("clips", gson.toJson(initialTest));
                        editor.commit();
                        Log.i(TAG, "add sample list");
                        //SAMPLE LIST ----------------------------------------------------------------------------------
                        Log.i(TAG, "store id");
                        storeRegistrationId(params[0], REG);

                    } else {
                        Log.i(TAG, "error to add customer");
                    }

                } catch (IOException e) {
                    Log.i(TAG, "error to generate REG = " + e.getMessage());
                    //e.printStackTrace();
                } catch (Exception e) {
                    Log.i(TAG, "error fff " + e.getMessage());
                    //e.printStackTrace();
                }


                return "";
            }

            @Override
            protected void onPostExecute(String message) {
//                try {
//                    gcm.unregister();
//                } catch (IOException e) {
//                    Log.i("app", "onPostExecute e = " + e.getMessage());
//                }
            }
        }.execute(context, null, null);

    }

    private static void checkInBackground(final Activity context, final String app_name) {

        new AsyncTask<Activity, Void, String>() {
            @Override
            protected String doInBackground(Activity... params) {

                try {
                    if (new CustomerRESTFULL().checkCustomer(REG)) {
                        return "1";
                    } else {
                        return "2";
                    }
                } catch (Exception e) {
                    return "0";
                }

            }

            @Override
            protected void onPostExecute(String message) {
                if (message.equals("2"))
                    registerInBackground(context, app_name);
            }
        }.execute(context, null, null);

    }

    private static String getRegistrationId(Activity ctx) {
        final SharedPreferences prefs = ctx.getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        String registrationId = prefs.getString(REG_ID, "");
        if (registrationId.isEmpty()) {
            System.out.println("Registration not found.");
            return "";
        }
        int registeredVersion = prefs.getInt("fff", Integer.MIN_VALUE);
        int currentVersion = getAppVersion(ctx);
        if (registeredVersion != currentVersion) {
            System.out.println("App version changed.");
            return "";
        }
        return registrationId;
    }

    //UTIL METHODS -----------------------------------------------------------------------------------------------

    private static void storeRegistrationId(Activity context, String regId) {
        final SharedPreferences prefs = context.getApplicationContext().getSharedPreferences(TAG, Context.MODE_PRIVATE);
        int appVersion = 1;
        try {
            appVersion = getAppVersion(context.getApplicationContext());
        } catch (Exception e) {
            appVersion = 999;
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(REG_ID, regId);
        editor.putInt("fff", appVersion);
        editor.apply();
    }

    private static int getAppVersion(Context context) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {

            throw new RuntimeException(e);
        }
    }

    private static String getIP() {
        URL ipAdress;
        String ip;

        try {
            ipAdress = new URL("http://checkip.amazonaws.com");
            BufferedReader in = new BufferedReader(new InputStreamReader(ipAdress.openStream()));
            ip = in.readLine();
        } catch (Exception e) {
            ip = "000.000.00.00";
        }

        return ip;
    }

    private static boolean checkPlayServices(Activity context) {
        int resultCode = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        if (resultCode != ConnectionResult.SUCCESS) {
            return false;
        }
        return true;
    }

    public String registerDevice(Activity ctx, String appName) throws Exception {
        this.app_name = appName;

        if (!checkPlayServices(ctx)) {
            throw new Exception("FFF - Google Play Services not supported. Please install and configure Google Play Store.");
        } else {

            gcm = GoogleCloudMessaging.getInstance(ctx);
            REG = getRegistrationId(ctx);

            if (TextUtils.isEmpty(REG)) {
                registerInBackground(ctx, this.app_name);
                return "1";
            } else {
                //checkInBackground(ctx);
                return "2";
            }
        }

    }

    public void watchClip(Activity activity, Clip clip) {
        Intent intent = new Intent(activity, FeedFromTheFieldClipActivity.class);
        intent.putExtra("clip", clip);
        activity.startActivity(intent);
    }

    public FragmentFeedFromTheField getFieldFromTheFieldFragment() {
        return new FragmentFeedFromTheField();
    }

    //UTIL METHODS -----------------------------------------------------------------------------------------------


}