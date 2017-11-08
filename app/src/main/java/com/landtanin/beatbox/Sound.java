package com.landtanin.beatbox;

/**
 * Created by Tanin on 07/11/2017.
 */

public class Sound {

    private String mAssetPath;
    private String mName;
    private Integer mSoundId;
    // Using Integer instead of an int allow us to say that a Sound.java
    // has no value set for mSoundId by assigning it a null value

    public Sound(String assetPath) {
        mAssetPath = assetPath;
        String[] components = assetPath.split("/");
        String filename = components[components.length - 1];
        mName = filename.replace(".wav", "");
    }

    public String getmAssetPath() {
        return mAssetPath;
    }

    public String getmName() {
        return mName;
    }

    public Integer getmSoundId() {
        return mSoundId;
    }

    public void setmSoundId(Integer mSoundId) {
        this.mSoundId = mSoundId;
    }
}
