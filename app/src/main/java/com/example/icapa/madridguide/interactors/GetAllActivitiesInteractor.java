package com.example.icapa.madridguide.interactors;


import android.content.Context;

import com.example.icapa.madridguide.manager.net.ActivityEntity;
import com.example.icapa.madridguide.manager.net.NetworkManager;
import com.example.icapa.madridguide.model.Activities;
import com.example.icapa.madridguide.model.Activity;
import com.example.icapa.madridguide.model.Shops;
import com.example.icapa.madridguide.model.mappers.ActivityEntityActivityMapper;

import java.util.List;

public class GetAllActivitiesInteractor {
    public interface GetAllActivitiesInteractorResponse{
        public void response(Activities activities);
    }
    public void execute(Context context,final GetAllActivitiesInteractorResponse response){
        //...
        Shops shops = null;
        // testing
        NetworkManager networkManager = new NetworkManager(context);
        networkManager.getActivitiesFromServer(new NetworkManager.GetActivitiesListener() {
            @Override
            public void getActivityEntitiesSuccess(List<ActivityEntity> result) {
                List<Activity> activities = new ActivityEntityActivityMapper().map(result);
                if (response != null){
                    response.response(Activities.build(activities));
                }
            }

            @Override
            public void getActivityEntitiesDidFail() {
                if (response != null){
                    response.response(null);
                }
            }
        });
    }
}
