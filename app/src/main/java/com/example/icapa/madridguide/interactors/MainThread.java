package com.example.icapa.madridguide.interactors;

import android.os.Handler;
import android.os.Looper;

class MainThread {
    public static void run (final Runnable runnable){
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                runnable.run();
            }
        });
    }
}
