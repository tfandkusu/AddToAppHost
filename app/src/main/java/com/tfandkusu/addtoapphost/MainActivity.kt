package com.tfandkusu.addtoapphost

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

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
        val startFlutter = findViewById<Button>(R.id.startFlutter)
        startFlutter.setOnClickListener {
            val intent = Intent(this, FirstFlutterActivity::class.java)
            startActivityForResult(intent, 0)
        }
        val flutterEngine = FlutterEngineCache
            .getInstance().get(MyApplication.FLUTTER_ENGINE_ID)
        if (flutterEngine != null) {
            MethodChannel(
                flutterEngine.dartExecutor.binaryMessenger,
                "com.tfandkusu.ga913flutter"
            ).setMethodCallHandler { call, result ->
                if (call.method == "navigateToDetail") {
                    call.argument<Int>("id")?.let {
                        Log.d("Takada","navigateToDetail")
                        val intent = Intent(this, NativeDetailActivity::class.java)
                        intent.putExtra(NativeDetailActivity.EXTRA_ID, it)
                        startActivity(intent)
                        result.success(null)
                    }
                }
            }
        }
        FlutterHandler.mainFlutterFragmentAttach.observeForever(observer)
    }

    override fun onDestroy() {
        super.onDestroy()
        FlutterHandler.mainFlutterFragmentAttach.removeObserver(observer)
    }

    private fun attachFlutterFragment() {
        Log.d("Takada", "attachFlutterFragment")
        val flutterFragment1 = FlutterFragment.withCachedEngine(
            MyApplication.FLUTTER_ENGINE_ID
        ).build<FlutterFragment>()
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
