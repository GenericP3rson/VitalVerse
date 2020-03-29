package com.example.test4;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

//import HealthDataStore;
//package com.samsung.android.sdk.HealthData;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;

import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;
import com.samsung.android.sdk.healthdata.HealthConstants;
import com.samsung.android.sdk.healthdata.HealthData;
import com.samsung.android.sdk.healthdata.HealthDataResolver;
import com.samsung.android.sdk.healthdata.HealthDataService;
import com.samsung.android.sdk.healthdata.HealthDataStore;
import com.samsung.android.sdk.healthdata.HealthPermissionManager;
import com.samsung.android.sdk.healthdata.HealthResultHolder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicBoolean;

import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    public static final String APP_TAG = "SimpleHealth";
    private HealthDataStore mStore;
    private HealthConnectionErrorResult mConnError;
    private final Set<HealthPermissionManager.PermissionKey> mKeySet;
    private HealthResultHolder<HealthDataResolver.ReadResult> result;
//    private HealthDataStore mStore;
//    Set<PermissionKey> mKeys = new HashSet<PermissionKey>();
//    private final WeakReference<Activity> mInstance;

//    private static MainActivity mInstance = null;
//    private HealthDataStore mStore;
//    private HealthConnectionErrorResult mConnError;
//    private Set<PermissionKey> mKeySet;
    private final HealthResultHolder.ResultListener<HealthPermissionManager.PermissionResult> mPermissionListener =
        new HealthResultHolder.ResultListener<HealthPermissionManager.PermissionResult>() {

            @Override
            public void onResult(HealthPermissionManager.PermissionResult result) {

                Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = result.getResultMap();

                if (resultMap.values().contains(Boolean.FALSE)) {
                    Log.d(APP_TAG, "All required permissions are not granted.");
                } else {
                    Log.d(APP_TAG, "All required permissions are granted.");
                    //Access health data
                }
            }
        };
    public void requestPermission() {

        // Acquire permission
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        mKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.HeartRate.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        mKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.BloodPressure.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
        mKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.OxygenSaturation.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));

        try {
            pmsManager.requestPermissions(mKeySet, MainActivity.this).setResultListener(mPermissionListener);
        } catch (Exception e) {
            Log.d(APP_TAG, "requestPermissions() fails");
        }
    }
    MainActivity(){
//        this.mInstance = new WeakReference<>(mInstance);
        mKeySet = new HashSet<>();
//        mKeySet.add(new HealthPermissionManager.PermissionKey("com.samsung.shealth.step_daily_trend", HealthPermissionManager.PermissionType.READ));
//        mKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.StepCount.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
//        mKeySet.add(new HealthPermissionManager.PermissionKey(HealthConstants.HeartRate.HEALTH_DATA_TYPE, HealthPermissionManager.PermissionType.READ));
//        HealthDataService healthDataService = new HealthDataService();

//        try {
//            healthDataService.initialize(mInstance);
////            mStore = new HealthDataStore(mInstance, mConnectionListener);
////            // Request the connection to the health data store
////            mStore.connectService();
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
//        mInstance = this;
//        mKeySet = new HashSet<>();
//        mKeySet.add(new PermissionKey(HealthConstants.StepCount.HEALTH_DATA_TYPE, PermissionType.READ));
//        // Create a HealthDataStore instance and set its listener
//        mStore = new HealthDataStore(this, mConnectionListener);
//        // Request the connection to the health data store
//        mStore.connectService();
        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
        Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = pmsManager.isPermissionAcquired(mKeySet);

        if (!resultMap.containsValue(Boolean.FALSE)) {
            System.out.println("SUCCESS");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getVitals(View v) {
        // Do something in response to button click
        // System.out.println("CLICKED");
//        System.out.println(mKeySet);
        HealthData h = new HealthData();

        System.out.println(HealthConstants.HeartRate.HEALTH_DATA_TYPE);
//        System.out.println(h.getSourceDevice());
////        HealthPermissionManager pmsManager = new HealthPermissionManager(mStore);
////        Map<HealthPermissionManager.PermissionKey, Boolean> resultMap = pmsManager.isPermissionAcquired(mKeySet);
//        System.out.println(HealthConstants.HeartRate.BINNING_DATA);
        if (h.getSourceDevice() == null) {
            System.out.println("There's been an error: No Detection found");
        } else {
            requestPermission();
//            for ( x : mKeySet) System.out.println(x);
            System.out.println(HealthConstants.HeartRate.HEALTH_DATA_TYPE);
            System.out.println(HealthConstants.BloodPressure.HEALTH_DATA_TYPE);
            System.out.println(HealthConstants.OxygenSaturation.HEALTH_DATA_TYPE);
        }


    }

}
