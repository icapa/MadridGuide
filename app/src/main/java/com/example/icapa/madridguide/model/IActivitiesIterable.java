package com.example.icapa.madridguide.model;


import java.util.List;

public interface IActivitiesIterable {

    long size();
    AnyTopic get(long index);
    List<Activity> allActivities();
}
