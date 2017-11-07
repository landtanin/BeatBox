package com.landtanin.beatbox;

import android.content.Context;
import android.content.res.AssetManager;
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
    private AssetManager mAssets;
    private List<Sound> mSounds = new ArrayList<>();

    // ask for context since we need to access to AssetManager
    public BeatBox(Context context) {
        mAssets = context.getAssets();
//        mSoundPool = new SoundPool(MAX_SOUNDS, AudioManager.STREAM_MUSIC, 0);
        loadSounds();
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
            String assetPath = SOUNDS_FOLDER + "/" + filename;
            Sound sound = new Sound(assetPath);
            mSounds.add(sound);
        }

    }

    public List<Sound> getSounds() {
        return mSounds;
    }

}
