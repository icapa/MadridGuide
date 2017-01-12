package com.example.icapa.madridguide.model.mappers;


import com.example.icapa.madridguide.manager.net.ActivityEntity;
import com.example.icapa.madridguide.model.Activity;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class ActivityEntityActivityMapper {
    public List<Activity> map(List<ActivityEntity> activityEntities){

        List<Activity> result = new LinkedList<>();

        String lan = Locale.getDefault().getDisplayLanguage();

        for (ActivityEntity entity: activityEntities){
            Activity activity = new Activity(entity.getId(),entity.getName());
            // Detect current lang
            if (lan == "es") {
                activity.setDescription(entity.getDescriptionEs());
            }
            else{
                activity.setDescription(entity.getDescriptionEn());
            }

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
