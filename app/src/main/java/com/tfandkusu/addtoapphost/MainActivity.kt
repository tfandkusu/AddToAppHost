package com.tfandkusu.addtoapphost

import android.app.ComponentCaller
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import io.flutter.embedding.android.FlutterFragment
import io.flutter.embedding.engine.FlutterEngineCache
import io.flutter.plugin.common.MethodChannel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val startFlutter = findViewById<Button>(R.id.startFlutter)
        startFlutter.setOnClickListener {
            val intent = Intent(this, FirstFlutterActivity::class.java)
            startActivityForResult(intent, 0)
        }
        if (true) {
            val flutterEngine = FlutterEngineCache
                .getInstance().get(MyApplication.FLUTTER_ENGINE_ID_1)
            if (flutterEngine != null) {
                MethodChannel(
                    flutterEngine.dartExecutor.binaryMessenger,
                    "com.tfandkusu.ga913flutter"
                ).setMethodCallHandler { call, result ->
                    if (call.method == "navigateToDetail") {
                        call.argument<Int>("id")?.let {
                            val intent = Intent(this, NativeDetailActivity::class.java)
                            intent.putExtra(NativeDetailActivity.EXTRA_ID, it)
                            startActivityForResult(intent, 0)
                            // removeFlutterFragment()
                            result.success(null)
                        }
                    }
                }
            }
            addFlutterFragment()
        }
    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        caller: ComponentCaller
    ) {
        // addFlutterFragment()
    }

    private fun addFlutterFragment() {
        val flutterFragment1 = FlutterFragment.withCachedEngine(
            MyApplication.FLUTTER_ENGINE_ID_1
        ).build<FlutterFragment>()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flutterFragment1, flutterFragment1)
            .commit()
        val flutterFragment2 = FlutterFragment.withCachedEngine(
            MyApplication.FLUTTER_ENGINE_ID_2
        ).build<FlutterFragment>()
        supportFragmentManager.beginTransaction()
            .replace(R.id.flutterFragment2, flutterFragment2)
            .commit()
    }

    private fun removeFlutterFragment() {
        supportFragmentManager.beginTransaction()
            .remove(supportFragmentManager.findFragmentById(R.id.flutterFragment1)!!)
            .commit()
        supportFragmentManager.beginTransaction()
            .remove(supportFragmentManager.findFragmentById(R.id.flutterFragment2)!!)
            .commit()
    }
}
