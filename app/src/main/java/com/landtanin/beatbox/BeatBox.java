package com.landtanin.beatbox;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tanin on 07/11/2017.
 *
 * BeatBox do work which related to asset management
 *  finding assets
 *  keeping track of them
 *  playing them as sounds
 *
 */
public class BeatBox {

    private static final String TAG = "BeatBox";
    private static final String SOUNDS_FOLDER = "sample_sounds";
    private static final int MAX_SOUNDS = 5;

    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();
    private SoundPool mSoundPool;

    // ask for context since we need to access to AssetManager
    public BeatBox(Context context) {
        mAssets = context.getAssets();

        // SoundPool(maximum sounds to play at a given time, kind of audio stream, quality)
        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
    }

    public void play(Sound sound){

        Integer soundId = sound.getmSoundId();
        if (soundId == null) {
            return;
        }
        mSoundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f);

    }

    public void release() {

        mSoundPool.release();

    }

    private void loadSounds() {

        String[] soundNames;
        try {
            soundNames = mAssets.list(SOUNDS_FOLDER);
            Log.e(TAG, "Found " + soundNames.length + " sounds");
        } catch (IOException ioe) {
            Log.e("TAG", "Could not list sounds");
            return;
        }


        for (String filename : soundNames) {
            try {
                String assetPath = SOUNDS_FOLDER + "/" + filename;
                Sound sound = new Sound(assetPath);
                load(sound);
                mSounds.add(sound);
            } catch (IOException ioe) {
                Log.e(TAG, "Could not load sound ", ioe);
            }
        }
    }

    private void load(Sound sound) throws IOException {

        AssetFileDescriptor afd = mAssets.openFd(sound.getmAssetPath());

        // load file into SoundPool for later placback
        int soundId = mSoundPool.load(afd, 1);

        sound.setmSoundId(soundId);

    }

    public List<Sound> getSounds() {
        return mSounds;
    }

}
