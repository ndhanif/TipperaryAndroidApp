package ienquire.ie.libfff;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.content.res.ResourcesCompat;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import java.io.InputStream;

import ienquire.ie.libfff.model.Clip;


/**
 * Video Player View.
 *
 * Created by diogo10 on 16/2/15.
 */
public class FeedFromTheFieldClipActivity extends Activity {

    private Clip clip;
    private MediaController mediaController;
    private String TAG = "fff";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.fff_activity_clip);

        VideoView videoView = (VideoView) findViewById(R.id.videoView);
        final ImageView fffImageView = (ImageView) findViewById(R.id.fffImageView);
        final ProgressBar fff_progressBar = (ProgressBar) findViewById(R.id.fff_progressBar);

        try {

            mediaController = new MediaController(this);
            mediaController.setAnchorView(videoView);

            Intent it = getIntent();
            if (it != null) {

                Bundle params = it.getExtras();

                if (params != null) {
                    clip = (Clip) getIntent().getExtras().get("clip");
                }
            }
//
            if (clip.getThumbnail() != null && !clip.getThumbnail().isEmpty())
                new DownloadImageTask(fffImageView).execute(clip.getThumbnail());
            else
                fffImageView.setImageDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.logorugby, null));


            Uri video = Uri.parse(clip.getUrl());
            videoView.setMediaController(mediaController);
            videoView.setVideoURI(video);


            mediaController.setAnchorView(videoView);
            mediaController.setMediaPlayer(videoView);

            MediaPlayer.OnPreparedListener onVideoPrepared = new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    fffImageView.setVisibility(View.GONE);
                    fff_progressBar.setVisibility(View.GONE);
                    mediaController.hide();
                    mp.setVolume(0, 0);
                }
            };

            MediaPlayer.OnCompletionListener onVideoCompleted = new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    Toast.makeText(FeedFromTheFieldClipActivity.this, clip.getMessage(), Toast.LENGTH_SHORT).show();
                    mediaController.hide();
                    finish();
                }
            };

            MediaPlayer.OnErrorListener onErrorListener = new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mp, int what, int extra) {
                    Toast.makeText(FeedFromTheFieldClipActivity.this, "Error connecting", Toast.LENGTH_SHORT).show();
                    finish();
                    return false;
                }
            };


            videoView.setOnCompletionListener(onVideoCompleted);
            videoView.setOnPreparedListener(onVideoPrepared);
            videoView.setOnErrorListener(onErrorListener);
            videoView.start();





        } catch (Exception e) {
            Toast.makeText(this, "Error connecting", Toast.LENGTH_SHORT).show();
            finish();
        }

    }


    /**
     * Hide controller after created screen
     */
    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mediaController.isShowing()) {
                    mediaController.hide();
                }
            }
        }, 1000);
    }


    /**
     * Download image from the internet.
     */
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urls[0]).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                mIcon11 = null;
            }
            return mIcon11;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            if (result != null) {
                bmImage.setImageBitmap(result);
                bmImage.startAnimation(AnimationUtils.loadAnimation(FeedFromTheFieldClipActivity.this, android.R.anim.fade_in));
            }
        }
    }



}








