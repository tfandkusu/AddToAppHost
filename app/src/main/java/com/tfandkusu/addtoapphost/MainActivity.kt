package com.tfandkusu.addtoapphost

import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.android.RenderMode

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val startFlutter = findViewById<Button>(R.id.startFlutter)
        startFlutter.setOnClickListener {
//            val intent = Intent(this, ::class.java)
//            startActivityForResult(intent, 0)
        }
        attachFlutterFragment()
    }


    private fun attachFlutterFragment() {
        val flutterFragment = FlutterFragment.NewEngineInGroupFragmentBuilder(MyApplication.FLUTTER_ENGINE_GROUP_ID)
            .renderMode(RenderMode.texture)
            .shouldAutomaticallyHandleOnBackPressed(true)
            .build<FlutterFragment>()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flutterFragment1, flutterFragment)
            .commitAllowingStateLoss()
    }

}
