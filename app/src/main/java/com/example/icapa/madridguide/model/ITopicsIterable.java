package com.example.icapa.madridguide.model;


import java.util.List;

public interface  ITopicsIterable {

    long size();
    AnyTopic get(long index);
    List<AnyTopic> allAnyTopics();
}
