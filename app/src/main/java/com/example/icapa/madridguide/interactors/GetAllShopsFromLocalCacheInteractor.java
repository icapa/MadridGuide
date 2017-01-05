package com.example.icapa.madridguide.interactors;


import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.example.icapa.madridguide.manager.db.ShopDAO;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;

import java.util.List;

public class GetAllShopsFromLocalCacheInteractor {
    public interface OnGetAllShopsFromLocalCacheInteractor{
       public void completion(Shops shop);
    }

    public void execute(final Context context, final OnGetAllShopsFromLocalCacheInteractor completion){
        new Thread(new Runnable() {
            @Override
            public void run() {
                ShopDAO dao = new ShopDAO(context);
                List<Shop> shopList = dao.query();
                final Shops shops = Shops.build(shopList);

                MainThread.run(new Runnable() {
                    @Override
                    public void run() {
                        completion.completion(shops);
                    }
                });


            }
        }).start();
    }
}

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