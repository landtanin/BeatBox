package com.landtanin.beatbox;

/**
 * Created by Tanin on 07/11/2017.
 */

public class SoundViewModel {

    private Sound mSound;
    private BeatBox mBeatBox;

    /**
     *
     * get beatBox to (eventually) play sound
     *
     * @param beatBox
     */
    public SoundViewModel(BeatBox beatBox) {

        mBeatBox = beatBox;

    }

    public String getTitle() {

        return mSound.getmName();

    }

    public Sound getmSound() {
        return mSound;
    }

    public void setmSound(Sound mSound) {
        this.mSound = mSound;
    }
}
