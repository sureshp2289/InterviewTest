package viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.databinding.BaseObservable;

import com.example.taskinterview.R;
import com.example.taskinterview.databinding.ActivityMainBinding;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;

public class MainViewModel extends BaseObservable {
    public static final String API_KEY = "AIzaSyAoD6OqoFVKY-mCL4XNYxzCIjj6uDCUVFk";
    com.example.taskinterview.databinding.ActivityMainBinding mainBinding;
    Context context;
    private YouTubePlayer mPlayer;

    public MainViewModel(Context context, ActivityMainBinding mainBinding) {
        this.mainBinding = mainBinding;
        this.context = context;

    }

    public void load() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int displayHeight = displayMetrics.heightPixels;
        int dialogWindowHeight = 0;
        if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            dialogWindowHeight = (int) (displayHeight * 0.35f);
            mainBinding.youtubePlayerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dialogWindowHeight));
        } else {
            dialogWindowHeight = (int) (displayHeight * 0.8f);
            mainBinding.youtubePlayerView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dialogWindowHeight));
        }
        mainBinding.youtubePlayerView.initialize(API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer player, boolean wasRestored) {
                if (null == player) return;

                mPlayer = player;

                if (!wasRestored) {
                    player.cuePlaylist("PLdkIZKEO2-4_bC8DPLLYMBm6KPPdgWJGZ");
                }
                player.setPlayerStyle(YouTubePlayer.PlayerStyle.CHROMELESS);
                mainBinding.videoControl.setVisibility(View.VISIBLE);
                // Add listeners to YouTubePlayer instance
                player.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onAdStarted() {
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason arg0) {
                    }

                    @Override
                    public void onLoaded(String arg0) {
                    }

                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onVideoEnded() {
                    }

                    @Override
                    public void onVideoStarted() {
                    }
                });


                player.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onBuffering(boolean arg0) {
                    }

                    @Override
                    public void onPaused() {
                    }

                    @Override
                    public void onPlaying() {

                    }

                    @Override
                    public void onSeekTo(int arg0) {

                    }

                    @Override
                    public void onStopped() {
                    }
                });
                player.setPlaylistEventListener(new YouTubePlayer.PlaylistEventListener() {
                    @Override
                    public void onPrevious() {

                    }

                    @Override
                    public void onNext() {

                    }

                    @Override
                    public void onPlaylistEnded() {

                    }
                });

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                Toast.makeText(context, "Failed to initialize.", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_video:
                if (null != mPlayer && !mPlayer.isPlaying())
                    mPlayer.play();
                break;
            case R.id.pause_video:
                if (null != mPlayer && mPlayer.isPlaying())
                    mPlayer.pause();
                break;
            case R.id.previous:
                if (null != mPlayer && mPlayer.hasPrevious())
                    mPlayer.previous();
                break;
            case R.id.next:
                if (null != mPlayer && mPlayer.hasNext())
                    mPlayer.next();
                break;
            case R.id.backward:
                mPlayer.seekRelativeMillis(-10000);
                break;
            case R.id.forward:
                mPlayer.seekRelativeMillis(10000);
                break;
        }

    }
}
