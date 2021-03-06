package com.bytedance.videoplayer;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {

    private CustomizedVideoView videoView;
    private MediaController mediaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        videoView = findViewById(R.id.video_view);
        mediaController = new MediaController(this);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);

        Intent intent = getIntent();
        Uri realUri;
        Uri data = intent.getData();

        if(data != null) {
            realUri = data;
        } else {
            realUri = Uri.parse("android.resource://" + getPackageName() + "/raw/" + R.raw.a);
        }

        videoView.setVideoURI(realUri);
        videoView.start();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            getSupportActionBar().hide();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
            getSupportActionBar().show();
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }
    }
}
