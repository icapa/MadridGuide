package com.example.icapa.madridguide.activities;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.icapa.madridguide.R;
import com.example.icapa.madridguide.interactors.CacheAllImagesInteractor;
import com.example.icapa.madridguide.interactors.CacheAllShopsInteractor;
import com.example.icapa.madridguide.interactors.GetAllShopsInteractor;
import com.example.icapa.madridguide.manager.preferences.InvalidateCacheCalendar;
import com.example.icapa.madridguide.model.Shops;
import com.example.icapa.madridguide.navigator.Navigator;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.example.icapa.madridguide.manager.preferences.InvalidateCacheCalendar.CacheIsOld;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.activity_main_shops_button)
    Button shopsButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        getShopsData();



    }


    private void getShopsData(){
        if (CacheIsOld(this,new Date())){
            shopsButton.setText("Downloading....");
            initShopsData(new CacheAllImagesInteractor.CacheAllImagesInteractorResponse(){
                @Override
                public void response(boolean success) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            InvalidateCacheCalendar.MarkCacheAsValid(MainActivity.this);
                            Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
                        }
                    });
                }
            });
        }else{
            Navigator.navigateFromMainActivityToShopsActivity(MainActivity.this);
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
                                new CacheAllImagesInteractor().execute(getApplicationContext(), shops, allTaskDone);
                            }
                        });
            }
        });
    }

    /*
    private void initShopsData(){
        new GetAllShopsInteractor().execute(getApplicationContext(), new GetAllShopsInteractor.GetAllShopsInteractorResponse() {
            @Override
            public void response(Shops shops) {
                new CacheAllShopsInteractor().execute(getApplicationContext(),
                        shops, new CacheAllShopsInteractor.CacheAllShopsInteractorResponse() {
                            @Override
                            public void response(boolean success) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this,"Lo descargue !!!",Toast.LENGTH_LONG);
                                        InvalidateCacheCalendar.MarkCacheAsValid(MainActivity.this);
                                        shopsButton.setText("CARGADOOO");
                                    }
                                });

                            }
                        });
            }
        });
    }*/
}
