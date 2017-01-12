package com.example.icapa.madridguide.interactors;


import android.content.Context;

import com.example.icapa.madridguide.model.AnyTopic;
import com.example.icapa.madridguide.util.MapsUtilities;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CacheAllImagesInteractor {

    private final static int NUMBER_OF_IMAGES_TO_DOWNLOAD = 3;

    public interface CacheAllImagesInteractorResponse{
        public void response(boolean resp);
    }

    Object semaphoreRemainingCheck = new Object();

    public void execute(final Context context, final List<AnyTopic> anyTopics, final CacheAllImagesInteractorResponse completion){
        new Thread(new Runnable() {

            private long imageRemaining=anyTopics.size() * NUMBER_OF_IMAGES_TO_DOWNLOAD;



            private void downloadImage(final AnyTopic anyTopic){

                Picasso.with(context)
                        .load(anyTopic.getLogoImgUrl())
                        .fetch(new Callback() {
                            @Override
                            public void onSuccess() {
                               synchronized (semaphoreRemainingCheck) {
                                   imageRemaining--;
                                   if (imageRemaining == 0) {
                                       MainThread.run(new Runnable() {
                                           @Override
                                           public void run() {
                                               completion.response(true);
                                           }
                                       });
                                   }
                               }
                           }

                           @Override
                           public void onError() {
                               synchronized (semaphoreRemainingCheck) {
                                   imageRemaining--;
                                   if (imageRemaining == 0) {
                                       MainThread.run(new Runnable() {
                                           @Override
                                           public void run() {
                                               completion.response(true);
                                           }
                                       });
                                   }
                               }
                           }
                       });

                Picasso.with(context)
                        .load(anyTopic.getImageUrl())
                        .fetch(new Callback() {
                            @Override
                            public void onSuccess() {

                                synchronized (semaphoreRemainingCheck) {
                                    imageRemaining--;
                                    if (imageRemaining == 0) {
                                        MainThread.run(new Runnable() {
                                            @Override
                                            public void run() {
                                                completion.response(true);
                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onError() {
                                synchronized (semaphoreRemainingCheck) {
                                    imageRemaining--;
                                    if (imageRemaining == 0) {
                                        MainThread.run(new Runnable() {
                                            @Override
                                            public void run() {
                                                completion.response(true);
                                            }
                                        });
                                    }
                                }
                            }
                        });

                Picasso.with(context)
                        .load(MapsUtilities.GetUrlImageFromMap(anyTopic.getLatitude(),anyTopic.getLongitude()))
                        .fetch(new Callback() {
                            @Override
                            public void onSuccess() {

                                synchronized (semaphoreRemainingCheck) {
                                    imageRemaining--;
                                    if (imageRemaining == 0) {
                                        MainThread.run(new Runnable() {
                                            @Override
                                            public void run() {
                                                completion.response(true);
                                            }
                                        });
                                    }
                                }
                            }

                            @Override
                            public void onError() {
                                synchronized (semaphoreRemainingCheck) {
                                    imageRemaining--;
                                    if (imageRemaining == 0) {
                                        MainThread.run(new Runnable() {
                                            @Override
                                            public void run() {
                                                completion.response(true);
                                            }
                                        });
                                    }
                                }
                            }
                        });
            }
            @Override
            public void run() {
                for (AnyTopic anyTopic: anyTopics){
                    downloadImage(anyTopic);
                }
                
            }
        }).start();
    }


}
