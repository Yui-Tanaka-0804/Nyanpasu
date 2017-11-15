package com.example.a2170131.nyanpasu;

import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private SoundPool mSoundPool;
    private int mSoundId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playFromSoundPool();
            }
        });

    }    @Override
    protected void onResume() {
        super.onResume();
        final AudioAttributes attrs = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build();
        new SoundPool.Builder()
                .setAudioAttributes(attrs)
                .setMaxStreams(1);

        // 予め音声データを読み込む
        mSoundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        mSoundId = mSoundPool.load(getApplicationContext(), R.raw.nc81088, 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        // リリース
        mSoundPool.release();
    }

    private void playFromSoundPool() {
        mSoundPool.play(mSoundId, 1.0F, 1.0F, 0, 0, 1.0F);
    }

}
