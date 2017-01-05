package com.example.icapa.madridguide.interactors;


import android.content.Context;

import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

public class CacheAllImagesInteractor {
    public interface CacheAllImagesInteractorResponse{
        public void response(boolean resp);
    }
    public void execute(final Context context, final Shops shops, final CacheAllImagesInteractorResponse completion){
        new Thread(new Runnable() {

            private void downloadImage(final Shop shop, final Shops shops){

                Picasso.with(context)
                        .load(shop.getLogoImgUrl())
                        .fetch();

                Picasso.with(context)
                        .load(shop.getImageUrl())
                        .fetch(new Callback() {
                            @Override
                            public void onSuccess() {
                                if (shops.get(shops.size()-1) == shop){
                                    completion.response(true);
                                }
                            }

                            @Override
                            public void onError() {
                                if (shops.get(shops.size()-1) == shop){
                                    completion.response(true);
                                }
                            }
                        });

            }
            @Override
            public void run() {
                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        for (Shop shop: shops.allShops()){
                            downloadImage(shop,shops);
                        }

                    }
                });


            }
        }).start();
    }


}
