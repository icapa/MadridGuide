package com.example.icapa.madridguide.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AnyTopics implements ITopicsIterable,ITopicsUpdatable {
    List<AnyTopic> mAnyTopics;

    public static @NonNull
    AnyTopics build(@NonNull final List<AnyTopic> shopList){
        AnyTopics anyTopics = new AnyTopics(shopList);
        if (anyTopics == null){
            anyTopics.mAnyTopics = new ArrayList<>();
        }
        return anyTopics;
    }
    public static @NonNull AnyTopics build(){
        return build(new ArrayList<AnyTopic>());
    }

    public AnyTopics(List<AnyTopic> anyTopics) {
        mAnyTopics = anyTopics;
    }

    private  AnyTopics() {

    }

    @Override
    public long size() {
        return mAnyTopics.size();
    }

    @Override
    public AnyTopic get(long index) {
        return mAnyTopics.get((int)index);
    }

    @Override
    public List<AnyTopic> allAnyTopics() {
        return mAnyTopics;
    }


    @Override
    public void add(AnyTopic anyTopic) {
        mAnyTopics.add(anyTopic);
    }

    @Override
    public void delete(AnyTopic anyTopic) {
        mAnyTopics.remove(anyTopic);
    }

    @Override
    public void edit(AnyTopic newAnytopic, long index) {
        mAnyTopics.set((int)index,newAnytopic);
    }
}
