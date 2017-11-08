package com.landtanin.beatbox;

import org.junit.Before;

import static org.mockito.Mockito.mock;

/**
 * Created by Tanin on 08/11/2017.
 */
public class SoundViewModelTest {

    private BeatBox mBeatBox;
    private Sound mSound;
    private SoundViewModel mSubject; // named it mSubject to remind us that this is the subject(class) we are testing

    // this is where you get all the instances of related objects/dependencies ready
    @Before
    public void setUp() throws Exception {

        // we use Mock to get a dull version of BeatBox object. This null object has no idea what BeatBox do.
        // Therefore, if BeatBox is broken, it won't know.
        mBeatBox = mock(BeatBox.class);

        // unlike BeatBox, Sound is just a data class, there's nothing to break
        // So, we can just use the normal way to create its instance
        mSound = new Sound("assetPath");
        mSubject = new SoundViewModel(mBeatBox);
        mSubject.setmSound(mSound);

    }

}