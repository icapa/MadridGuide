package com.example.icapa.madridguide.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Shops implements IShopsIterable,IShopsUpdatable,ITopicsIterable{

    List<Shop> mShops;

    public static @NonNull Shops build(@NonNull final List<Shop> shopList){
        Shops shops = new Shops(shopList);
        if (shopList == null){
            shops.mShops = new ArrayList<>();
        }
        return shops;
    }
    public static @NonNull Shops build(){
        return build(new ArrayList<Shop>());
    }

    private Shops(List<Shop> shops) {
        mShops = shops;
    }

    private  Shops() {

    }

    @Override
    public long size() {
        return mShops.size();
    }

    @Override
    public Shop get(long index) {
        return mShops.get((int)index);
    }

    @Override
    public List<AnyTopic> allAnyTopics() {
        List<AnyTopic> anyTopics = new ArrayList<>();
        for (Shop shop: mShops){
            anyTopics.add(shop);
        }
        return anyTopics;
    }

    @Override
    public List<Shop> allShops() {
        return mShops;
    }

    @Override
    public void add(Shop shop) {
        mShops.add(shop);
    }

    @Override
    public void delete(Shop shop) {
        mShops.remove(shop);
    }

    @Override
    public void edit(Shop newShop, long index) {
        mShops.set((int)index,newShop);
    }
}
