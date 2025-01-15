package com.tfandkusu.addtoapphost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.FrameLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {

    private val observer: Observer<Boolean> = Observer { attach ->
        if (attach) {
            attachFlutterFragment()
        } else {
            detachFlutterFragment()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        FlutterHandler.mainFlutterFragmentAttach.observeForever(observer)
        val startFlutter = findViewById<Button>(R.id.startFlutter)
        startFlutter.setOnClickListener {
            val intent = Intent(this, FirstFlutterActivity::class.java)
            startActivityForResult(intent, 0)
        }
        val hideFlutter = findViewById<Button>(R.id.showHideFlutter)
        hideFlutter.setOnClickListener {
            val frame = findViewById<FrameLayout>(R.id.flutterFragment1)
            frame.isVisible = !frame.isVisible
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        FlutterHandler.mainFlutterFragmentAttach.removeObserver(observer)
    }

    private fun attachFlutterFragment() {
        Log.d("Takada", "attachFlutterFragment")
        val flutterFragment1 = FirstFlutterFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flutterFragment1, flutterFragment1)
            .commitAllowingStateLoss()
    }

    private fun detachFlutterFragment() {
        Log.d("Takada", "detachFlutterFragment")
        val flutterFragment1 = supportFragmentManager.findFragmentById(R.id.flutterFragment1)!!
        supportFragmentManager.beginTransaction()
            .remove(flutterFragment1)
            .commitAllowingStateLoss()
    }
}
