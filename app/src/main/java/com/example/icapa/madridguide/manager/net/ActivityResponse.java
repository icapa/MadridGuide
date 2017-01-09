package com.example.icapa.madridguide.manager.net;


import com.example.icapa.madridguide.model.Activity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ActivityResponse {
    @SerializedName("result")
    List<ActivityEntity> result;
}
