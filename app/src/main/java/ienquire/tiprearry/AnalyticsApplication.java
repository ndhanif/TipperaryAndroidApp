package ienquire.tiprearry;

//import android.content.Context;
//import android.support.multidex.MultiDex;
//import android.support.multidex.MultiDexApplication;
//import android.util.Log;
//
//import com.google.android.gms.analytics.GoogleAnalytics;
//import com.google.android.gms.analytics.Tracker;
//
//public class AnalyticsApplication extends MultiDexApplication {
//
//    private Tracker mTracker;
//    private static final String PROPERTY_ID = "UA-83676494-1";
//
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        MultiDex.install(this);
//    }
//
//    synchronized public Tracker getDefaultTracker() {
//        if (mTracker == null) {
//            GoogleAnalytics analytics = GoogleAnalytics.getInstance(this);
//
//            mTracker = analytics.newTracker(R.xml.app_tracker);
//        }
//        return mTracker;
//    }
//
//    public void init(Context ctx) {
//        try {
//
//            if (mTracker == null && ctx != null) {
//                mTracker = GoogleAnalytics.getInstance(ctx).newTracker(PROPERTY_ID);
//            }
//        } catch (Exception e) {
//            Log.d("Cricket", "init, e=" + e);
//        }
//    }
//    @Override
//    protected void attachBaseContext(Context base) {
//        super.attachBaseContext(base);
//        MultiDex.install(getBaseContext());
//    }
//
//
//}






