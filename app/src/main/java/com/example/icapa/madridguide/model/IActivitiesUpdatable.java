package com.example.icapa.madridguide.model;


public interface IActivitiesUpdatable {
    void add(Activity anyActivity);
    void delete(Activity anyActivity);
    void edit(Activity newActivity, long index);
}
