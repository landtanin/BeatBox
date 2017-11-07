package com.landtanin.beatbox;

import android.databinding.BaseObservable;
import android.databinding.Bindable;

/**
 * Created by Tanin on 07/11/2017.
 */

public class SoundViewModel extends BaseObservable{

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

    @Bindable
    public String getTitle() {

        return mSound.getmName();

    }

    public Sound getmSound() {
        return mSound;
    }

    public void setmSound(Sound sound) {
        mSound = sound;

        // notifies your binding class that all of the Bindable fields on your objects have been updated
        notifyChange();
    }
}
