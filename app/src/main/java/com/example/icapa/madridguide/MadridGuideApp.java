package com.example.icapa.madridguide;

import android.app.Application;
import android.content.Context;

import com.squareup.picasso.OkHttpDownloader;
import com.squareup.picasso.Picasso;

import java.lang.ref.WeakReference;


public class MadridGuideApp extends Application {

    private static WeakReference<Context> appContext;

    @Override
    public void onCreate() {
        super.onCreate();

        // Init your app
        appContext = new WeakReference<Context>(getApplicationContext());

        // insert test data in DB
        //insertTestDataInDB();

        // Log de picasso

        Picasso.Builder builder = new Picasso.Builder(appContext.get());
        builder.downloader(new OkHttpDownloader(appContext.get(),Integer.MAX_VALUE));
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        built.setLoggingEnabled(true);
        Picasso.setSingletonInstance(built);

        /*
        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
        Picasso.with(getApplicationContext()).setIndicatorsEnabled(true);
        */

        /* Esto era la primera aproximacion */
        /*
        new GetAllShopsInteractor().execute(getApplicationContext(), new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
            @Override
            public void response(Shops shops) {
                new CacheAllShopsInteractor().execute(getApplicationContext(),
                    shops, new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
                        @Override
                        public void response(boolean success) {

                        }
                    });
            }
        });
        */

    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    public static Context getAppContext(){
        return appContext.get();
    }
}
