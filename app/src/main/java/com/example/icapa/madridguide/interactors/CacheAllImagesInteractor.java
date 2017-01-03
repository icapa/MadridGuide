package com.example.icapa.madridguide.interactors;


import android.content.Context;
import android.widget.ImageView;

import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;
import com.squareup.picasso.Picasso;

public class CacheAllImagesInteractor {
    public interface CacheAllImagesInteractorResponse{
        public void response(boolean resp);
    }
    public void execute(final Context context, final Shops shops, final CacheAllImagesInteractorResponse completion){
        new Thread(new Runnable() {
            @Override
            public void run() {
                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        ImageView fakeImageViewUrl;
                        ImageView fakeImageViewLogo;
                        for (Shop shop: shops.allShops()){
                            fakeImageViewUrl=null;
                            fakeImageViewLogo=null;
                            Picasso.with(context)
                                    .load(shop.getLogoImgUrl())
                                    .into(fakeImageViewUrl);

                            Picasso.with(context)
                                    .load(shop.getLogoImgUrl())
                                    .into(fakeImageViewLogo);


                            while(fakeImageViewLogo==null && fakeImageViewUrl==null)
                            {
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                        completion.response(true);
                    }
                });


            }
        }).start();
    }


}
