package com.second

import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.Logger
import com.clevertap.android.sdk.product_config.CTProductConfigListener
import java.util.*


class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clevertapi = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.createNotificationChannel(getApplicationContext(), "generic", "generic", "Game Of Thrones", NotificationManager.IMPORTANCE_MAX, true, "myring.mp3");

        CleverTapAPI.setDebugLevel(3);

        val cleverTapAPI = CleverTapAPI.getDefaultInstance(applicationContext)

        cleverTapAPI?.productConfig()?.setDefaults(R.xml.product_config_defaults)
        cleverTapAPI?.productConfig()?.fetchAndActivate();

        cleverTapAPI!!.setCTProductConfigListener(object : CTProductConfigListener {
            override fun onInit() {
                //Must Call activate if you want to apply the last fetched values on init every time.
                cleverTapAPI!!.productConfig().activate()
            }

            override fun onFetched() {
                var pc = cleverTapAPI?.productConfig();
                Logger.d("Clevertap Config");
                Logger.d(pc?.getString("reward type"));
                Logger.d("Clevertap Config");
                Logger.d(pc?.getString("price"));
                Logger.d("Clevertap Config");
            }
            override fun onActivated() {}
        })

    }
}