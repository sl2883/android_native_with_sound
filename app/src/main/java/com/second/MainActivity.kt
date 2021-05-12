package com.second

import android.app.NotificationManager
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.clevertap.android.sdk.CleverTapAPI
import com.clevertap.android.sdk.Logger
import com.clevertap.android.sdk.displayunits.DisplayUnitListener
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnit
import com.clevertap.android.sdk.displayunits.model.CleverTapDisplayUnitContent
import com.clevertap.android.sdk.product_config.CTProductConfigListener
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), DisplayUnitListener {

    var contentList:ArrayList<CleverTapDisplayUnitContent> = ArrayList();

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var clevertapi = CleverTapAPI.getDefaultInstance(getApplicationContext());
        CleverTapAPI.createNotificationChannel(
            getApplicationContext(),
            "generic",
            "generic",
            "Game Of Thrones",
            NotificationManager.IMPORTANCE_MAX,
            true,
            "myring.mp3"
        );
        CleverTapAPI.createNotificationChannel(
            getApplicationContext(),
            "generic1",
            "generic1",
            "Game Of Thrones",
            NotificationManager.IMPORTANCE_MAX,
            true
        );

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


        clevertapi?.apply {
            setDisplayUnitListener(this@MainActivity)
        }


        native_trigger.setOnClickListener(View.OnClickListener {
            clevertapi?.pushEvent("Native Trigger");
        })

        recyclerview.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
    }



    override fun onDisplayUnitsLoaded(units: ArrayList<CleverTapDisplayUnit>) {
        // you will get display units here
        // implement your logic to create your display views using these Display Units here
        Logger.v("i am here 1")
        for (i in 0 until units.size)
        {
            Logger.v("i am here 2")
            val unit = units.get(i)
            Logger.v(unit.contents[0].title)
            prepareDisplayView(unit)
        }

        var posts:ArrayList<String> = ArrayList()


        for (i in 1..100) {
            posts.add("Post #$i")
        }
//        recyclerview.adapter = PostsAdapter(posts);
        recyclerview.adapter = PostsAdapter(contentList);
    }

    fun prepareDisplayView(unit: CleverTapDisplayUnit) {
        contentList = ArrayList<CleverTapDisplayUnitContent>(contentList.plus(unit.contents))
        Logger.v("Added to display adapter")
        Logger.v((contentList.size).toString())
        Logger.v((contentList.size).toString())
    }
}