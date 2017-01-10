package com.example.icapa.madridguide.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.interactors.CacheAllActivitiesInteractor;
import com.example.icapa.madridguide.interactors.CacheAllImagesInteractor;
import com.example.icapa.madridguide.interactors.CacheAllShopsInteractor;
import com.example.icapa.madridguide.interactors.GetAllActivitiesInteractor;
import com.example.icapa.madridguide.interactors.GetAllShopsInteractor;
import com.example.icapa.madridguide.manager.preferences.InvalidateCacheCalendar;
import com.example.icapa.madridguide.model.Activities;
import com.example.icapa.madridguide.model.Shops;
import com.example.icapa.madridguide.navigator.Navigator;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.icapa.madridguide.manager.preferences.InvalidateCacheCalendar.CacheIsOld;

public class MainActivity extends AppCompatActivity {
    private static final int TOTAL_TASK_TO_PERFORM=2;


    @BindView(R.id.activity_main_test_button)
    Button testButton;

    @BindView(R.id.activity_main_shops_button)
    Button viewShopsButton;
    @BindView(R.id.activity_main_activitites_button)
    Button viewActivitiesButton;

    Object semaphoreObject = new Object();

    private int taskLoadNumber = TOTAL_TASK_TO_PERFORM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getTopicsData();

        configureButtonEvents();


    }

    private void configureButtonEvents() {

        viewShopsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvalidateCacheCalendar.MarkCacheAsValid(MainActivity.this);
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this,ShopsActivity.SHOW_SHOPS);
            }
        });

        viewActivitiesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                InvalidateCacheCalendar.MarkCacheAsValid(MainActivity.this);
                Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this,ShopsActivity.SHOW_ACTIVITIES);
            }
        });
    }


    private void getTopicsData(){
        if (CacheIsOld(this,new Date())){
            testButton.setText("Downloading....");
            initShopsData(new CacheAllImagesInteractor.CacheAllImagesInteractorResponse(){
                @Override
                public void response(boolean success) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (semaphoreObject) {
                                checkTaskCompleteAndExit();
                            }
                        }
                    });
                }
            });
            initActivitiesData(new CacheAllImagesInteractor.CacheAllImagesInteractorResponse() {
                @Override
                public void response(boolean resp) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            synchronized (semaphoreObject) {
                                checkTaskCompleteAndExit();

                            }
                        }
                    });
                }
            });
        }else{
            ActivateAllButtons();
        }
    }


    private void initShopsData(final CacheAllImagesInteractor.CacheAllImagesInteractorResponse allTaskDone){
        new GetAllShopsInteractor().execute(getApplicationContext(), new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
            @Override
            public void response(final Shops shops) {

                new CacheAllShopsInteractor().execute(getApplicationContext(),
                        shops, new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
                            @Override
                            public void response(boolean success) {
                                new CacheAllImagesInteractor().execute(getApplicationContext(), shops.allAnyTopics(), allTaskDone);
                            }
                        });
            }
        });
    }

    private void initActivitiesData(final CacheAllImagesInteractor.CacheAllImagesInteractorResponse allTaskDone){
        new GetAllActivitiesInteractor().execute(getApplicationContext(), new GetAllActivitiesInteractor.GetAllActivitiesInteractorResponse() {
            @Override
            public void response(final Activities activities) {
                new CacheAllActivitiesInteractor().execute(getApplicationContext(),
                        activities, new CacheAllActivitiesInteractor.CacheAllActivitiesInteractorResponse() {
                            @Override
                            public void response(boolean success) {
                                new CacheAllImagesInteractor().execute(getApplicationContext(),
                                        activities.allAnyTopics(),allTaskDone);
                            }
                });
            }
        });
    }


    private void checkTaskCompleteAndExit(){
        taskLoadNumber--;
        testButton.setText("Paso" + taskLoadNumber);
        if (taskLoadNumber==0){
            ActivateAllButtons();
        }
    }

    private void ActivateAllButtons() {
        testButton.setText("OLE OLE");
        viewShopsButton.setEnabled(true);
        viewActivitiesButton.setEnabled(true);
    }
}
