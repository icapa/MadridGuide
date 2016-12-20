package com.example.icapa.madridguide.model.mappers;


import com.example.icapa.madridguide.manager.net.ShopEntity;
import com.example.icapa.madridguide.model.Shop;

import java.util.LinkedList;
import java.util.List;

public class ShopEntityShopMapper {
    public List<Shop> map(List<ShopEntity> shopEntities){
        List<Shop> result = new LinkedList<>();

        for (ShopEntity entity: shopEntities){
            Shop shop = new Shop(entity.getId(),entity.getName());
            // Detect current lang
            shop.setDescription(entity.getDescriptionEs());
            shop.setLogoImgUrl(entity.getLogoImg());
            //... continuar con mapeo
            result.add(shop);
        }
        return result;
    }
}
