package com.example.icapa.madridguide.interactors;


import android.content.Context;

import com.example.icapa.madridguide.manager.net.NetworkManager;
import com.example.icapa.madridguide.manager.net.ShopEntity;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.model.Shops;
import com.example.icapa.madridguide.model.mappers.ShopEntityShopMapper;

import java.util.List;

public class GetAllShopsInteractor {
    public interface GetAllShopsInteractorResponse{
        public void response(Shops shops);
    }
    public void execute(final Context context, final GetAllShopsInteractorResponse response){
        //...
        Shops shops = null;
        // testing
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getShopsFromServer(new NetworkManager.GetShopsListener() {
            @Override
            public void getShopEntitiesSuccess(List<ShopEntity> result) {
                List<Shop> shops = new ShopEntityShopMapper().map(result);
                if (response != null){
                    response.response(Shops.build(shops));
                }

            }

            @Override
            public void getShopEntitiesDidFail() {
                if (response != null){
                    response.response(null);
                }
            }
        });
    }
}
