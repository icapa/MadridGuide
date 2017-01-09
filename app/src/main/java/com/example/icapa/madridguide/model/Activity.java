package com.example.icapa.madridguide.model;


final public class Activity extends AnyTopic {
    public static String TOPIC_NAME = "Activity";

    public Activity(long id, String name) {
        super(id,name);
        setType(Activity.TOPIC_NAME);
    }



}
