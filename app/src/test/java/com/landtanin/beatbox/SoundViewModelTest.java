package com.landtanin.beatbox;

import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

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

    @Test
    public void exposesSoundNameAsTitle(){

        assertThat(mSubject.getTitle(), is(mSound.getmName()));

    }

    @Test
    public void getmSoundShouldReturnmSound(){

        assertThat(mSubject.getmSound(), is(mSound));

    }

    @Test
    public void callsBeatBoxPlayOnButtonClicked(){

        mSubject.onButtonClicked();

        // verify() is Mockito's method.
        // This can be interpreted as - Verify that the play(...) method was called on mBeatBox with mSound as a parameter
        verify(mBeatBox).play(mSound);

    }

}