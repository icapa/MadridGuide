package com.example.icapa.madridguide.interactors;


/*
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

*/