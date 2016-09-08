package ienquire.ie.libfff.gcm;

import android.app.*;
import android.content.*;
import android.os.*;
import android.support.v4.app.*;
import android.util.*;

import com.google.android.gms.gcm.*;
import com.google.gson.*;

import ienquire.ie.libfff.*;
import ienquire.ie.libfff.R;
import ienquire.ie.libfff.model.*;
import ienquire.ie.libfff.util.*;

public class GCMNotificationIntentService extends IntentService {

    public static final int NOTIFICATION_ID = 1;
    public static final String TAG = "fff";
    private NotificationManager mNotificationManager;

    public GCMNotificationIntentService() {
        super("GcmIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        GoogleCloudMessaging gcm = GoogleCloudMessaging.getInstance(this);
        String messageType = gcm.getMessageType(intent);

        if (!extras.isEmpty()) {
            if (GoogleCloudMessaging.MESSAGE_TYPE_MESSAGE.equals(messageType)) {

                try {
                    if (FFFUtil.isEnableNotification(getApplicationContext())) {
                        Clip clip = new Gson().fromJson(extras.get("default").toString(), Clip.class);
                        sendNotification(clip);
                        FFFUtil.saveNotification(getApplicationContext(), clip);
                        Log.i(TAG, "sent notification...");
                    }
                } catch (Exception e) {
                    Log.i(TAG, "error notification = " + e.getMessage());
                }

            }
        }
        GcmBroadcastReceiver.completeWakefulIntent(intent);
    }

    private void sendNotification(Clip clip) {

        String msg = "Click to see";
        mNotificationManager = (NotificationManager) getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent = new Intent(getApplicationContext(), FeedFromTheFieldClipActivity.class);
        intent.putExtra("clip", clip);
        PendingIntent contentIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                getApplicationContext()).setSmallIcon(R.drawable.logorugby)
                .setContentTitle(clip.getCategory() != null ? clip.getCategory() : "New video from feed from the field")
                .setStyle(new NotificationCompat.BigTextStyle().bigText(msg))
                .setVibrate(new long[]{1000, 1000, 1000, 1000, 1000})
                .setContentText(clip.getMessage() != null ? clip.getMessage() : msg);

        mBuilder.setContentIntent(contentIntent);
        mNotificationManager.notify(NOTIFICATION_ID, mBuilder.build());
    }


}
