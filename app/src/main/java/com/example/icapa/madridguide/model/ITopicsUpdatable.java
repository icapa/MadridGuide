package com.example.icapa.madridguide.model;


public interface ITopicsUpdatable {
    void add(AnyTopic anyTopic);
    void delete(AnyTopic anyTopic);
    void edit(AnyTopic newAnyTopic,long index);
}
