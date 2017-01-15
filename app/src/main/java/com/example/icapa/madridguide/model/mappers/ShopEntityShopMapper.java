package com.example.icapa.madridguide.model.mappers;


import com.example.icapa.madridguide.manager.net.ShopEntity;
import com.example.icapa.madridguide.model.Shop;
import com.example.icapa.madridguide.util.Constants;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ShopEntityShopMapper {
    public List<Shop> map(List<ShopEntity> shopEntities){
        List<Shop> result = new LinkedList<>();

        String lan = Locale.getDefault().getLanguage();

        for (ShopEntity entity: shopEntities){
            Shop shop = new Shop(entity.getId(),entity.getName());
            // Detect current lang

            if (lan.equals(Constants.SPANISH_LANGUAGE)) {
                shop.setDescription(entity.getDescriptionEs());
            }
            else{
                shop.setDescription(entity.getDescriptionEn());
            }

            shop.setLogoImgUrl(entity.getLogoImg());
            //... continuar con mapeo
            shop.setUrl(entity.getUrl());
            shop.setLongitude(entity.getLongitude());
            shop.setLatitude(entity.getLatitude());
            shop.setAddress(entity.getAddress());
            shop.setImageUrl(entity.getImg());

            result.add(shop);
        }
        return result;
    }
}
