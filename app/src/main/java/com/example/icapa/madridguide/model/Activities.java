package com.example.icapa.madridguide.model;


import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Activities implements IActivitiesIterable,IActivitiesUpdatable,ITopicsIterable {

    List<Activity> mActivities;

    public static @NonNull
    Activities build(@NonNull final List<Activity> activityList){
        Activities activities = new Activities(activityList);
        if (activityList == null){
            activities.mActivities = new ArrayList<>();
        }
        return activities;
    }
    public static @NonNull Activities build(){
        return build(new ArrayList<Activity>());
    }

    private Activities(List<Activity> activities) {
        mActivities = activities;
    }

    private  Activities() {

    }

    @Override
    public long size() {
        return mActivities.size();
    }

    @Override
    public Activity get(long index) {
        return mActivities.get((int)index);
    }

    @Override
    public List<AnyTopic> allAnyTopics() {
        List<AnyTopic> anyTopics = new ArrayList<>();
        for (Activity activity: mActivities){
            anyTopics.add(activity);
        }
        return anyTopics;
    }

    @Override
    public List<Activity> allActivities() {
        return mActivities;
    }

    @Override
    public void add(Activity anyActivity) {
        mActivities.add(anyActivity);
    }

    @Override
    public void delete(Activity anyActivity) {
        mActivities.remove(anyActivity);
    }

    @Override
    public void edit(Activity newActivity, long index) {
        mActivities.set((int)index,newActivity);
    }

}
