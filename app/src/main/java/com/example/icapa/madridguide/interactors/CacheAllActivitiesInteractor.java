package com.example.icapa.madridguide.interactors;


import android.content.Context;

import com.example.icapa.madridguide.manager.db.AnyTopicDAO;
import com.example.icapa.madridguide.model.Activities;
import com.example.icapa.madridguide.model.Activity;

public class CacheAllActivitiesInteractor {
    public interface CacheAllActivitiesInteractorResponse{
        public void response(boolean success);
    }
    public void execute(final Context context, final Activities activities, final CacheAllActivitiesInteractorResponse response) {
        new Thread(new Runnable(){

            @Override
            public void run() {
                AnyTopicDAO dao = new AnyTopicDAO(context);
                boolean sucess=true;
                for (Activity activity: activities.allActivities()){
                    sucess = dao.insert(activity)>0;
                    if (!sucess){
                        break;
                    }
                }

                if (response!=null){
                    response.response(sucess);
                }


            }

        }).start();


    }

}

