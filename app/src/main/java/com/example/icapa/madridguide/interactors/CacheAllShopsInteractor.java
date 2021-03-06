package com.example.icapa.madridguide.interactors;


import android.content.Context;

import com.example.icapa.madridguide.manager.db.AnyTopicDAO;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;

public class CacheAllShopsInteractor {
    public interface CacheAllShopsInteractorResponse{
        public void response(boolean success);
    }
    public void execute(final Context context, final Shops shops, final CacheAllShopsInteractorResponse response) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                AnyTopicDAO dao = new AnyTopicDAO(context);
                boolean sucess=true;
                for (Shop shop: shops.allShops()){
                    sucess = dao.insert(shop)>0;
                    if (!sucess){
                        break;
                    }
                }

                if (response!=null){
                    response.response(sucess);
                }



            }

        }).start();


    }
}

