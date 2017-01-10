package com.example.icapa.madridguide.model.mappers;


import com.example.icapa.madridguide.manager.net.ActivityEntity;
import com.example.icapa.madridguide.model.Activity;

import java.util.LinkedList;
import java.util.List;

public class ActivityEntityActivityMapper {
    public List<Activity> map(List<ActivityEntity> activityEntities){

        List<Activity> result = new LinkedList<>();

        for (ActivityEntity entity: activityEntities){
            Activity activity = new Activity(entity.getId(),entity.getName());
            // Detect current lang
            activity.setDescription(entity.getDescriptionEs());
            activity.setLogoImgUrl(entity.getLogoImg());
            activity.setUrl(entity.getUrl());
            activity.setLongitude(entity.getLongitude());
            activity.setLatitude(entity.getLatitude());
            activity.setAddress(entity.getAddress());
            activity.setImageUrl(entity.getImg());
            result.add(activity);
        }
        return result;
    }
}
