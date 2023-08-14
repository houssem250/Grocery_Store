package com.example.grocerystore.res;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;

import com.example.grocerystore.R;

public class ClickSound {
    private static MediaPlayer clickSound;
    private static boolean isSoundEnabled = true;
    private static final int delayMillisClick = 500;

    public static void init(Context context) {
        clickSound = MediaPlayer.create(context, R.raw.click_sound);
    }

    public static void playSound() {
        if (isSoundEnabled) {
            isSoundEnabled = false;
            executeSound();
            new Handler().postDelayed(() -> isSoundEnabled = true, delayMillisClick); // Set a delay between clicks
        }
    }

    private static void executeSound() {
        if (clickSound.isPlaying()) {
            clickSound.seekTo(0); // Rewind the sound to the beginning
        } else {
            clickSound.start();
        }
    }

    public static void releaseResources() {
        if (clickSound != null) {
            clickSound.release();
            clickSound = null;
        }
    }
}